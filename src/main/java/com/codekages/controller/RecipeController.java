package com.codekages.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.dto.AddRecipeIngredientDTO;
import com.codekages.dto.MessageDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.IngredientNotFoundException;
import com.codekages.exception.RecipeNotFoundException;
import com.codekages.model.Ingredient;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;
import com.codekages.model.User;
import com.codekages.service.IngredientService;
import com.codekages.service.RecipeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class RecipeController { // THIS IS THE LAYER FOR THE MAPPINGS

	@Autowired
	public RecipeService recipeService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	public IngredientService ingredientService;



	@PostMapping(path = "/recipe", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addRecipe(@RequestBody AddRecipeDTO addRecipeDTO) {

		try {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("currentUser") == null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
			}

			User user = (User) session.getAttribute("currentUser");

			Recipe recipe = recipeService.addRecipe(addRecipeDTO, user);

			return ResponseEntity.status(201).body(recipe);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));

		}

	}

	@PostMapping(path = "/recipe/{id}/ingredient/{ingredientId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addRecipeIngredient(@PathVariable("id") int id, @PathVariable("ingredientId") int ingredientId, @RequestBody AddRecipeIngredientDTO addRecipeIngredientDto) {

		Recipe recipe;
		Ingredient ingredient;

		try {

			List<Recipe> recipes = recipeService.getAllRecipes();
			recipe = recipes.get(id-1);

		} catch(RecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		}

		try {

			List<Ingredient> ingredients = ingredientService.getAllIngredients();
			ingredient = ingredients.get(ingredientId-1);

		}catch (IngredientNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));

		}		

		try {
			RecipeIngredient recipeIngredient = recipeService.addRecipeIngredient(addRecipeIngredientDto, recipe, ingredient);

			return ResponseEntity.status(201).body(recipeIngredient);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));

		}

	}

	@GetMapping(path = "/recipes/cost")
	public ResponseEntity<Object> getCostsForRecipes() {

		try {
			List<Recipe> recipes = recipeService.getAllRecipes();
			
			double[] costs = recipeService.getCostsForRecipes(recipes);

			return ResponseEntity.status(200).body(costs);
		} catch(RecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		}
	}
	
//	@GetMapping(path = "/recipes/cost/list/{listid}")
//	public ResponseEntity<Object> getCostsForRecipeLists(@PathVariable("listid") int id) {
//
//		try {
//			List<Recipe> recipes = recipeService.getAllRecipes();
//			
//			List<Recipe> recipesList = null;
//			
//			
//			
//			double[] costs = recipeService.getCostsForRecipes(recipes);
//
//			return ResponseEntity.status(200).body(costs);
//		} catch(RecipeNotFoundException e) {
//			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
//		}
//	}
	
	@GetMapping(path = "/ingredients/recipe/{recipeid}", produces = "application/json")
	public ResponseEntity<Object> getIngredientsForRecipe(@PathVariable("recipeid") int id) {
		try {
			
			List<RecipeIngredient> ingredients = recipeService.getIngredientsForRecipe(id);
			
			return ResponseEntity.status(200).body(ingredients);
		}catch (IngredientNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
			
		}
	}

	@GetMapping(path = "/recipes")
	public ResponseEntity<Object> getAllrecipes() {
		try {

			List<Recipe> recipes = recipeService.getAllRecipes();

			return ResponseEntity.status(200).body(recipes);
		} catch(RecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		}
	}


}
