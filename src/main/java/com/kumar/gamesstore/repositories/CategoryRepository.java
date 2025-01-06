package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	Category findByCategoryId(String categoryId);
}
