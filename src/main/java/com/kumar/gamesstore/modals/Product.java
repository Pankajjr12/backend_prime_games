package com.kumar.gamesstore.modals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "product")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	

	private String title;
	

	private String description;
	
	private int sellingPrice;
	
	private int mrpPrice;
	
	private int discountPercent;
	
	private int quantity;

	private String brand;

	private String platform;
	
	@ElementCollection
	private List<String> images =  new ArrayList<>();
	
	private double numRatings;
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private Seller seller;
	
	private LocalDateTime createdAt;
	
	private String years;
	
	@OneToMany(mappedBy = "product" , cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Review> reviews = new ArrayList<>();
	
	 private boolean in_stock = true;
	
}
