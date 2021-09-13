package com.codekages.controller.unitTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.codekages.controller.IngredientController;
import com.codekages.model.Ingredient;
import com.codekages.service.IngredientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
public class IngredientGetControllerTest {

	private static MockMvc mockMvc;
	
	@Mock 
	private IngredientService ingredientServiceMock;
	
	@InjectMocks
	private IngredientController ingredientController;
	
	@BeforeEach 
	void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();
		
	}
	
	@Test 
	void testgetAllingredients_Success() throws Exception{
		List<Ingredient> ingredient = new ArrayList<>();
		ingredient.add(new Ingredient("Italian", 50.0));		

		Mockito.when(ingredientServiceMock.getAllIngredients()).thenReturn(ingredient);
		
		String url = "/ingredients";
		
		 mockMvc
			.perform(get(url))
			.andExpect(status().isOk())
			.andReturn();
		
//		byte[] bytes = mvcResult.getResponse().getContentAsByteArray();
//		Path path = Paths.get("user.xlsx");
//		Files.write(path,bytes);
		
	
	}
	
}
