package com.codekages.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RecipeIngredients")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString

public class RecipeIngredient {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int riID;

	@Column(name = "riQuantity")
	private int ingredientQuantity;
	
	@ManyToOne
	@JoinColumn(name = "riRecipeId")
	private Recipe recipe;

	@ManyToOne
	@JoinColumn(name = "riIngredientId")
	private Ingredient ingredient;

	public RecipeIngredient(int quantity, Recipe recipe, Ingredient ingredient) {
		super();
		this.ingredientQuantity = quantity;
		this.recipe = recipe;
		this.ingredient = ingredient;
	}

}
