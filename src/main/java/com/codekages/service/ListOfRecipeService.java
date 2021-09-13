package com.codekages.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codekages.dao.ListOfRecipeDAO;
import com.codekages.dto.AddListOfRecipeDTO;
import com.codekages.dto.ListOfRecipeDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.ListOfRecipeNotFoundException;
import com.codekages.model.Ingredient;
import com.codekages.model.ListOfRecipe;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeList;
import com.codekages.model.User;

@Service
public class ListOfRecipeService {

	private ListOfRecipeDAO listofrecipeDAO;

	@Autowired 
	public ListOfRecipeService(ListOfRecipeDAO listofrecipeDAO) {
		this.listofrecipeDAO = listofrecipeDAO;
	}

	public ListOfRecipe addListOfRecipe(AddListOfRecipeDTO addListOfRecipeDTO, User user) throws BadParameterException {
		if(addListOfRecipeDTO.getListOfRecipe().trim().equals("")) {
			throw new BadParameterException("You cannot have a blank name for the Recipe");
		}

		ListOfRecipe listofrecipe = listofrecipeDAO.addListOfRecipe(addListOfRecipeDTO, user);
		return listofrecipe;
	}

	public List<ListOfRecipe>getAllListOfRecipe() throws ListOfRecipeNotFoundException{
		List<ListOfRecipe>listofrecipe =  listofrecipeDAO.getAllListOfRecipe();

		if (listofrecipe.size()==0) {
			throw new ListOfRecipeNotFoundException("No List of Recipes were found in the system");
		}
		return listofrecipe;
	}

	public List<ListOfRecipe>getAllListOfRecipeForUser(User user) throws ListOfRecipeNotFoundException{
		List<ListOfRecipe>listofrecipe =  listofrecipeDAO.getAllListOfRecipeForUser(user);

		if (listofrecipe.size()==0) {
			throw new ListOfRecipeNotFoundException("No List of Recipes were found in the system");
		}
		return listofrecipe;
	}
	
	public List<RecipeList>getAllRecipesInList(int id) throws ListOfRecipeNotFoundException{
		
		List<RecipeList> recipesInList = listofrecipeDAO.getAllRecipesInList(id);
		
		
		if (recipesInList.size()==0) {
			throw new ListOfRecipeNotFoundException("No List of Recipes were found in the system");
		}
		return recipesInList;
	}
	
	

	public RecipeList editListOfRecipe(ListOfRecipe list, Recipe recipe) throws BadParameterException {
		//Session session = sessionFactory.getCurrentSession();
		//		Ingredient editedIngredient = session.get(Ingredient.class, id);

		//		if(ingredientDto.getQuantity() < 0) {
		//			throw new BadParameterException("You can not have a negative quantity for ingredients");
		//		}
		//		
		//		if (ingredientDto.getCost() < 0) {
		//			throw new BadParameterException("You can not have a negative cost for ingredients");
		//		}

		RecipeList editedRecipeList = listofrecipeDAO.editListOfRecipe(list, recipe);

		return editedRecipeList;
	}
}
