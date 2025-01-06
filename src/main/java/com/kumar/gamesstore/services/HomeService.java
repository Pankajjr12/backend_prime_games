package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.modals.Home;
import com.kumar.gamesstore.modals.HomeCategory;

public interface HomeService {

	
	Home creatHomePageData(List<HomeCategory> allCategories);
	 Home getHomePageData();
}
