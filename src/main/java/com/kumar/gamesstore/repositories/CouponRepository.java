package com.kumar.gamesstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kumar.gamesstore.modals.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

	 Coupon findByCode(String couponCode);
}
