package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.Transaction;

public interface TransactionService {

	    Transaction createTransaction(Order order);
	    List<Transaction> getTransactionBySeller(Seller seller);
	    List<Transaction>getAllTransactions();
}
