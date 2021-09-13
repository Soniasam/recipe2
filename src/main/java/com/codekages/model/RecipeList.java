package com.codekages.model;

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
@Table(name = "Recipe_And_List")
@Getter@Setter@NoArgsConstructor@EqualsAndHashCode@ToString
public class RecipeList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "listId")
	private ListOfRecipe list;
	
	@ManyToOne
	@JoinColumn(name = "recipeId")
	private Recipe recipe;
	
	public RecipeList(ListOfRecipe list, Recipe recipe) {
		super();
		this.list = list;
		this.recipe = recipe;
	}
	
}
