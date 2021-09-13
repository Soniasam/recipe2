package com.codekages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codekages.dao.RecipeDao;
import com.codekages.dao.RecipeIngredientDao;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.dto.AddRecipeIngredientDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.IngredientNotFoundException;
import com.codekages.exception.RecipeNotFoundException;
import com.codekages.model.Ingredient;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;
import com.codekages.model.User;

@Service
public class RecipeService {

	private RecipeDao recipeDao;
	private RecipeIngredientDao recipeIngredientDao;

	
//	public RecipeService(RecipeDao recipeDao) {
//		this.recipeDao = recipeDao;
//	}
	
	@Autowired
	public RecipeService(RecipeIngredientDao recipeIngredientDao, RecipeDao recipeDao) {
		this.recipeIngredientDao = recipeIngredientDao;
		this.recipeDao = recipeDao;
	}

	public Recipe addRecipe(AddRecipeDTO addRecipeDto, User user) throws BadParameterException {
		if (addRecipeDto.getName().trim().equals("")) {
			throw new BadParameterException("You can not have a blank name for recipe");
		}

		if (addRecipeDto.getDescription().trim().equals("")) {
			throw new BadParameterException("You can not have a blank description");
		}

		Recipe recipe = recipeDao.addRecipe(addRecipeDto, user);

		return recipe;
	}

	public RecipeIngredient addRecipeIngredient(AddRecipeIngredientDTO addRecipeIngredientDto, Recipe recipe, Ingredient ingredient) throws BadParameterException {
//		if (addRecipeIngredientDto.getName().trim().equals("")) {
//			throw new BadParameterException("You can not have a blank name for recipe");
//		}
//
//		if (addRecipeIngredientDto.getType().trim().equals("")) {
//			throw new BadParameterException("You can not have a blank recipe type");
//		}
//
//		if (addRecipeIngredientDto.getDescription().trim().equals("")) {
//			throw new BadParameterException("You can not have a blank description");
//		}

		RecipeIngredient addedRecipeIngredient = recipeIngredientDao.addRecipeIngredient(addRecipeIngredientDto, recipe, ingredient);

		return addedRecipeIngredient;
	}
	
	public double[] getCostsForRecipes(List<Recipe> recipes) {
		
		double[] costs = recipeDao.getCostsForRecipes(recipes);
		
		return costs;
	}
	
	public List<RecipeIngredient> getIngredientsForRecipe(int id) throws IngredientNotFoundException {

		List<RecipeIngredient> ingredients = recipeIngredientDao.getIngredientsForRecipe(id);
		
		if (ingredients.size() == 0) {
			throw new IngredientNotFoundException("No ingredients were found in the system");
		}

		return ingredients;
		}
	
	public List<Recipe> getAllRecipes() throws RecipeNotFoundException {
		List<Recipe> recipes = recipeDao.getAllRecipes();
		
		if (recipes.size() == 0) {
			throw new RecipeNotFoundException("No recipes were found in the system");
		}
		
		return recipes;
	}
	
}
