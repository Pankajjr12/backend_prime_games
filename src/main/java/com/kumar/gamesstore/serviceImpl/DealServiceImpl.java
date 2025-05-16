package com.kumar.gamesstore.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kumar.gamesstore.modals.Deal;
import com.kumar.gamesstore.modals.HomeCategory;
import com.kumar.gamesstore.repositories.DealRepository;
import com.kumar.gamesstore.repositories.HomeCategoryRepository;
import com.kumar.gamesstore.services.DealService;

@Service
public class DealServiceImpl implements DealService {

    private final DealRepository dealRepository;
    private final HomeCategoryRepository homeCategoryRepository;

    public DealServiceImpl(DealRepository dealRepository, HomeCategoryRepository homeCategoryRepository) {
        this.dealRepository = dealRepository;
        this.homeCategoryRepository = homeCategoryRepository;
    }

    @Override
    public Deal createDeal(Deal deal) {
        HomeCategory category = deal.getHomeCategory().getId() != null
                ? homeCategoryRepository.findById(deal.getHomeCategory().getId()).orElse(null)
                : null;

        if (category == null) {
            // If HomeCategory is not found, create a new one
            category = homeCategoryRepository.save(deal.getHomeCategory());
        }

        // Create a new Deal and set the HomeCategory and discount
        Deal newDeal = new Deal();
        newDeal.setHomeCategory(category);
        newDeal.setDiscount(deal.getDiscount());

        // Save and return the Deal
        return dealRepository.save(newDeal);
    }

//
//    @Override
//    public List<Deal> createDeals(List<Deal> deals) {
//        if(dealRepository.findAll().isEmpty()){
//            return dealRepository.saveAll(deals);
//        }
//        else return dealRepository.findAll();
//
//    }
    @Override
    public List<Deal> getDeals() {
        return dealRepository.findAll();
    }

    @Override
    public Deal updateDeal(Deal deal, Long id) throws Exception {
        Deal existingDeal = dealRepository.findById(id).orElse(null);
        HomeCategory category = homeCategoryRepository.findById(deal.getHomeCategory().getId()).orElse(null);

        if (existingDeal != null) {
            if (deal.getDiscount() != null) {
                existingDeal.setDiscount(deal.getDiscount());
            }
            if (category != null) {
                existingDeal.setHomeCategory(category);
            }
            return dealRepository.save(existingDeal);
        }
        throw new Exception("Deal not found");
    }

    @Override
    public void deleteDeal(Long id) throws Exception {
        Deal deal = dealRepository.findById(id).orElse(null);

        if (deal != null) {

            dealRepository.delete(deal);
        }

    }

}
