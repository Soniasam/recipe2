package com.codekages.controller;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codekages.dto.LoginDTO;
import com.codekages.dto.MessageDTO;
import com.codekages.model.User;
import com.codekages.service.AuthorizationService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class LoginController {
	
	@Autowired
	private AuthorizationService authService;
	
	@Autowired
	private HttpServletRequest request;

	@PostMapping(path = "/login", consumes = "application/json")
	public ResponseEntity<Object> login(@RequestBody LoginDTO loginDto) {
		
		try {
			User user = this.authService.login(loginDto.getUsername(), loginDto.getPassword());
			
			// true as a parameter means create a new session
			// false as a parameter means do not create a new session if one does not already exist. Return null instead
			HttpSession session = request.getSession(true);
			
			if (session.getAttribute("currentUser") != null) {
				return ResponseEntity.status(400).body(new MessageDTO("You are already logged in!"));
			}
			
			session.setAttribute("currentUser", user);
			
			return ResponseEntity.status(200).body(user);
		} catch (LoginException e) {
			return ResponseEntity.status(400).body(new MessageDTO(e.getMessage()));
		}
	}
	
	@GetMapping(path = "/currentuser")
	public ResponseEntity<Object> getCurrentUser() {
		
		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("currentUser") == null) {
			return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
		}
		
		User user = (User) session.getAttribute("currentUser");
		return ResponseEntity.status(200).body(user);
		
	}
	
	@PostMapping (path = "/logout")
	public ResponseEntity<Object> logout(){

		HttpSession session = request.getSession(false);
		
		if (session == null || session.getAttribute("currentUser") == null) {
			return ResponseEntity.status(400).body(new MessageDTO("You are not logged in!"));
		}
		
		session.invalidate(); //invaliadates this session
		return ResponseEntity.status(200).body(new MessageDTO("You have logged out successfully"));
	}
}
