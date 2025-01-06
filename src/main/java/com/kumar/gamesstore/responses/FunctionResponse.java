package com.kumar.gamesstore.responses;

import com.kumar.gamesstore.dto.OrderHistory;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
	public class FunctionResponse {
	    private String functionName;
	    private Cart userCart;
	    private OrderHistory orderHistory;
	    private Product product;
	}

