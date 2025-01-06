package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long> {


    PaymentOrder findByPaymentLinkId(String paymentId);
}
