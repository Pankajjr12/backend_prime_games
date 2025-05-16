package com.kumar.gamesstore.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumar.gamesstore.domain.AccountStatus;
import com.kumar.gamesstore.exceptions.SellerException;
import com.kumar.gamesstore.modals.HomeCategory;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.services.HomeCategoryService;
import com.kumar.gamesstore.services.SellerService;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private final SellerService sellerService;
    private final HomeCategoryService homeCategoryService;

    public AdminController(SellerService sellerService, HomeCategoryService homeCategoryService) {
        this.sellerService = sellerService;
        this.homeCategoryService = homeCategoryService;
    }

    @PatchMapping("/seller/{id}/status/{status}")
    public ResponseEntity<Seller> updateSellerStatus(
            @PathVariable Long id,
            @PathVariable AccountStatus status) throws SellerException {

        Seller updatedSeller = sellerService.updateSellerAccountStatus(id, status);
        return ResponseEntity.ok(updatedSeller);

    }

    @GetMapping("/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory() throws Exception {

        List<HomeCategory> categories = homeCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);

    }

    @PatchMapping("/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
            @PathVariable Long id,
            @RequestBody HomeCategory homeCategory) throws Exception {

        HomeCategory updatedCategory = homeCategoryService.updateCategory(homeCategory, id);
        return ResponseEntity.ok(updatedCategory);

    }
}
