package com.codekages.controller.integration;

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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.codekages.dto.AddListOfRecipeDTO;
import com.codekages.model.ListOfRecipe;
import com.codekages.model.Recipe;
import com.codekages.model.RecipeList;
import com.codekages.model.User;
import com.codekages.service.AuthorizationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)

public class ListOfRecipeControllerTest {

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
	void test_addListOfRecipe_andReciveJsonResponse() throws Exception {

		Session session = sessionFactory.getCurrentSession();
		session.persist(new User ("user12345", "12345","Shaq","Bruno"));

		User user = authService.login("user12345", "12345");

		MockHttpSession mockSession = new MockHttpSession();
		mockSession.setAttribute("currentUser", user);

		AddListOfRecipeDTO addListOfRecipeDto = new AddListOfRecipeDTO();
		addListOfRecipeDto.setListOfRecipe("Thai");

		String addListOfRecipeDtoJson = objectMapper.writeValueAsString(addListOfRecipeDto);

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/listofrecipe")
				.session(mockSession)
				.contentType(MediaType.APPLICATION_JSON)
				.content(addListOfRecipeDtoJson);


		ListOfRecipe expected = new ListOfRecipe("Thai", user);
		expected.setId(1);
		String expectedJson = objectMapper.writeValueAsString(expected);

		this.mockMvc
		.perform(builder)
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.content().json(expectedJson));
	}
//
//	@Test
//	@Transactional
//	@Order(2)
////	@Commit
//	void test_editListOfRecipe_andReciveJsonResponse() throws Exception{
//		Session session = sessionFactory.getCurrentSession();
//		session.persist(new User ("user1234", "1234","Shaq","Bruno"));
//
//		User user = authService.login("user1234", "1234");
//		MockHttpSession mockSession = new MockHttpSession();
//		mockSession.setAttribute("currentUser", user);
//
//		ListOfRecipe list = new ListOfRecipe("Italian",user);
//		list.setId(2);
//		
//
//		Recipe recipe = new Recipe("Pizza"," a flat, round base of dough baked with a topping of tomato sauce  a flat, round base of dough baked with a topping of tomato sauce and cheese",user);
//		recipe.setId(1);
//
//		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
//				.patch("/listofrecipe/2/recipe/1")
//				.session(mockSession);
//
//
//
//		RecipeList  expected = new RecipeList(list,recipe);
//		expected.setId(1);
//		String expectedJson = objectMapper.writeValueAsString(expected);
//
//		this.mockMvc
//		.perform(builder)
////		.with(csrf())
//		.andExpect(MockMvcResultMatchers.status().isOk())
//		.andExpect(MockMvcResultMatchers.content().json(expectedJson));
//	}



}



