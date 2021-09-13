package com.codekages.controller.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.IngredientDTO;
import com.codekages.model.Ingredient;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)

public class IngredientControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

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
	void test_addIngredient_andReciveJsonResponse () throws Exception {
		AddIngredientDTO addIngredientDto = new AddIngredientDTO();
		addIngredientDto.setName("Butter");
		addIngredientDto.setCost(5.0);
		
		String addIngredientDtoJson = objectMapper.writeValueAsString(addIngredientDto);
		
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
				.post("/ingredient")
				.contentType(MediaType.APPLICATION_JSON)
				.content(addIngredientDtoJson);
		
		Ingredient expected = new Ingredient ("Butter",5.0);
		expected.setId(1);
		String expectedJson = objectMapper.writeValueAsString(expected);
		
		
		this.mockMvc
		.perform(builder)
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.content().json(expectedJson));
		
		
				
	}
	@Test
	@Transactional
	@Order(1)
	void test_editIngredient_andReciveJsonResponse() throws Exception{
	IngredientDTO editIngredientDto = new IngredientDTO();
	editIngredientDto.setCost(5.0);
	
	String editIngredientDtoJson = objectMapper.writeValueAsString(editIngredientDto);
	
	MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
			.patch("/ingredient/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(editIngredientDtoJson);
	
	Ingredient expected = new Ingredient ("Butter",5.0);
	expected.setId(1);
	String expectedJson = objectMapper.writeValueAsString(expected);
			
	
	this.mockMvc
	.perform(builder)
	.andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(MockMvcResultMatchers.content().json(expectedJson));
	
		
	}
	
}
	


