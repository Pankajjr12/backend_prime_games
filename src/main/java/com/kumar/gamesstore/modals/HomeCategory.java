package com.kumar.gamesstore.modals;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kumar.gamesstore.domain.HomeCategorySection;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class HomeCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String image;
	
	private String categoryId;
	
	private HomeCategorySection section;
		
	public HomeCategorySection getSection() {
			// TODO Auto-generated method stub
			return section;
		}
	
}
