package com.kumar.gamesstore.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemRequest {

	
	private Long productId;
	private String year;
	private int quantity;
	private Integer price;
	
}
