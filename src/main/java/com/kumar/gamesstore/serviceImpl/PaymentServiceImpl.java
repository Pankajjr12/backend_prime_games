package com.kumar.gamesstore.serviceImpl;

import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.domain.PaymentOrderStatus;
import com.kumar.gamesstore.domain.PaymentStatus;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.PaymentOrder;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.CartRepository;
import com.kumar.gamesstore.repositories.OrderRepository;
import com.kumar.gamesstore.repositories.PaymentOrderRepository;
import com.kumar.gamesstore.services.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

	 private final PaymentOrderRepository paymentOrderRepository;
	    
	 private final OrderRepository orderRepository;
	    
	 private final CartRepository cartRepository;
	 
     @Value("${stripe.api.key}")
     private String stripeApiKey;
     
     @Value("${stripe.secret.key}")
     private String stripeSecretKey;
     
     @Value("${razorpay.api.key}")
     private String apiKey;
     
     @Value("${razorpay.api.secret}")
     private String apiSecret;
     
			 
	
	@Override
	public PaymentOrder createOrder(User user, Set<Order> orders) {
		// TODO Auto-generated method stub
		
		Long amount=orders.stream().mapToLong(Order::getTotalSellingPrice).sum();
        int couponPrice=cartRepository.findByUserId(user.getId()).getCouponPrice();
        
        PaymentOrder order=new PaymentOrder();
        order.setUser(user);
        order.setAmount(amount-couponPrice);
        order.setOrders(orders);
        return paymentOrderRepository.save(order);
	}
	

    @Override
    public PaymentOrder getPaymentOrderById(Long id) throws Exception {
        Optional<PaymentOrder> optionalPaymentOrder=paymentOrderRepository.findById(id);
        if(optionalPaymentOrder.isEmpty()){
            throw new Exception("payment order not found with id "+id);
        }
        return optionalPaymentOrder.get();
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String paymentLinkId) throws Exception {
        PaymentOrder paymentOrder = paymentOrderRepository
                .findByPaymentLinkId(paymentLinkId);

        if(paymentOrder==null){
            throw new Exception("payment order not found with id "+paymentLinkId);
        }
        return paymentOrder;
    }


    @Override
    public Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,
                                       String paymentId,
                                       String paymentLinkId) throws RazorpayException {

        if(paymentOrder.getStatus().equals(PaymentOrderStatus.PENDING)){


                RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);
                Payment payment = razorpay.payments.fetch(paymentId);

                Integer amount = payment.get("amount");
                String status = payment.get("status");

                if(status.equals("captured")){
//                    System.out.println("payment ===== captured");
                    Set<Order> orders=paymentOrder.getOrders();
                    for(Order order:orders){
                        order.setPaymentStatus(PaymentStatus.COMPLETED);
                        orderRepository.save(order);
                    }
                    paymentOrder.setStatus(PaymentOrderStatus.SUCCESS);
                    paymentOrderRepository.save(paymentOrder);


                    return true;
                }
                paymentOrder.setStatus(PaymentOrderStatus.FAILED);
                paymentOrderRepository.save(paymentOrder);
                return false;
            }

            return false;
    }
    
	@Override
	public PaymentLink createRazorpayPaymentLink(User user, Long Amount, Long orderId) throws RazorpayException {
		// TODO Auto-generated method stub
		
		Long amount = Amount * 100;
		
		try {
	        RazorpayClient razorpay = new RazorpayClient(apiKey, apiSecret);

            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",amount);
            paymentLinkRequest.put("currency","INR");
            
            // Create a JSON object with the customer details
            JSONObject customer = new JSONObject();
            customer.put("name",user.getFullName());

            customer.put("email",user.getEmail());
            paymentLinkRequest.put("customer",customer);

            // Create a JSON object with the notification settings
            JSONObject notify = new JSONObject();
            notify.put("email",true);
            paymentLinkRequest.put("notify",notify);

            // Set the reminder settings
            paymentLinkRequest.put("reminder_enable",true);
            
            // Set the callback URL and method
            paymentLinkRequest.put("callback_url","http://localhost:3000/payment-success/"+orderId);
            paymentLinkRequest.put("callback_method","get");

            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkUrl = payment.get("short_url");
            String paymentLinkId = payment.get("id");



            System.out.println("payment ----- "+payment);

            return payment;
			
		} catch (RazorpayException e) {

            System.out.println("Error creating payment link: " + e.getMessage());
            throw new RazorpayException(e.getMessage());
        }
	}

	@Override
	public String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {
		// TODO Auto-generated method stub
		
		Stripe.apiKey = stripeSecretKey;
		
		SessionCreateParams params = SessionCreateParams.builder()
				   .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
	                .setMode(SessionCreateParams.Mode.PAYMENT)
	                .setSuccessUrl("http://localhost:3000/payment-success/"+orderId)
	                .setCancelUrl("http://localhost:3000/payment/cancel")
	                .addLineItem(SessionCreateParams.LineItem.builder()
	                        .setQuantity(1L)
	                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
	                                .setCurrency("usd")
	                                .setUnitAmount(amount*100)
	                                .setProductData(SessionCreateParams
	                                        .LineItem
	                                        .PriceData
	                                        .ProductData
	                                        .builder()
	                                        .setName("Top up wallet")
	                                        .build()
	                                ).build()
	                        ).build()
	                ).build();
		Session session = Session.create(params);

	    System.out.println("session _____ " + session);

//	        PaymentLinkResponse res = new PaymentLinkResponse();
//	        res.setPayment_link_url(session.getUrl());

	    return session.getUrl();
	}

}
