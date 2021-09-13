package com.codekages.dto;

import com.codekages.model.Ingredient;
import com.codekages.model.Recipe;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @EqualsAndHashCode @ToString @NoArgsConstructor
public class AddRecipeIngredientDTO {

	private int quantity;

}
