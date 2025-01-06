package com.kumar.gamesstore.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.services.ProductService;
import com.kumar.gamesstore.services.SellerService;
import com.kumar.gamesstore.services.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	
    private final ProductService productService;

    private final UserService userService;

    private final SellerService sellerService;
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId) throws ProductException {
		Product product = productService.findProductById(productId);
	    return new ResponseEntity<>(product, HttpStatus.OK);
	}
	
	@GetMapping("/search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam(required = false) String query) {
        List<Product> products = productService.searchProduct(query);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
	

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam(required = false) String category,
                                                        @RequestParam(required = false) String brand,
                                                        @RequestParam(required = false) String color,
                                                        @RequestParam(required = false) String years,
                                                        @RequestParam(required = false) Integer minPrice,
                                                        @RequestParam(required = false) Integer maxPrice,
                                                        @RequestParam(required = false) Integer minDiscount,
                                                        @RequestParam(required = false) String sort,
                                                        @RequestParam(required = false) String stock,
                                                        @RequestParam(defaultValue = "0") Integer pageNumber) {
        System.out.println("color p -------- "+pageNumber);
        return new ResponseEntity<>(
                productService.getAllProducts(category,brand,
                        years, minPrice,
                        maxPrice, minDiscount, sort,
                        stock, pageNumber), HttpStatus.OK);
    }
	
	
}
