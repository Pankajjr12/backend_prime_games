package com.kumar.gamesstore.services;

import java.util.Set;

import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.PaymentOrder;
import com.kumar.gamesstore.modals.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;
import com.stripe.exception.StripeException;

public interface PaymentService {

	 PaymentOrder createOrder(User user,
             Set<Order> orders);
	 
	 PaymentOrder getPaymentOrderById(Long id) throws Exception;


	 PaymentOrder getPaymentOrderByPaymentId(String paymentId) throws Exception;


	 Boolean ProceedPaymentOrder (PaymentOrder paymentOrder,
                 String paymentId, String paymentLinkId) throws RazorpayException;


	 PaymentLink createRazorpayPaymentLink(User user,
                          Long Amount,
                          Long orderId) throws RazorpayException;


	 String createStripePaymentLink(User user, Long amount,
                            Long orderId) throws StripeException;
}
