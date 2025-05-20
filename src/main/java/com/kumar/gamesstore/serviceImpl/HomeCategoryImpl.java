package com.kumar.gamesstore.serviceImpl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.modals.HomeCategory;
import com.kumar.gamesstore.repositories.HomeCategoryRepository;
import com.kumar.gamesstore.services.HomeCategoryService;

@Service
public class HomeCategoryImpl implements HomeCategoryService {

    private final HomeCategoryRepository homeCategoryRepository;

    public HomeCategoryImpl(HomeCategoryRepository homeCategoryRepository) {
        this.homeCategoryRepository = homeCategoryRepository;
    }

    @Override
    @CacheEvict(value = "homeCategories", allEntries = true)
    public HomeCategory createCategory(HomeCategory category) {
        return homeCategoryRepository.save(category);
    }

    @Override
    @CacheEvict(value = "homeCategories", allEntries = true)
    public List<HomeCategory> createCategories(List<HomeCategory> categories) {
        if (homeCategoryRepository.findAll().isEmpty()) {
            return homeCategoryRepository.saveAll(categories);
        }
        return homeCategoryRepository.findAll();
    }

    @Override
    @Cacheable(value = "homeCategories")
    public List<HomeCategory> getAllCategories() {
        return homeCategoryRepository.findAll();
    }

    @Override
    @CacheEvict(value = "homeCategories", allEntries = true)
    public HomeCategory updateCategory(HomeCategory category, Long id) throws Exception {

        HomeCategory existingCategory = homeCategoryRepository.findById(id)
                .orElseThrow(() -> new Exception("Category not found"));
        if (category.getImage() != null) {
            existingCategory.setImage(category.getImage());
        }
        if (category.getCategoryId() != null) {
            existingCategory.setCategoryId(category.getCategoryId());
        }
        return homeCategoryRepository.save(existingCategory);
    }

}
