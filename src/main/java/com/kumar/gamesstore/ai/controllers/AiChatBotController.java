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

        String message = prompt.getPrompt();
        if (productId != null) {
            message = "the product id is " + productId + ", " + message;
        }

        User user = new User();
        if (jwt != null) {
            user = userService.findUserByJwtToken(jwt);
        }

//        Long userId;
//        if(user!=null){
//            userId=user.getId();
//        }
        ApiResponse apiResponse = aiChatBotService.aiChatBot(message, productId, user.getId());

        return ResponseEntity.ok(apiResponse);

    }

}
