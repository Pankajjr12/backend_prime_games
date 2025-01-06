package com.kumar.gamesstore.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.domain.OrderStatus;
import com.kumar.gamesstore.exceptions.OrderException;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.Order;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.services.OrderService;
import com.kumar.gamesstore.services.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/zseller/orders")
@RequiredArgsConstructor
public class SellerOrderController {
	
	  private final OrderService orderService;

	  private final SellerService sellerService;
	  
	  
	  @GetMapping()
	    public ResponseEntity<List<Order>> getAllOrdersHandler(
	            @RequestHeader("Authorization") String jwt
	    ) throws  SellerException {
	        Seller seller=sellerService.getSellerProfile(jwt);
	        List<Order> orders=orderService.getShopsOrders(seller.getId());

	        return new ResponseEntity<>(orders, HttpStatus.ACCEPTED);
	    }

	    @PatchMapping("/{orderId}/status/{orderStatus}")
	    public ResponseEntity<Order> updateOrderHandler(
	            @RequestHeader("Authorization") String jwt,
	            @PathVariable Long orderId,
	            @PathVariable OrderStatus orderStatus
	    ) throws OrderException {

	        Order orders=orderService.updateOrderStatus(orderId,orderStatus);

	        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
	    }
	    
	    @DeleteMapping("/{orderId}/delete")
	    public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId,
	                                                          @RequestHeader("Authorization") String jwt) throws OrderException{
	        orderService.deleteOrder(orderId);
	        ApiResponse res=new ApiResponse("Order Deleted Successfully",true);
	        System.out.println("delete method working....");
	        return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
	    }

}
