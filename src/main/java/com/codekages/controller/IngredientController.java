package com.codekages.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.IngredientDTO;
import com.codekages.dto.MessageDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.IngredientNotFoundException;
import com.codekages.model.Ingredient;
import com.codekages.model.RecipeIngredient;
import com.codekages.service.IngredientService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class IngredientController { // THIS IS THE LAYER YOU DO YOUR MAPPINGS

	@Autowired
	public IngredientService ingredientService;

	@PostMapping(path = "/ingredient", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addIngredient(@RequestBody AddIngredientDTO addIngredientDTO) {

		try {
			Ingredient ingredient = ingredientService.addIngredient(addIngredientDTO);

			return ResponseEntity.status(201).body(ingredient);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));

		}

	}

	@PatchMapping(path = "/ingredient/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> editIngredient(@PathVariable("id") int id, @RequestBody IngredientDTO ingredientDto) {

		try {
			Ingredient editedIngredient = ingredientService.editIngredient(id, ingredientDto);

			return ResponseEntity.status(200).body(editedIngredient);
		} catch (BadParameterException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));

		}

	}
	
	@GetMapping(path = "/ingredients", produces = "application/json")
	public ResponseEntity<Object> getAllingredients() {
		try {
			
			List<Ingredient> ingredients = ingredientService.getAllIngredients();
			
			return ResponseEntity.status(200).body(ingredients);
		}catch (IngredientNotFoundException e) {
			return ResponseEntity.status(404).body(new MessageDTO(e.getMessage()));
			
		}
	}

	
}
