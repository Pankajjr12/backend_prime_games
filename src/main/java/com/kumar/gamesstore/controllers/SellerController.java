package com.kumar.gamesstore.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.kumar.gamesstore.config.JwtProvider;
import com.kumar.gamesstore.domain.AccountStatus;
import com.kumar.gamesstore.domain.UserRole;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.modals.SellerReport;
import com.kumar.gamesstore.modals.VerificationCode;
import com.kumar.gamesstore.repositories.SellerRepository;
import com.kumar.gamesstore.repositories.VerificationRepository;
import com.kumar.gamesstore.requests.LoginOtpRequest;
import com.kumar.gamesstore.requests.LoginRequest;
import com.kumar.gamesstore.requests.SignUpRequest;
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.responses.AuthResponse;
import com.kumar.gamesstore.serviceImpl.CustomUserServiceImpl;
import com.kumar.gamesstore.services.AuthService;
import com.kumar.gamesstore.services.EmailService;
import com.kumar.gamesstore.services.SellerReportService;
import com.kumar.gamesstore.services.SellerService;
import com.kumar.gamesstore.services.VerificationService;
import com.kumar.gamesstore.utils.OtpUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private final AuthService authService;

    @Autowired
    private final SellerService sellerService;

    @Autowired
    private final VerificationService verificationService;

    @Autowired
    private final EmailService emailService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private final CustomUserServiceImpl customeUserServiceImplementation;

    private SellerReportService sellerReportService;

    @Autowired
    private final VerificationRepository verificationRepository;

    @Autowired
    private final SellerRepository sellerRepository;

    @PostMapping("/sent/login-top")
    public ResponseEntity<ApiResponse> sentLoginOtp(@RequestBody VerificationCode req) throws Exception {
        Seller seller = sellerService.getSellerByEmail(req.getEmail());

        String otp = OtpUtils.generateOtp();
        VerificationCode verificationCode = verificationService.createVerificationCode(otp, req.getEmail());

        String subject = "Prime Game Store";
        String text = "your login otp is - ";
        text += "otp=" + otp;
        emailService.sendVerificationOtpEmail(req.getEmail(), verificationCode.getOtp(), subject, text, true);

        ApiResponse res = new ApiResponse();
        res.setMessage("otp sent");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/verify/login-top")
    public ResponseEntity<AuthResponse> verifyLoginOtp(@RequestBody VerificationCode req) throws SellerException {
//        Seller savedSeller = sellerService.createSeller(seller);

        String otp = req.getOtp();
        String email = req.getEmail();
        VerificationCode verificationCode = verificationRepository.findByEmail(email);

        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new SellerException("wrong otp...");
        }

        Authentication authentication = authenticate(req.getEmail());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();

        authResponse.setMessage("Login Success");
        authResponse.setJwt(token);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        authResponse.setRole(UserRole.valueOf(roleName));

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username) {
        UserDetails userDetails = customeUserServiceImplementation.loadUserByUsername("seller_" + username);

        System.out.println("sign in userDetails - " + userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {

        VerificationCode verificationCode = verificationRepository.findByOtp(otp);
        if (verificationCode == null && !verificationCode.getOtp().equals(otp)) {
            throw new Exception("wrong otp..");
        }

        Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception {
        // Create and save the new seller
        Seller savedUser = sellerService.createSeller(seller);

        // Generate OTP and save the verification code
        String otpString = OtpUtils.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otpString);
        verificationCode.setEmail(seller.getEmail());

        verificationRepository.save(verificationCode);

        // Set up email parameters
        String subject = "Prime Games Hub || Email Verification Code";
        String text = "Welcome to Prime Games Hub! Please verify your account using this link: ";
        String frontendUrls = "http://localhost:3000/verify-seller";  // Frontend URL for email verification

        // Append the verification URL to the email body
        text += frontendUrls + "?otp=" + otpString;  // Add the OTP as a query parameter for verification

        // Send the email with the correct recipient
        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, text, true);

        // Return the created seller as the response
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {

        Seller seller = sellerService.getSellerById(id);
        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {

        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);

        return new ResponseEntity<>(seller, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Seller>> getAllSellers(@RequestParam(required = false) AccountStatus status) {
        List<Seller> sellers = sellerService.getAllSellers(status);
        return ResponseEntity.ok(sellers);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(
            @RequestHeader("Authorization") String jwt) throws SellerException {
        System.out.println("Received JWT: " + jwt);
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        Seller seller = sellerService.getSellerByEmail(email);
        if (seller == null) {
            System.out.println("Seller not found for email: " + email);
        }
        SellerReport report = sellerReportService.getSellerReport(seller);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @PatchMapping("/profile")
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt, @RequestBody Seller seller) throws Exception {

        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updateSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updateSeller);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }

}
