package com.kumar.gamesstore.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.domain.HomeCategorySection;
import com.kumar.gamesstore.modals.Deal;
import com.kumar.gamesstore.modals.Home;
import com.kumar.gamesstore.modals.HomeCategory;
import com.kumar.gamesstore.repositories.DealRepository;
import com.kumar.gamesstore.repositories.HomeCategoryRepository;
import com.kumar.gamesstore.services.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

    private final DealRepository dealRepository;

    private final HomeCategoryRepository homeCategoryRepository;

    public HomeServiceImpl(DealRepository dealRepository, HomeCategoryRepository homeCategoryRepository) {
        this.dealRepository = dealRepository;
        this.homeCategoryRepository = homeCategoryRepository;
    }

    @Override
    public Home creatHomePageData(List<HomeCategory> allCategories) {
        // Log the incoming categories
        System.out.println("All Categories Received: " + allCategories);
        Home home = new Home();

        // Filter categories based on section and map to the correct HomeCategorySection
        List<HomeCategory> gridCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.fromString("GRID"))
                .collect(Collectors.toList());
        System.out.println("Grid Categories: " + gridCategories);

        List<HomeCategory> shopByCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.fromString("SHOP_BY_CATEGORIES"))
                .collect(Collectors.toList());
        System.out.println("Shop By Categories: " + shopByCategories);

        List<HomeCategory> gameCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.fromString("PC_GAMES_CATEGORIES"))
                .collect(Collectors.toList());
        System.out.println("Game Categories: " + gameCategories);

        List<HomeCategory> dealCategories = allCategories.stream()
                .filter(category -> category.getSection() == HomeCategorySection.fromString("DEALS"))
                .collect(Collectors.toList());
        System.out.println("Deal Categories: " + dealCategories);

        List<Deal> createdDeals = new ArrayList<>();
        if (dealRepository.findAll().isEmpty()) {
            System.out.println("Creating new deals");
            List<Deal> deals = allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.fromString("DEALS"))
                    .map(category -> new Deal(null, 10, category)) // Assuming a discount of 10 for each deal
                    .collect(Collectors.toList());
            createdDeals = dealRepository.saveAll(deals);
            System.out.println("Created Deals: " + createdDeals);
        } else {
            createdDeals = dealRepository.findAll();
            System.out.println("Existing Deals: " + createdDeals);
        }

        // Set the categories and deals in the Home object
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setGameCategories(gameCategories);
        home.setDeals(createdDeals);
        home.setDealCategories(dealCategories);

        // Log the final Home object
        System.out.println("Home Object Created: " + home);
        return home;
    }

    @Override
    @Cacheable(value = "homepage", unless = "#result == null")
    public Home getHomePageData() {
        List<HomeCategory> allCategories = homeCategoryRepository.findAll();

        // Use the existing logic to create a Home object (assuming this logic exists)
        // This could include filtering categories by section, handling deals, etc.
        return creatHomePageData(allCategories);
    }

}
