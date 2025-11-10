package com.kumar.gamesstore.ai.services;

import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.mapper.OrderMapper;
import com.kumar.gamesstore.mapper.ProductMapper;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.CartRepository;
import com.kumar.gamesstore.repositories.OrderRepository;
import com.kumar.gamesstore.repositories.ProductRepository;
import com.kumar.gamesstore.repositories.UserRepository;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.responses.FunctionResponse;

@Service
public class AiChatBotServiceImpl implements AiChatbotService {

    @Value("${gemini.api.key}")
    private String GEMINI_API_KEY;

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public AiChatBotServiceImpl(
            CartRepository cartRepository,
            OrderRepository orderRepository,
            ProductRepository productRepository,
            UserRepository userRepository
    ) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // -------------------- Function Declarations --------------------
    private JSONArray createFunctionDeclarations() {
        JSONArray declarations = new JSONArray();

        // getUserCart
        declarations.put(new JSONObject()
                .put("name", "getUserCart")
                .put("description", "Retrieve the user's cart details")
                .put("parameters", new JSONObject()
                        .put("type", "OBJECT")
                        .put("properties", new JSONObject()
                                .put("cart", new JSONObject()
                                        .put("type", "STRING")
                                        .put("description", "Cart details such as cart ID, items, total, etc.")))
                        .put("required", new JSONArray().put("cart"))
                )
        );

        // getUsersOrder
        declarations.put(new JSONObject()
                .put("name", "getUsersOrder")
                .put("description", "Retrieve the user's order history and status")
                .put("parameters", new JSONObject()
                        .put("type", "OBJECT")
                        .put("properties", new JSONObject()
                                .put("order", new JSONObject()
                                        .put("type", "STRING")
                                        .put("description", "Order details such as current, delivered, pending, canceled orders")))
                        .put("required", new JSONArray().put("order"))
                )
        );

        // getProductDetails
        declarations.put(new JSONObject()
                .put("name", "getProductDetails")
                .put("description", "Retrieve product details")
                .put("parameters", new JSONObject()
                        .put("type", "OBJECT")
                        .put("properties", new JSONObject()
                                .put("product", new JSONObject()
                                        .put("type", "STRING")
                                        .put("description", "Product title, id, color, size, price, rating, etc.")))
                        .put("required", new JSONArray().put("product"))
                )
        );

        return declarations;
    }

    // -------------------- Process Function Call --------------------
    private FunctionResponse processFunctionCall(JSONObject functionCall, Long productId, Long userId)
            throws ProductException {

        String functionName = functionCall.optString("name", "");
        FunctionResponse res = new FunctionResponse();
        res.setFunctionName(functionName);

        // Fetch user safely
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ProductException("User not found with id: " + userId)
        );

        switch (functionName) {
            case "getUserCart":
                Cart cart = cartRepository.findByUserId(userId);
                if (cart != null) {
                    res.setUserCart(cart);
                }
                break;

            case "getUsersOrder":
                List<Order> orders = orderRepository.findByUserId(userId);
                res.setOrderHistory(OrderMapper.toOrderHistory(orders, user));
                break;

            case "getProductDetails":
                Optional<Product> optProduct = productRepository.findById(productId);
                if (optProduct.isEmpty()) {
                    throw new ProductException("Product not found with id: " + productId);
                }
                res.setProduct(optProduct.get());
                break;

            default:
                throw new IllegalArgumentException("Unsupported function: " + functionName);
        }

        return res;
    }

    // -------------------- Gemini API Call --------------------
    public FunctionResponse getFunctionResponse(String prompt, Long productId, Long userId) throws ProductException {
        String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + GEMINI_API_KEY;

        JSONObject requestBody = new JSONObject()
                .put("contents", new JSONArray().put(
                        new JSONObject().put("parts", new JSONArray()
                                .put(new JSONObject().put("text", prompt))
                        )))
                .put("tools", new JSONArray().put(new JSONObject()
                        .put("functionDeclarations", createFunctionDeclarations())
                ));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody.toString(), headers);
        ResponseEntity<String> response = restTemplate.postForEntity(GEMINI_API_URL, requestEntity, String.class);

        String responseBody = response.getBody();
        if (responseBody == null || responseBody.isEmpty()) {
            throw new JSONException("Empty response from Gemini API");
        }

        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONArray candidates = jsonResponse.optJSONArray("candidates");
        if (candidates == null || candidates.isEmpty()) {
            throw new JSONException("No candidates found in response");
        }

        JSONObject content = candidates.getJSONObject(0).optJSONObject("content");
        if (content == null) {
            throw new JSONException("Missing content in Gemini response");
        }

        JSONArray parts = content.optJSONArray("parts");
        if (parts == null || parts.isEmpty()) {
            throw new JSONException("Missing parts in Gemini response");
        }

        JSONObject firstPart = parts.getJSONObject(0);
        JSONObject functionCall = firstPart.optJSONObject("functionCall");
        if (functionCall == null) {
            throw new JSONException("Function call not found in Gemini response");
        }

        return processFunctionCall(functionCall, productId, userId);
    }

    // -------------------- AI Chat --------------------
    @Override
    public ApiResponse aiChatBot(String prompt, Long productId, Long userId) throws ProductException {
        ApiResponse response = new ApiResponse();

        // -------------------- Not logged in --------------------
        if (userId == null) {
            response.setMessage("Please login or signup first to chat with me.");
            return response;
        }

        // -------------------- Simple greetings --------------------
        if ("hi".equalsIgnoreCase(prompt) || "hello".equalsIgnoreCase(prompt)) {
            response.setMessage("Hi, I’m Panku 🤖 — your personal assistant! How can I help you today?");
            return response;
        } else if ("ok".equalsIgnoreCase(prompt)) {
            response.setMessage("Okay! Have a great day. 👋");
            return response;
        }

        // -------------------- Call Gemini --------------------
        FunctionResponse functionResponse = getFunctionResponse(prompt, productId, userId);

        // Build safe args JSON
        JSONObject args = new JSONObject();
        if (functionResponse.getUserCart() != null) {
            args.put("cart", functionResponse.getUserCart());
        }
        if (functionResponse.getOrderHistory() != null) {
            args.put("order", functionResponse.getOrderHistory());
        }
        if (functionResponse.getProduct() != null) {
            args.put("product", ProductMapper.toProductDto(functionResponse.getProduct()));
        }

        JSONObject requestBody = new JSONObject()
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("role", "user")
                                .put("parts", new JSONArray().put(new JSONObject().put("text", prompt))))
                        .put(new JSONObject()
                                .put("role", "model")
                                .put("parts", new JSONArray().put(new JSONObject()
                                        .put("functionCall", new JSONObject()
                                                .put("name", functionResponse.getFunctionName())
                                                .put("args", args)))))
                        .put(new JSONObject()
                                .put("role", "function")
                                .put("parts", new JSONArray().put(new JSONObject()
                                        .put("functionResponse", new JSONObject()
                                                .put("name", functionResponse.getFunctionName())
                                                .put("response", new JSONObject()
                                                        .put("content", functionResponse)))))))
                .put("tools", new JSONArray().put(new JSONObject()
                        .put("functionDeclarations", createFunctionDeclarations())));

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(requestBody.toString(), headers);
        String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + GEMINI_API_KEY;
        ResponseEntity<String> geminiResponse = restTemplate.postForEntity(GEMINI_API_URL, request, String.class);

        String responseBody = geminiResponse.getBody();
        if (responseBody == null) {
            response.setMessage("Error: Empty response from Gemini API.");
            return response;
        }

        JSONObject json = new JSONObject(responseBody);
        JSONArray candidates = json.optJSONArray("candidates");
        if (candidates != null && !candidates.isEmpty()) {
            JSONObject first = candidates.getJSONObject(0);
            JSONObject content = first.optJSONObject("content");
            if (content != null) {
                JSONArray parts = content.optJSONArray("parts");
                if (parts != null && !parts.isEmpty()) {
                    String text = parts.getJSONObject(0).optString("text", "No response text found.");
                    response.setMessage(text);
                    return response;
                }
            }
        }

        response.setMessage("Error: Unexpected Gemini response format.");
        return response;
    }
}
