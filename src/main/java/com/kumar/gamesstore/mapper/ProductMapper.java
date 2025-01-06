package com.kumar.gamesstore.mapper;

import com.kumar.gamesstore.dto.ProductDto;
import com.kumar.gamesstore.modals.Product;

public class ProductMapper {

    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setMrpPrice(product.getMrpPrice());
        productDto.setSellingPrice(product.getSellingPrice());
        productDto.setDiscountPercent(product.getDiscountPercent());
        productDto.setQuantity(product.getQuantity());
        productDto.setBrand(product.getBrand());
        productDto.setImages(product.getImages());
        productDto.setNumRatings((int) product.getNumRatings());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setYears(product.getYears());

        return productDto;
    }
    public Product mapToEntity(ProductDto productDto) {
        return null;
    }
}
