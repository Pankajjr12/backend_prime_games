package com.kumar.gamesstore.modals;

import com.kumar.gamesstore.domain.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

	private String paymentId;
	private String razorPaymentLinkId;
	private String  razorPaymentLinkReferenceId;
	private String  razorPaymentLinkStatus;
	private String  razorPaymentId;
	
	private PaymentStatus status;
	
	
}
