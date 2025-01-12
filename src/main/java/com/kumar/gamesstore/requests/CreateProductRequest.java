package com.kumar.gamesstore.requests;

import java.util.List;

import jakarta.persistence.Column;
import lombok.Data;
@Data
public class CreateProductRequest {

	
	private String title;
	
	@Column(length = 2000)
	private String description;
	  
	private int mrpPrice;
	private int sellingPrice;
	private String brand;
	
	private double numRatings;
	
	@Column(length = 5000)
	private List<String> images;
	
	private String category;
	private String category2;
	private String category3;
	
	private String years;
	private String platform;
}
