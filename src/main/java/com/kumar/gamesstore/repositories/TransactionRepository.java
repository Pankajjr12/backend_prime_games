package com.kumar.gamesstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findBySellerId(Long sellerId);
}
