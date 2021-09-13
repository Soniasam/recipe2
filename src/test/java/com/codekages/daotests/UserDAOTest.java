package com.codekages.daotests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codekages.dao.UserDAO;
import com.codekages.dto.AddUserDTO;
import com.codekages.model.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:applicationContext.xml")
@WebAppConfiguration
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource("classpath:springorm-test.properties")
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class UserDAOTest {
	@Autowired
	private UserDAO userDao;
	
	@Test
	@Transactional
	@Order(0)
	@Commit
	void testAddUser_hasAutoGereatedId() {
	AddUserDTO dto = new AddUserDTO();
	dto.setFirstName("Pete");
	dto.setLastName("Junior");
	dto.setUsername("use1234");
	dto.setPassword("1234");
	
	
	User user = userDao.addUser(dto);
	
	assertEquals(2, user.getId());
}
	
	@Test
	@Transactional
	@Order(1)
	void testUser_Success() {
		AddUserDTO dto = new AddUserDTO();
		dto.setFirstName("Pete");
		dto.setLastName("Junior");
		dto.setUsername("user1234");
		dto.setPassword("1234");
		
		User expected = new User("user1234","1234","Pete","Junior");
		expected.setId(1);
		
		
		User actual = userDao.addUser(dto);
		assertEquals(expected,actual);
	}
}
