package com.kumar.gamesstore.dto;

import lombok.Data;

@Data
public class OrderItemDto {

    private Long id;

    private ProductDto product;

    private String year;

    private int quantity;

    private Integer mrpPrice;

    private Integer sellingPrice;

    private Long userId;

}