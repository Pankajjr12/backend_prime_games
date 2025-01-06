package com.kumar.gamesstore.ai.services;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.responses.ApiResponse;

public interface AiChatbotService {
	  ApiResponse aiChatBot(String prompt,Long productId,Long userId) throws ProductException;
}


