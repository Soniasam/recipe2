package com.codekages.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codekages.dto.AddRecipeIngredientDTO;
import com.codekages.model.Ingredient;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;

@Repository
public class RecipeIngredientDao {

	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	public RecipeIngredient addRecipeIngredient(AddRecipeIngredientDTO addRecipeIngredientDto, Recipe recipe, Ingredient ingredient) {
		Session session = sessionFactory.getCurrentSession();

		RecipeIngredient newRI = new RecipeIngredient(addRecipeIngredientDto.getQuantity(), recipe, ingredient);

		session.persist(newRI);

		return newRI;
	}
	
	@Transactional
	public List<RecipeIngredient> getIngredientsForRecipe(int id) {
		Session session = sessionFactory.getCurrentSession();
		
		List<RecipeIngredient> ingredients = session.createQuery("Select i FROM RecipeIngredient i Join i.recipe r WHERE r.id = :recipeid").setParameter("recipeid", id).getResultList();
		
		return ingredients;
	}

}