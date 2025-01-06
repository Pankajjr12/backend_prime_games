package com.kumar.gamesstore.modals;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Home {

	
	private List<HomeCategory> grid;
	private List<HomeCategory> shopByCategories;
	
	private List<HomeCategory> gameCategories;
	private List<HomeCategory> dealCategories;
	
	private List<Deal> deals;
	
}
