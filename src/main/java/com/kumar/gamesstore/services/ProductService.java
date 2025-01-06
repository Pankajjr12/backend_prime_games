package com.kumar.gamesstore.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.requests.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req,Seller seller); 
	
	public void deleteProduct(Long productId) throws ProductException;
	
	public Product updateProduct(Long productId,Product product);
	
	Product findProductById(Long productId) throws ProductException;
	public Product updateProductStock(Long productId)throws ProductException;
	
	public List<Product> searchProduct(String query);

	public Page<Product> getAllProducts(String category,String brand, 
			String years,Integer minPrice,Integer maxPrice,
			Integer minDiscount ,String sort,String stock,Integer pageNumber);
	
	List<Product> getProductBySellerId(Long sellerId);
}

