package com.kumar.gamesstore.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.domain.PaymentMethod;
import com.kumar.gamesstore.exceptions.OrderException;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.exceptions.UserException;
import com.kumar.gamesstore.modals.Address;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.OrderItem;
import com.kumar.gamesstore.modals.PaymentOrder;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.SellerReport;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.PaymentOrderRepository;
import com.kumar.gamesstore.responses.PaymentLinkResponse;
import com.kumar.gamesstore.services.CartService;
import com.kumar.gamesstore.services.OrderItemService;
import com.kumar.gamesstore.services.OrderService;
import com.kumar.gamesstore.services.PaymentService;
import com.kumar.gamesstore.services.SellerReportService;
import com.kumar.gamesstore.services.SellerService;
import com.kumar.gamesstore.services.UserService;
import com.razorpay.PaymentLink;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/orders")
public class OrderController {
	
	private final OrderService orderService;
	private final UserService userService;
	private final OrderItemService orderItemService;
	private final SellerService sellerService;
	private final SellerReportService sellerReportService;
	private final CartService cartService;
	private final PaymentOrderRepository paymentOrderRepository;
	
	private final PaymentService paymentService;
	
	
	@PostMapping()
	public ResponseEntity<PaymentLinkResponse> createOrderHandler(
			@RequestBody Address shippingAddress,
			@RequestParam PaymentMethod paymentMethod,
			@RequestHeader("Authorization")String jwt)
            throws Exception {
		
		User user=userService.findUserByJwtToken(jwt);
		Cart cart=cartService.findUserCart(user);
		Set<Order> orders =orderService.createOrder(user, shippingAddress,cart);

		PaymentOrder paymentOrder=paymentService.createOrder(user,orders);

		PaymentLinkResponse res = new PaymentLinkResponse();
//
		if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
			PaymentLink payment=paymentService.createRazorpayPaymentLink(user,
					paymentOrder.getAmount(),
					paymentOrder.getId());
			String paymentUrl=payment.get("short_url");
			String paymentUrlId=payment.get("id");

			res.setPayment_link_url(paymentUrl);
			res.setPayment_link_id(paymentUrlId);
			paymentOrder.setPaymentLinkId(paymentUrlId);
			paymentOrderRepository.save(paymentOrder);
		}
		else{
			String paymentUrl=paymentService.createStripePaymentLink(user,
					paymentOrder.getAmount(),
					paymentOrder.getId());
			res.setPayment_link_url(paymentUrl);
		}
		return new ResponseEntity<>(res,HttpStatus.OK);

	}
	
	@GetMapping("/user")
	public ResponseEntity< List<Order>> usersOrderHistoryHandler(
			@RequestHeader("Authorization")
	String jwt) throws UserException{
		
		User user=userService.findUserByJwtToken(jwt);
		List<Order> orders=orderService.usersOrderHistory(user.getId());
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{orderId}")
	public ResponseEntity< Order> getOrderById(@PathVariable Long orderId, @RequestHeader("Authorization")
	String jwt) throws OrderException, UserException{
		
		User user = userService.findUserByJwtToken(jwt);
		Order orders=orderService.findOrderById(orderId);
		return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/item/{orderItemId}")
	public ResponseEntity<OrderItem> getOrderItemById(
			@PathVariable Long orderItemId, @RequestHeader("Authorization")
	String jwt) throws Exception {
		System.out.println("------- controller ");
		User user = userService.findUserByJwtToken(jwt);
		OrderItem orderItem=orderItemService.getOrderItemById(orderItemId);
		return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrder(
			@PathVariable Long orderId,
			@RequestHeader("Authorization") String jwt
	) throws UserException, OrderException, SellerException {
		User user=userService.findUserByJwtToken(jwt);
		Order order=orderService.cancelOrder(orderId,user);

		Seller seller= sellerService.getSellerById(order.getSellerId());
		SellerReport report=sellerReportService.getSellerReport(seller);

		report.setCancelOrders(report.getCancelOrders()+1);
		report.setTotalRefunds(report.getTotalRefunds()+order.getTotalSellingPrice());
		sellerReportService.updateSellerReport(report);

		return ResponseEntity.ok(order);
	}

}
