package com.codekages.service;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import com.codekages.dao.IngredientDAO;
import com.codekages.dao.ListOfRecipeDAO;
import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.AddListOfRecipeDTO;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.model.Ingredient;
import com.codekages.model.ListOfRecipe;
import com.codekages.model.Recipe;
import com.codekages.model.User;

class ListOfRecipeServiceTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private ListOfRecipeDAO listofRecipeDao;

//	@Test
//	@Transactional
//	@Order(10)
//	@Commit
//	void testAddListOfRecipe_hasAutoGeneratedId() throws BadParameterException {
//		AddListOfRecipeDTO dto = new AddListOfRecipeDTO();
//		dto.setListOfRecipe("Tomatoes");
//				
//		User user = new User();
//		user.setFirstName("John");
//		user.setLastName("Lewis");
//		user.setUsername("user123");
//		user.setPassword("12345");
//		user.setId(1);
//		user.setUserRole(2);		
//
//		listofRecipeDao.addListOfRecipe(dto, user);
//		
//		ListOfRecipe listofrecipe = listofRecipeDao.addListOfRecipe(dto, user);
//		
//		int actual = 1;		
//		int expected = listofrecipe.getId();		
//		assertEquals(expected, actual);	
//		
//	}	

	
//	void testAddRecipe_hasAutoGeneratedId() throws BadParameterException, SQLException {
//		AddRecipeDTO dto = new AddRecipeDTO();
//		dto.setName("Southern Pizza");
//		dto.setDescription("The kind of pizza you can eat at breakfast, lunch or dinner");
//		
//		Recipe recipe = recipeService.addRecipe(dto, null);
//
//		int actual = 1;
//		int expected = recipe.getId();
//		assertEquals(expected, actual);
	
	
	
}
