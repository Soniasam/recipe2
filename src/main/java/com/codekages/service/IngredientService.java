package com.codekages.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codekages.dao.IngredientDAO;
import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.IngredientDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.IngredientNotFoundException;
import com.codekages.model.Ingredient;
import com.codekages.model.RecipeIngredient;

@Service
public class IngredientService {

	private IngredientDAO ingredientDao;

	@Autowired
	public IngredientService(IngredientDAO ingredientDao) {
		this.ingredientDao = ingredientDao;
	}

	public Ingredient addIngredient(AddIngredientDTO addIngredientDto) throws BadParameterException {
		if (addIngredientDto.getName().trim().equals("")) {
			throw new BadParameterException("You can not have a blank name for ingredient");
		}

		if (addIngredientDto.getCost() < 0) {
			throw new BadParameterException("You can not have a negative cost for ingredients");
		}

		Ingredient ingredient = ingredientDao.addIngredient(addIngredientDto);

		return ingredient;

	}


	public Ingredient editIngredient(int id, IngredientDTO ingredientDto) throws BadParameterException {

		if (ingredientDto.getCost() < 0) {
			throw new BadParameterException("You can not have a negative cost for ingredients");
		}

		Ingredient ingredient = ingredientDao.editIngredient(id, ingredientDto);

		return ingredient;
	}

	public List<Ingredient> getAllIngredients() throws IngredientNotFoundException {
		List<Ingredient> ingredients = ingredientDao.getAllIngredients();

		if (ingredients.size() == 0) {
			throw new IngredientNotFoundException("No ingredients were found in the system");
		}

		return ingredients;
	}

}
