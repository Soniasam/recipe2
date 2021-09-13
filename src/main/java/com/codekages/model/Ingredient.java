package com.codekages.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// The letter 'i' that is in front of the variable stands for "ingredient". 
// For Example: "iName" stand for ingredientName.

@Entity
@Table(name = "Ingredients")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class Ingredient {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column(name = "ingredient_name")
	private String name;
	
	@Column(name = "iCost")
	private double cost;

	public Ingredient(String name, double cost) {
		super();
		this.name = name;
		this.cost = cost;
	}
		
}
