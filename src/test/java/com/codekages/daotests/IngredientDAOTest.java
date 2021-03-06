package com.codekages.daotests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codekages.dao.IngredientDAO;
import com.codekages.dto.AddIngredientDTO;
import com.codekages.dto.IngredientDTO;
import com.codekages.model.Ingredient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class IngredientDAOTest {

	@Autowired
	private IngredientDAO ingredientDao;
	
	@Test
	@Transactional
	@Order(0)
	@Commit// By default, after every single test, the transaction will be rolled back
	// If we want to retain the data that was modified during that transaction,
//	we need to have @Commit annotation to actually commit the changes
	void testAddIngredient_hasAutoGeneratedId() {
		AddIngredientDTO dto = new AddIngredientDTO();
		dto.setName("Spinach");
		dto.setCost(2.00);
		
		Ingredient ingredient = ingredientDao.addIngredient(dto);
		
		assertEquals(1,ingredient.getId());
	}
	
	@Test
	@Transactional
	@Order(1)
	void testAddIngredient_Success() {
		
		
		AddIngredientDTO dto = new AddIngredientDTO();
		dto.setName("Spinach");
		dto.setCost(2.00);
		Ingredient actual = ingredientDao.addIngredient(dto);
		
		Ingredient expected = new Ingredient("Spinach",2.00);
		expected.setId(2);
		
		assertEquals(expected,actual);
	}
	
//	@Test
//	@Transactional
//	@Order(2)
//	void testGetIngredientById_doesNotExsit() {
//	Ingredient actual = ingredientDao.getIngredientById(1000);
//	
//	Ingredient expected = null;
//	assertEquals(expected,actual);
//	}
	
	
	@Test
	@Transactional 
	@Order(3)
	void testEditIngredient_hasAutoGeneratedId() {
		IngredientDTO dto = new IngredientDTO();
		dto.setCost(2.00);
		
		
		Ingredient ingredient = ingredientDao.editIngredient(1, dto);
		assertEquals(1,ingredient.getId());
	
	}
	
	
}
