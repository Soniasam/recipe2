package com.codekages.controller.integration;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.codekages.dto.AddRecipeDTO;
import com.codekages.dto.AddRecipeIngredientDTO;
import com.codekages.model.Ingredient;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeIngredient;
import com.codekages.model.User;
import com.codekages.service.AuthorizationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)

public class RecipeControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private AuthorizationService authService;

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		this.objectMapper = new ObjectMapper();
	}

	@Test
	@Transactional
	@Order(0)
	@Commit
	void test_addRecipe_andReciveJsonResponse() throws Exception {

		Session session = sessionFactory.getCurrentSession();
		session.persist(new User("user12345", "12345", "Shaq", "Bruno"));

		User user = authService.login("user12345", "12345");

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("currentUser", user);

		AddRecipeDTO addRecipeDto = new AddRecipeDTO();
		addRecipeDto.setName("Indian");
		addRecipeDto.setDescription("Spicy, rich, flavourful and diverse");

		String addRecipeDtoJson = objectMapper.writeValueAsString(addRecipeDto);
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/recipe").session(mockSession)
				.contentType(MediaType.APPLICATION_JSON).content(addRecipeDtoJson);

		Recipe expected = new Recipe("Indian", "Spicy, rich, flavourful and diverse", user);
		expected.setId(1);
		String expectedJson = objectMapper.writeValueAsString(expected);

		this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().json(expectedJson));
	}
//
//	@Test
//	@Transactional
//	@Order(1)
//	@Commit
//	void test_addRecipeIngredient_andReciveJsonResponse()throws Exception{
//		
//		Session session = sessionFactory.getCurrentSession();
//		session.persist(new User ("user123", "123","Shaq","Bruno"));
//		
//		User user = authService.login("user123", "123");
//		
//		MockHttpSession mockSession = new MockHttpSession();
//		mockSession.setAttribute("currentUser", user);
//		
//		
//		AddRecipeIngredientDTO addRecipeIngredientDto = new AddRecipeIngredientDTO ();
//		addRecipeIngredientDto .setQuantity(5);
//		
//		Recipe recipe = new Recipe();
//		Ingredient ingredient = new Ingredient();
////		User user = new User();
//		
//		
////		user.setId(1);
////		user.setFirstName("Sonia");
////		user.setLastName("Sam");
////		user.setUsername("user123");
////		user.setPassword("1234");
////		user.setUserRole(2);
//		
//		recipe.setId(1);
//		recipe.setUser(user);
//		recipe.setRecipeName("Tortilla");
//		recipe.setRecipeDescription("It is a thin, flat, circular unleavened flatbread");
//		
//		ingredient.setId(1);
//		ingredient.setName("Onions");
//		ingredient.setCost(15.0);
//		
//		String addRecipeIngredientDtoJson = objectMapper.writeValueAsString(addRecipeIngredientDto);
//	
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.post("/recipe/1/ingredient/1")
//				.session(mockSession)
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(addRecipeIngredientDtoJson);
//		
//		
//		RecipeIngredient expected  = new RecipeIngredient(1,recipe,ingredient);
//		expected.setRiID(1);
//		String  expectedJson = objectMapper.writeValueAsString(expected);
//		
//		this.mockMvc
//		.perform(builder)
//		.andExpect(MockMvcResultMatchers.status().isCreated())
//		.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//	}


}

