package com.codekages.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codekages.dto.AddListOfRecipeDTO;
import com.codekages.dto.ListOfRecipeDTO;
import com.codekages.dto.MessageDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.ListOfRecipeNotFoundException;
import com.codekages.exception.RecipeNotFoundException;
import com.codekages.model.ListOfRecipe;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeList;
import com.codekages.model.User;
import com.codekages.service.ListOfRecipeService;
import com.codekages.service.RecipeService;
import com.codekages.service.AuthorizationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class ListOfRecipeController {

	@Autowired
	private ListOfRecipeService ListOfRecipeService;
		
	
	@Autowired
	public RecipeService recipeService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping(path = "/listofrecipe", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addListOfRecipe(@RequestBody AddListOfRecipeDTO addListOfRecipeDTO) {

		try {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("currentUser") == null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
			}

			User user = (User) session.getAttribute("currentUser");

			ListOfRecipe listofrecipe = ListOfRecipeService.addListOfRecipe(addListOfRecipeDTO, user);

			return ResponseEntity.status(201).body(listofrecipe);

		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}

	@GetMapping(path = "/listofrecipe", produces = "application/json")
	public ResponseEntity<Object> getAllListOfRecipe() {

		try {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("currentUser") == null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
			}

			User user = (User) session.getAttribute("currentUser");
			
			List<ListOfRecipe> listofrecipe = ListOfRecipeService.getAllListOfRecipeForUser(user);
			return ResponseEntity.status(200).body(listofrecipe);

		} catch (ListOfRecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		}
	}

	@PatchMapping(path = "/listofrecipe/{id}/recipe/{recipeId}", produces = "application/json")
	public ResponseEntity<Object> editListOfRecipe(@PathVariable("id") int id, @PathVariable("recipeId") int recipeId) {

		try {

			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("currentUser") == null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
			}
			
			List<ListOfRecipe> listOfRecipe = ListOfRecipeService.getAllListOfRecipe();
			
			ListOfRecipe list = listOfRecipe.get(id - 1);
			
			List<Recipe> recipes = recipeService.getAllRecipes();
			Recipe recipe = recipes.get(recipeId-1);

			RecipeList editedList = ListOfRecipeService.editListOfRecipe(list, recipe);

			return ResponseEntity.status(200).body(editedList);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));

		} catch (ListOfRecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		} catch(RecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		}

	}
	
	@GetMapping(path = "/listofrecipe/{id}/recipes", produces = "application/json")
	public ResponseEntity<Object> getAllRecipesInList(@PathVariable("id") int id) {

		try {
			HttpSession session = request.getSession(false);

			if (session == null || session.getAttribute("currentUser") == null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
			}
			
			List<RecipeList> recipesInList = ListOfRecipeService.getAllRecipesInList(id);
			
			return ResponseEntity.status(200).body(recipesInList);

		} catch (ListOfRecipeNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
		}
	}

}
