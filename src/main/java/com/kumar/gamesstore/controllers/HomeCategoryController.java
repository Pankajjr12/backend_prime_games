package com.kumar.gamesstore.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.modals.Home;
import com.kumar.gamesstore.modals.HomeCategory;
import com.kumar.gamesstore.services.HomeCategoryService;
import com.kumar.gamesstore.services.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class HomeCategoryController {

	
	   private final HomeCategoryService homeCategoryService;
	    private final HomeService homeService;

	    @GetMapping("/home-page")
	    public ResponseEntity<Home> getHomePageData() {
	        Home homePageData = homeService.getHomePageData();
	        return new ResponseEntity<>(homePageData, HttpStatus.ACCEPTED);
	 
	    }

	    @PostMapping("/home/categories")
	    public ResponseEntity<Home> createHomeCategories(
	            @RequestBody List<HomeCategory> homeCategories
	    ) {
	        List<HomeCategory> categories = homeCategoryService.createCategories(homeCategories);
	        Home home=homeService.creatHomePageData(categories);
	        return new ResponseEntity<>(home, HttpStatus.ACCEPTED);
	    }

	    
	    @GetMapping("/home-category")
	    public ResponseEntity<List<HomeCategory>> getHomeCategory(
	          ) throws Exception {

	        List<HomeCategory> categories=homeCategoryService.getAllCategories();
	        return ResponseEntity.ok(categories);

	    }
}
