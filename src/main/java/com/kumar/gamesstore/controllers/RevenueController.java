package com.kumar.gamesstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.dto.ReviewChart;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.services.RevenueService;
import com.kumar.gamesstore.services.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller/revenue/chart")
public class RevenueController {
    private final RevenueService revenueService;
    private final SellerService sellerService;

    @GetMapping()
    public ResponseEntity<List<ReviewChart>> getRevenueChart(
            @RequestParam(defaultValue = "today") String type,
            @RequestHeader("Authorization") String jwt) throws SellerException {
        Seller seller = sellerService.getSellerProfile(jwt);
        List<ReviewChart> revenue = revenueService
                .getRevenueChartByType(type, seller.getId());
        return ResponseEntity.ok(revenue);
    }

}
