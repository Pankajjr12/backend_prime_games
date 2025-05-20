package com.kumar.gamesstore.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kumar.gamesstore.exceptions.ProductException;
import com.kumar.gamesstore.modals.Category;
import com.kumar.gamesstore.modals.Product;
import com.kumar.gamesstore.modals.Seller;
import com.kumar.gamesstore.repositories.CategoryRepository;
import com.kumar.gamesstore.repositories.ProductRepository;
import com.kumar.gamesstore.requests.CreateProductRequest;
import com.kumar.gamesstore.services.ProductService;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @CacheEvict(value = {"products", "allProducts", "searchProducts"}, allEntries = true)
    @Override
    public Product createProduct(CreateProductRequest req, Seller seller) {
        // TODO Auto-generated method stub
        int discountPercentage = calculateDiscountPercentage(req.getMrpPrice(), req.getSellingPrice());

        Category category1 = categoryRepository.findByCategoryId(req.getCategory());

        if (category1 == null) {
            Category category = new Category();
            category.setCategoryId(req.getCategory());
            category.setLevel(1);
            category.setName(req.getCategory().replace("_", " "));
            category1 = categoryRepository.save(category);
        }

        Category category2 = categoryRepository.findByCategoryId(req.getCategory2());

        if (category2 == null) {
            Category category = new Category();
            category.setCategoryId(req.getCategory2());
            category.setLevel(2);
            category.setParentCategory(category1);
            category.setName(req.getCategory2().replace("_", " "));
            category2 = categoryRepository.save(category);
        }

        Category category3 = categoryRepository.findByCategoryId(req.getCategory3());
        if (category3 == null) {
            Category category = new Category();
            category.setCategoryId(req.getCategory3());
            category.setLevel(3);
            category.setParentCategory(category2);
            category.setName(req.getCategory3().replace("_", " "));
            category3 = categoryRepository.save(category);
        }

        Product product = new Product();
        product.setSeller(seller);
        product.setCategory(category3);
        product.setDescription(req.getDescription());
        product.setCreatedAt(LocalDateTime.now());
        product.setTitle(req.getTitle());
        product.setYears(req.getYears());
        product.setSellingPrice(req.getSellingPrice());
        product.setImages(req.getImages());
        product.setNumRatings(req.getNumRatings());
        product.setPlatform(req.getPlatform());
        product.setMrpPrice(req.getMrpPrice());
        product.setBrand(req.getBrand());
        product.setDiscountPercent(discountPercentage);

        return productRepository.save(product);
    }

    public static int calculateDiscountPercentage(double mrpPrice, double sellingPrice) {
        if (mrpPrice <= 0) {
            throw new IllegalArgumentException("Actual price must be greater than zero.");
        }
        double discount = mrpPrice - sellingPrice;
        double discountPercentage = (discount / mrpPrice) * 100;
        return (int) discountPercentage;
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long productId) throws ProductException {
        // TODO Auto-generated method stub
        Product product = findProductById(productId);
        productRepository.delete(product);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product updateProduct(Long productId, Product product) {
        // TODO Auto-generated method stub
        productRepository.findById(productId);
        product.setId(productId);
        return productRepository.save(product);
    }

    @Cacheable(value = "products", key = "#productId")
    @Override
    public Product findProductById(Long productId) throws ProductException {
        // TODO Auto-generated method stub
        return productRepository.findById(productId)
                .orElseThrow(() -> new ProductException("product not found"));
    }

    @Cacheable(
            value = "allProducts",
            key = "T(java.util.Objects).hash(#category, #brand, #platform, #years, #minPrice, #maxPrice, #minDiscount, #sort, #stock, #pageNumber)"
    )
    @Override
    public Page<Product> getAllProducts(String category, String brand, String platform, String years, Integer minPrice, Integer maxPrice,
            Integer minDiscount, String sort, String stock, Integer pageNumber) {
        // TODO Auto-generated method stub
        Specification<Product> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (category != null) {
                Join<Product, Category> categoryJoin = root.join("category");
//                predicates.add(criteriaBuilder.equal(categoryJoin.get("categoryId"), category));
//                predicates.add(criteriaBuilder.equal(categoryJoin.get("parentCategory").get("categoryId"), category));
                Predicate categoryPredicate = criteriaBuilder.or(
                        criteriaBuilder.equal(categoryJoin.get("categoryId"), category), // Match categoryId
                        criteriaBuilder.equal(categoryJoin.get("parentCategory").get("categoryId"), category) // Match parentCategory.categoryId
                );

                predicates.add(categoryPredicate);
            }

            if (years != null && !years.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("years"), years));
            }

            if (platform != null && !platform.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("platform"), platform));
            }

            if (brand != null && !brand.isEmpty()) {
                System.out.println("color " + brand);
                predicates.add(criteriaBuilder.equal(root.get("brand"), brand));
            }

            if (minPrice != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("sellingPrice"),
                        minPrice));
            }

            if (maxPrice != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("sellingPrice"),
                        maxPrice));
            }

            if (minDiscount != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("discountPercent"),
                        minDiscount));
            }

            if (stock != null) {
                predicates.add(criteriaBuilder.equal(root.get("stock"), stock));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };

        Pageable pageable;
        if (sort != null && !sort.isEmpty()) {
            pageable = switch (sort) {
                case "price_low" ->
                    PageRequest.of(pageNumber != null ? pageNumber : 0, 8, Sort.by("sellingPrice").ascending());
                case "price_high" ->
                    PageRequest.of(pageNumber != null ? pageNumber : 0, 8, Sort.by("sellingPrice").descending());
                default ->
                    PageRequest.of(pageNumber != null ? pageNumber : 0, 8, Sort.unsorted());
            };
        } else {
            pageable = PageRequest.of(pageNumber != null ? pageNumber : 0, 5, Sort.unsorted());
        }
        return productRepository.findAll(spec, pageable);
    }

    @Override
    public List<Product> getProductBySellerId(Long sellerId) {
        return productRepository.findBySellerId(sellerId);
    }

    @Cacheable(value = "searchProducts", key = "#query", unless = "#query == null || #query.isEmpty()")
    @Override
    public List<Product> searchProduct(String query) {

        return productRepository.searchProduct(query);
    }

    @Override
    public Product updateProductStock(Long productId) throws ProductException {
        // TODO Auto-generated method stub
        Product product = this.findProductById(productId);
        product.setInStock(!product.isInStock());
        return productRepository.save(product);
    }

}
