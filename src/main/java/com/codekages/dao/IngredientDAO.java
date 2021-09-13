package com.codekages.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.IngredientDTO;
import com.codekages.model.Ingredient;

@Repository
public class IngredientDAO {

	@Autowired
	public SessionFactory sessionFactory;

	@Transactional
	public Ingredient addIngredient(AddIngredientDTO addIngredientDto) {
		Session session = sessionFactory.getCurrentSession();

		Ingredient newIngredient = new Ingredient(addIngredientDto.getName(), addIngredientDto.getCost());

		session.persist(newIngredient);

		return newIngredient;
	}

	@Transactional
	public Ingredient editIngredient(int id, IngredientDTO ingredientDto) {
		Session session = sessionFactory.getCurrentSession();

		Ingredient editedIngredient = session.get(Ingredient.class, id);
		editedIngredient.setCost(ingredientDto.getCost());
		session.saveOrUpdate(editedIngredient);
		
		
		return editedIngredient;
	}
	
	@Transactional
	public List<Ingredient> getAllIngredients() {
		Session session = sessionFactory.getCurrentSession();
		
		List<Ingredient> ingredients = session.createQuery("FROM Ingredient i").getResultList();
		
		return ingredients;
	}	

}
