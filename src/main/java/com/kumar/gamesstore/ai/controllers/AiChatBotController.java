package com.kumar.gamesstore.ai.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.ai.services.AiChatbotService;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.requests.Prompt;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.services.UserService;

@RestController
@RequestMapping("/ai/chat")
public class AiChatBotController {

    private final AiChatbotService aiChatBotService;
    private final UserService userService;

    public AiChatBotController(AiChatbotService aiChatBotService, UserService userService) {
        this.aiChatBotService = aiChatBotService;
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse> generate(
            @RequestBody Prompt prompt,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long productId,
            @RequestHeader(required = false, name = "Authorization") String jwt) throws Exception {

        ApiResponse apiResponse = new ApiResponse();

        // Step 1: Check if user is authenticated
        if (jwt == null && userId == null) {
            apiResponse.setMessage("Please login or signup first to chat with me.");
            return ResponseEntity.ok(apiResponse);
        }

        // Step 2: Get userId from JWT if provided
        if (jwt != null && userId == null) {
            User user = userService.findUserByJwtToken(jwt);
            if (user == null) {
                apiResponse.setMessage("Invalid token. Please login again.");
                return ResponseEntity.ok(apiResponse);
            }
            userId = user.getId();
        }

        // Step 3: Prepare prompt for AI
        String message = prompt.getPrompt();
        if (productId != null) {
            message = "The product id is " + productId + ", " + message;
        }

        // Step 4: Call AI service
        apiResponse = aiChatBotService.aiChatBot(message, productId, userId);

        return ResponseEntity.ok(apiResponse);
    }
}
