package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Deal;

public interface DealRepository extends JpaRepository<Deal, Long> {

}
