package com.codekages.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import org.junit.Before;

import org.mockito.Mockito;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import com.codekages.dao.RecipeDao;
import com.codekages.dto.AddRecipeDTO;
import com.codekages.dto.AddRecipeIngredientDTO;
import com.codekages.exception.BadParameterException;
import com.codekages.exception.RecipeNotFoundException;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@PropertySource("classpath:springorm-test.properties")
public class RecipeServiceTest {

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
	private RecipeService recipeService;

	@Autowired
	private RecipeDao recipeDao;

	@Test
	@Transactional
	@Commit
	@Order(0)
	void testAddRecipe_hasAutoGeneratedId() throws BadParameterException, SQLException {
		AddRecipeDTO dto = new AddRecipeDTO();
		dto.setName("Southern Pizza");
		dto.setDescription("The kind of pizza you can eat at breakfast, lunch or dinner");
		
		Recipe recipe = recipeService.addRecipe(dto, null);

		int actual = 1;
		int expected = recipe.getId();
		assertEquals(expected, actual);
	}

	@Test()
	@Transactional
	@Order(1)
	// Testing AddRecipe with blank name
	void testAddRecipe_blankName() throws  SQLException {
		AddRecipeDTO dto = new AddRecipeDTO();
		dto.setName("");
		dto.setDescription("One of a kind of pizza");

		try {
			recipeService.addRecipe(dto, null);
			fail();
		} catch (BadParameterException e) {
			assertEquals("You can not have a blank name for recipe", e.getMessage());
		}

	}

	@Test()
	@Transactional
	@Order(2)
	// Testing AddRecipe with blank name with Space
	void testAddRecipe_blankNameWithSpaces() throws SQLException {
		AddRecipeDTO dto = new AddRecipeDTO();
		dto.setName("       ");
		dto.setDescription("One of a kind of pizza");

		try {
			recipeService.addRecipe(dto, null);
			fail();
		} catch (BadParameterException e) {
			assertEquals("You can not have a blank name for recipe", e.getMessage());
		}

	}

	// getAllRecipes Unit Testing
//	@Test
//	@Transactional
//	@Order(3)
//	public void test_getAllRecipes_positive()
//			throws SQLException, BadParameterException, RecipeNotFoundException {
////		List<Recipe> actual = recipeService.getAllRecipes();
////		
////		List<Recipe> expected = new ArrayList<>();
////		expected.add(new Recipe("Southern Pizza","The kind of pizza you can eat at breakfast, lunch or dinner", null));
////		assertEquals(expected, actual);
//		
//
//		List<Recipe> mockRecipes = new ArrayList<>();
//		mockRecipes.add(new Recipe("Mongolian chicken", "The classic Mongolian recipe gets a chicken twist", null));
//		mockRecipes.add(new Recipe("chicken pot pie", "Get this chicken pot pie on the table", null));
//		
//		when(recipeDao.getAllRecipes()).thenReturn(mockRecipes);
//		
//		List<Recipe> actualRecipes = recipeService.getAllRecipes();
//
//		System.out.println(actualRecipes);
//		
//		// expected = what we expect for the ships List to contain
//		List<Recipe> expected = new ArrayList<>();
//		Recipe s1 = new Recipe("Mongolian chicken", "The classic Mongolian recipe gets a chicken twist", null);
//		Recipe s2 = new Recipe("chicken pot pie", "Get this chicken pot pie on the table", null);
//		assertEquals(mockRecipes, actualRecipes);
//
//		List<Recipe> mockReturnValues = new ArrayList<>();
//		mockReturnValues
//				.add(new Recipe("Mongolian chicken", "The classic Mongolian recipe gets a chicken twist", null));
//		mockReturnValues.add(new Recipe("chicken pot pie", "Get this chicken pot pie on the table", null));
//
//		when(recipeDao.getAllRecipes()).thenReturn(mockReturnValues);
//
//		List<Recipe> actual = recipeService.getAllRecipes();
//
//		List<Recipe> expected = new ArrayList<>();
//		expected.add(new Recipe("Mongolian chicken", "The classic Mongolian recipe gets a chicken twist", null));
//		expected.add(new Recipe("chicken pot pie", "Get this chicken pot pie on the table", null));
//
//		assertEquals(expected, actual);
//
//	}

//	@Test()
//	@Transactional
//	@Order(5)
//	public void test_getAllRecipes_negative() throws DatabaseException, BadParameterException, SQLException, RecipeNotFoundException {
//		recipeService.getAllRecipes();
//	}

	@Test
	@Transactional
	@Order(7)
	// Testing AddRecipeIngredient
	void testAddRecipeIngredient_hasAutoGeneratedId() throws BadParameterException {
		AddRecipeIngredientDTO dto = new AddRecipeIngredientDTO();
		dto.setQuantity(6);

		RecipeIngredient ri = recipeService.addRecipeIngredient(dto, null, null);

		assertEquals(1, ri.getRiID());
	}

//	@BeforeEach
//	public void RecipeService(RecipeDao recipeDao) {
//		this.recipeDao = recipeDao;
//	}
//	
//	
//
//	@Test
//	public RecipeIngredient addRecipeIngredient(AddRecipeIngredientDTO addRecipeIngredientDto, Recipe recipe, Ingredient ingredient) throws BadParameterException {
//
//		RecipeIngredient addedRecipeIngredient = recipeIngredientDao.addRecipeIngredient(addRecipeIngredientDto, recipe, ingredient);
//
//		return addedRecipeIngredient;
//	}

	@Test
	@Transactional
	@Order(8)
	public List<Recipe> testgetAllRecipes() throws RecipeNotFoundException, SQLException {
		List<Recipe> recipes = recipeDao.getAllRecipes();

		if (recipes.size() == 0) {
			throw new RecipeNotFoundException("No recipes were found in the system");
		}

		return recipes;
	}

}
