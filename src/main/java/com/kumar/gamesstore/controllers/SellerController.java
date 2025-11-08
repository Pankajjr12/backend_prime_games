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
import com.kumar.gamesstore.responses.ApiResponse;
import com.kumar.gamesstore.responses.AuthResponse;
import com.kumar.gamesstore.serviceImpl.CustomUserServiceImpl;
import com.kumar.gamesstore.services.AuthService;
import com.kumar.gamesstore.services.EmailService;
import com.kumar.gamesstore.services.SellerReportService;
import com.kumar.gamesstore.services.SellerService;
import com.kumar.gamesstore.services.VerificationService;
import com.kumar.gamesstore.utils.OtpUtils;

@RestController

@RequestMapping("/sellers")
public class SellerController {

    private final SellerService sellerService;
    private final VerificationService verificationService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;
    private final CustomUserServiceImpl customeUserServiceImplementation;
    private final VerificationRepository verificationRepository;

    private SellerReportService sellerReportService;

    // ✅ Constructor for dependency injection
    @Autowired
    public SellerController(
            AuthService authService,
            SellerService sellerService,
            VerificationService verificationService,
            EmailService emailService,
            JwtProvider jwtProvider,
            CustomUserServiceImpl customeUserServiceImplementation,
            VerificationRepository verificationRepository,
            SellerRepository sellerRepository) {

        this.sellerService = sellerService;
        this.verificationService = verificationService;
        this.emailService = emailService;
        this.jwtProvider = jwtProvider;
        this.customeUserServiceImplementation = customeUserServiceImplementation;
        this.verificationRepository = verificationRepository;
    }

    @PostMapping("/sent/login-top")
    public ResponseEntity<ApiResponse> sendLoginOtp(@RequestBody VerificationCode req) throws Exception {
        // Fetch seller by email
        Seller seller = sellerService.getSellerByEmail(req.getEmail());

        // Generate OTP
        String otp = OtpUtils.generateOtp();

        // Save OTP in verification table
        VerificationCode verificationCode = verificationService.createVerificationCode(otp, req.getEmail());

        // Email details
        String subject = "Your Login OTP – Panku Game Store";

        String text = """
                <html>
                <body style="font-family: Arial, sans-serif; font-size: 14px; color: #333;">
                    <p>Hi <strong>%s</strong>,</p>
                    
                    <p>Your One-Time Password (OTP) to log in to your <strong>Panku Game Store</strong> account is:</p>
                    
                    <p style="font-size: 18px; font-weight: bold; background: #f3f3f3; padding: 10px; border-radius: 6px; display: inline-block;">
                        🔐 %s
                    </p>
                    
                    <p style="margin-top: 15px;">
                        Please <strong>do not share</strong> this OTP with anyone for security reasons.<br>
                        This OTP will expire shortly.
                    </p>
                    
                    <br>
                    <p>Regards,<br>
                    <strong>Panku Game Store Team</strong></p>
                </body>
                </html>
                """.formatted(seller.getSellerName(), otp);

        // Send email
        emailService.sendVerificationOtpEmail(req.getEmail(), verificationCode.getOtp(), subject, text, true);

        // Prepare API response
        ApiResponse res = new ApiResponse();
        res.setMessage("OTP sent successfully to " + req.getEmail());

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
        String frontendUrls = "https://frontend-prime-games.vercel.app/verify-seller";  // Frontend URL for email verification

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
