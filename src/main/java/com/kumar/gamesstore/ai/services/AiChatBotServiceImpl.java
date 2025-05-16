package com.kumar.gamesstore.ai.services;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.mapper.OrderMapper;
import com.kumar.gamesstore.mapper.ProductMapper;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.User;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.kumar.gamesstore.repositories.CartRepository;
import com.kumar.gamesstore.repositories.OrderRepository;
import com.kumar.gamesstore.repositories.ProductRepository;
import com.kumar.gamesstore.repositories.UserRepository;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.responses.FunctionResponse;

@Service
public class AiChatBotServiceImpl implements AiChatbotService {

    String GEMINI_API_KEY = "AIzaSyAxdinQIIN6EfCAOYjN37_tV7xbl5IoiWg";

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public AiChatBotServiceImpl(CartRepository cartRepository, OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private JSONArray createFunctionDeclarations() {
        return new JSONArray()
                .put(new JSONObject()
                        .put("name", "getUserCart")
                        .put("description", "Retrieve the user's cart details")
                        .put("parameters", new JSONObject()
                                .put("type", "OBJECT")
                                .put("properties", new JSONObject()
                                        .put("cart", new JSONObject()
                                                .put("type", "STRING")
                                                .put("description", "Cart Details, like total item in cart, cart item, remove item from cart, cart Id")
                                        )
                                )
                                .put("required", new JSONArray()
                                        .put("cart")
                                )
                        )
                )
                .put(new JSONObject()
                        .put("name", "getUsersOrder")
                        .put("description", "Retrieve the user's order details")
                        .put("parameters", new JSONObject()
                                .put("type", "OBJECT")
                                .put("properties", new JSONObject()
                                        .put("order", new JSONObject()
                                                .put("type", "STRING")
                                                .put("description", "Order Details, order, total order, current order, delivered order, pending order, current order, canceled order")
                                        )
                                )
                                .put("required", new JSONArray()
                                        .put("order")
                                )
                        )
                )
                .put(new JSONObject()
                        .put("name", "getProductDetails")
                        .put("description", "Retrieve product details")
                        .put("parameters", new JSONObject()
                                .put("type", "OBJECT")
                                .put("properties", new JSONObject()
                                        .put("product", new JSONObject()
                                                .put("type", "STRING")
                                                .put("description", "The Product Details like, Product title, product id, product color, product size, selling price, mrp price, rating extra...")
                                        )
                                )
                                .put("required", new JSONArray()
                                        .put("product")
                                )
                        )
                );
    }

    private FunctionResponse processFunctionCall(JSONObject functionCall,
            Long productId,
            Long userId) throws ProductException {
        String functionName = functionCall.getString("name");
        JSONObject args = functionCall.getJSONObject("args");

        FunctionResponse res = new FunctionResponse();
        res.setFunctionName(functionName);
        User user = userRepository.findById(userId).orElse(null);

        switch (functionName) {
            case "getUserCart":
                Cart cart = cartRepository.findByUserId(userId);
                System.out.println("cart: " + cart.getId());
                res.setUserCart(cart);
                break;
            case "getUsersOrder":
                List<Order> orders = orderRepository.findByUserId(userId);
                res.setOrderHistory(OrderMapper.toOrderHistory(orders, user));
                System.out.println("order history: " + OrderMapper.toOrderHistory(orders, user));
                break;
            case "getProductDetails":
                Product product = productRepository.findById(productId).orElseThrow(
                        () -> new ProductException("product not found")
                );
                res.setProduct(product);
                break;
            default:
                throw new IllegalArgumentException("Unsupported function: " + functionName);
        }
        return res;
    }

    public FunctionResponse getFunctionResponse(String prompt, Long productId, Long userId) throws ProductException {
        String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=" + GEMINI_API_KEY;

        JSONObject requestBodyJson = new JSONObject()
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("parts", new JSONArray()
                                        .put(new JSONObject()
                                                .put("text", prompt)
                                        )
                                )
                        )
                ).put("tools", new JSONArray()
                        .put(new JSONObject()
                                .put("functionDeclarations", createFunctionDeclarations())
                        )
                );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBodyJson.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(GEMINI_API_URL, requestEntity, String.class);

        String responseBody = response.getBody();
        System.out.println("Response Body: " + responseBody); // Debugging

        JSONObject jsonObject = new JSONObject(responseBody);
        JSONArray candidates = jsonObject.optJSONArray("candidates");
        if (candidates != null && candidates.length() > 0) {
            JSONObject firstCandidate = candidates.optJSONObject(0);
            if (firstCandidate != null) {
                JSONObject content = firstCandidate.optJSONObject("content");
                if (content != null) {
                    JSONArray parts = content.optJSONArray("parts");
                    if (parts != null && parts.length() > 0) {
                        JSONObject firstPart = parts.optJSONObject(0);
                        if (firstPart != null) {
                            JSONObject functionCall = firstPart.optJSONObject("functionCall");
                            if (functionCall != null) {
                                return processFunctionCall(functionCall, productId, userId);
                            } else {
                                System.out.println("functionCall not found in response");
                            }
                        }
                    }
                }
            }
        }
        throw new JSONException("Invalid response structure, functionCall not found.");
    }

    @Override
    public ApiResponse aiChatBot(String prompt, Long productId, Long userId) throws ProductException {
        ApiResponse response = new ApiResponse();

        // Handle specific responses based on the prompt
        if ("hi".equalsIgnoreCase(prompt) || "hello".equalsIgnoreCase(prompt)) {
            response.setMessage("Hi, my name is Panku. I am your assistant today. Please type if you have any query.");
            return response;
        } else if ("ok".equalsIgnoreCase(prompt)) {
            response.setMessage("Ok bye have a nice day. 👍 ");
            return response;
        }

        // If not "hi" or "ok", continue with the usual functionality.
        String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + GEMINI_API_KEY;

        System.out.println("------- " + prompt);

        FunctionResponse functionResponse = getFunctionResponse(prompt, productId, userId);
        System.out.println("------- " + functionResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Construct the request body
        String body = new JSONObject()
                .put("contents", new JSONArray()
                        .put(new JSONObject()
                                .put("role", "user")
                                .put("parts", new JSONArray()
                                        .put(new JSONObject()
                                                .put("text", prompt)
                                        )
                                )
                        )
                        .put(new JSONObject()
                                .put("role", "model")
                                .put("parts", new JSONArray()
                                        .put(new JSONObject()
                                                .put("functionCall", new JSONObject()
                                                        .put("name", functionResponse.getFunctionName())
                                                        .put("args", new JSONObject()
                                                                .put("cart", functionResponse.getUserCart() != null ? functionResponse.getUserCart().getUser() : null)
                                                                .put("order", functionResponse.getOrderHistory() != null ? functionResponse.getOrderHistory() : null)
                                                                .put("product", functionResponse.getProduct() != null ? ProductMapper.toProductDto(functionResponse.getProduct()) : null)
                                                        )
                                                )
                                        )
                                )
                        )
                        .put(new JSONObject()
                                .put("role", "function")
                                .put("parts", new JSONArray()
                                        .put(new JSONObject()
                                                .put("functionResponse", new JSONObject()
                                                        .put("name", functionResponse.getFunctionName())
                                                        .put("response", new JSONObject()
                                                                .put("name", functionResponse.getFunctionName())
                                                                .put("content", functionResponse)
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .put("tools", new JSONArray()
                        .put(new JSONObject()
                                .put("functionDeclarations", createFunctionDeclarations())
                        )
                )
                .toString();

        // Make the API request
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> geminiResponse = restTemplate.postForEntity(GEMINI_API_URL, request, String.class);

        // Process the response
        String responseBody = geminiResponse.getBody();
        JSONObject jsonObject = new JSONObject(responseBody);

        // Extract the first candidate
        JSONArray candidates = jsonObject.optJSONArray("candidates");
        JSONObject firstCandidate = candidates != null && candidates.length() > 0 ? candidates.optJSONObject(0) : null;

        if (firstCandidate != null) {
            // Extract the text
            JSONObject content = firstCandidate.optJSONObject("content");
            if (content != null) {
                JSONArray parts = content.optJSONArray("parts");
                JSONObject firstPart = parts != null && parts.length() > 0 ? parts.optJSONObject(0) : null;
                if (firstPart != null) {
                    String text = firstPart.optString("text", "No text found in response");

                    // Prepare and return the API response
                    response.setMessage(text);
                    return response;
                }
            }
        }

        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setMessage("Error: function call response structure is invalid.");
        return errorResponse;
    }
}
