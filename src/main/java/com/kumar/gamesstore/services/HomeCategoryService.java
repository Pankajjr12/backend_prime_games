package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.modals.HomeCategory;

public interface HomeCategoryService {

    HomeCategory createCategory(HomeCategory category);
    List<HomeCategory> createCategories(List<HomeCategory> categories);
    List<HomeCategory> getAllCategories();
    HomeCategory updateCategory(HomeCategory categories,Long id) throws Exception;
	
}
