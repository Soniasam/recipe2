package com.codekages.dto;

import com.codekages.model.Recipe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString
public class ListOfRecipeDTO {

	private String listOfRecipe;
	private Recipe recipes;

}
