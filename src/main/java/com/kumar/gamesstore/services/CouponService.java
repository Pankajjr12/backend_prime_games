package com.kumar.gamesstore.services;

import java.util.List;

import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Coupon;
import com.kumar.gamesstore.modals.User;

public interface CouponService {

	
	    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
	    Cart removeCoupon(String code, User user) throws Exception;
	    Coupon createCoupon(Coupon coupon);
	    void deleteCoupon(Long couponId);
	    List<Coupon> getAllCoupons();
	    
	    Coupon getCouponById(Long couponId) throws Exception;
}
