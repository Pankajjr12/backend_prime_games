package com.kumar.gamesstore.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.Transaction;
import com.kumar.gamesstore.repositories.SellerRepository;
import com.kumar.gamesstore.repositories.TransactionRepository;
import com.kumar.gamesstore.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final SellerRepository sellerRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, SellerRepository sellerRepository) {

        this.transactionRepository = transactionRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Transaction createTransaction(Order order) {

        Seller seller = sellerRepository.findById(order.getSellerId()).get();
        Transaction transaction = new Transaction();
        transaction.setCustomer(order.getUser());
        transaction.setOrder(order);
        transaction.setSeller(seller);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionBySeller(Seller seller) {
        return transactionRepository.findBySellerId(seller.getId());
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
