package com.kumar.gamesstore.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.CouponNotValidException;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Coupon;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.repositories.CartRepository;
import com.kumar.gamesstore.repositories.CouponRepository;
import com.kumar.gamesstore.repositories.UserRepository;
import com.kumar.gamesstore.services.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

	private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
	
	@Override
	public Cart applyCoupon(String code, double orderValue, User user) throws Exception {
		
		Coupon coupon = couponRepository.findByCode(code);
        Cart cart = cartRepository.findByUserId(user.getId());
        
        if (coupon==null) {
            throw new CouponNotValidException("coupon not found");
        }
        if(user.getUsedCoupons().contains(coupon)){
            throw new CouponNotValidException("coupon already used");
        }
        if(orderValue <= coupon.getMinimumOrderValue()){
            throw new CouponNotValidException("valid for minimum order value "+coupon.getMinimumOrderValue() );
        }
        
        if (
                coupon.isActive() &&
                LocalDate.now().isAfter(coupon.getValidityStartDate()) &&
                LocalDate.now().isBefore(coupon.getValidityEndDate())


        ) {

            user.getUsedCoupons().add(coupon);
            userRepository.save(user);

            double discountedPrice = Math.round((cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100);
            cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountedPrice);
            cart.setCouponCode(code);
            cart.setCouponPrice((int) discountedPrice);
            return cartRepository.save(cart);
//            return cart;
        }
        throw new CouponNotValidException("coupon not valid...");
	
	}

	@Override
	public Cart removeCoupon(String code, User user) throws Exception {
	
		
	    Coupon coupon = couponRepository.findByCode(code);

        if(coupon==null){
            throw new Exception("coupon not found...");
        }
        user.getUsedCoupons().remove(coupon);

        Cart cart = cartRepository.findByUserId(user.getId());
        cart.setTotalSellingPrice(cart.getTotalSellingPrice()+cart.getCouponPrice());
        cart.setCouponCode(null);
        cart.setCouponPrice(0);
        return cartRepository.save(cart);
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	    public Coupon createCoupon(Coupon coupon) {
	        return couponRepository.save(coupon);
	    }

	 
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	    public void deleteCoupon(Long couponId) {
	        couponRepository.deleteById(couponId);
	    }

	    @Override
	    @PreAuthorize("hasRole('ADMIN')")
	    public List<Coupon> getAllCoupons() {
	        return couponRepository.findAll();
	    } 

	@Override
	public Coupon getCouponById(Long couponId) throws Exception {
		return couponRepository.findById(couponId).orElseThrow(()->new Exception("coupon not found"));// TODO Auto-generated method stub
	}

}
