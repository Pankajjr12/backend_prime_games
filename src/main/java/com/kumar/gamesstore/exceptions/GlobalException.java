package com.kumar.gamesstore.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {

	@ExceptionHandler(SellerException.class)
	public ResponseEntity<ErrorDetails> sellerExceptionHandler(SellerException se,WebRequest wb){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setError(se.getMessage());
		errorDetails.setDetails(wb.getDescription(false));
		errorDetails.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<ErrorDetails> productExceptionHandler(ProductException se,WebRequest wb){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setError(se.getMessage());
		errorDetails.setDetails(wb.getDescription(false));
		errorDetails.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
		
	}
}
