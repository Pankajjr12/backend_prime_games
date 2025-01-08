package com.kumar.gamesstore.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.config.JwtProvider;
import com.kumar.gamesstore.domain.UserRole;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.Cart;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.User;
import com.kumar.gamesstore.modals.VerificationCode;
import com.kumar.gamesstore.repositories.CartRepository;
import com.kumar.gamesstore.repositories.SellerRepository;
import com.kumar.gamesstore.repositories.UserRepository;
import com.kumar.gamesstore.repositories.VerificationRepository;
import com.kumar.gamesstore.requests.LoginRequest;
import com.kumar.gamesstore.requests.SignUpRequest;
import com.kumar.gamesstore.responses.AuthResponse;
import com.kumar.gamesstore.services.AuthService;
import com.kumar.gamesstore.services.EmailService;
import com.kumar.gamesstore.services.UserService;
import com.kumar.gamesstore.utils.OtpUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private SellerRepository sellerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private VerificationRepository verificationRepository;
	
	private final CustomUserServiceImpl customUserServiceImpl;
	
	@Override
	public String createUser(SignUpRequest req) throws Exception {
		// TODO Auto-generated method stub
		
		VerificationCode verificationCode = verificationRepository.findByEmail(req.getEmail());
		if (verificationCode == null) {
		    throw new Exception("No verification code found for the email.");
		}
		if (!verificationCode.getOtp().equals(req.getOtp())) {
		    throw new Exception("The OTP does not match.");
		}

		
		
		User user = userRepository.findByEmail(req.getEmail());
		
		if(user == null) {
			User createdUser = new User();
			
			createdUser.setFullName(req.getFullName());
			createdUser.setEmail(req.getEmail());
			createdUser.setRole(UserRole.ROLE_CUSTOMER);
			createdUser.setMobile(req.getMobile());
			createdUser.setPassword(passwordEncoder.encode(req.getOtp()));
			
			user = userRepository.save(createdUser);
			
			Cart cart = new Cart();
			
			cart.setUser(user);
			cartRepository.save(cart);

 		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		authorities.add(new SimpleGrantedAuthority(UserRole.ROLE_CUSTOMER.toString()));
		Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null,authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return jwtProvider.generateToken(authentication);
	}

	@Override
	public void sentLoginOtp(String email,UserRole role) throws Exception {
		// TODO Auto-generated method stub
		   String SIGNING_PREFIX = "signing_";

	        if (email.startsWith(SIGNING_PREFIX)) {
	            email = email.substring(SIGNING_PREFIX.length());
	            userService.findUserByEmail(email);
	        }

	        VerificationCode isExist = verificationRepository
	                .findByEmail(email);

	    
		
	
		
		if(isExist != null) {
			verificationRepository.delete(isExist);
		}
		
		String otp = OtpUtils.generateOtp();
		
		VerificationCode verificationCode = new VerificationCode();
		verificationCode.setOtp(otp);
		verificationCode.setEmail(email);
		verificationRepository.save(verificationCode);
		
		String subject = "Prime Games Store: OTP for Login/Register";
		String text = "Dear User,<br><br>" +
              "Your OTP for logging in or registering is:<br><br>" +
              "<span style=\"font-size: 24px; font-weight: bold;\">" + otp + "</span><br><br>" +
              "If you didn’t request this, please ignore this message.<br><br>" +
              "Best regards,<br>" +
              "Prime Games Store Team";


		
			  emailService.sendVerificationOtpEmail(email, otp, subject, text, true);
	}

	@Override
	public AuthResponse login(LoginRequest req) throws Exception {
		// TODO Auto-generated method stub
		
		String username= req.getEmail();
		String otp = req.getOtp();
		
		Authentication authentication = authenticate(username,otp);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
				
		AuthResponse authResponse = new AuthResponse();
		
		authResponse.setJwt(token);
		authResponse.setMessage("Login Success");
		
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String roleName = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
		
		authResponse.setRole(UserRole.valueOf(roleName));
		return authResponse;

}

	 private Authentication authenticate(String username, String otp) throws SellerException {
	        UserDetails userDetails = customUserServiceImpl.loadUserByUsername(username);

	        System.out.println("sign in userDetails - " + userDetails);

	        if (userDetails == null) {
	            System.out.println("sign in userDetails - null ");
	            throw new BadCredentialsException("Invalid username or password");
	        }
	        VerificationCode verificationCode = verificationRepository.findByEmail(username);

	        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
	            throw new SellerException("wrong otp...");
	        }
	        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	    }
}
