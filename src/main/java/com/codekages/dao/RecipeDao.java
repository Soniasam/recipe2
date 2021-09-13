package com.codekages.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.model.Ingredient;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;
import com.codekages.model.User;

@Repository
public class RecipeDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	public Recipe addRecipe(AddRecipeDTO addRecipeDto, User user) {
		Session session = sessionFactory.getCurrentSession();

		Recipe newRecipe = new Recipe(addRecipeDto.getName(),addRecipeDto.getDescription(), user);

		session.persist(newRecipe);

		return newRecipe;
	}

	@Transactional
	public double[] getCostsForRecipes(List<Recipe> recipes) {
		Session session = sessionFactory.getCurrentSession();

		List<RecipeIngredient> RecipeIngredients = session.createQuery("FROM RecipeIngredient i").getResultList();
		
		double[] costs = new double[recipes.size()];

		for(int i = 0; i < recipes.size(); i++) {
			double total = 0;

			for(RecipeIngredient ri: RecipeIngredients) {
				if(ri.getRecipe().equals(recipes.get(i)))
					total += ri.getIngredientQuantity() * ri.getIngredient().getCost();
			}
			
			costs[i] = total;

		}

		return costs;
	}

	@Transactional
	public List<Recipe> getAllRecipes() {
		Session session = sessionFactory.getCurrentSession();

		List<Recipe> recipes = session.createQuery("FROM Recipe r").getResultList();

		return recipes;
	} 


}
