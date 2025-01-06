package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.HomeCategory;

public interface HomeCategoryRepository extends JpaRepository<HomeCategory, Long> {

}
