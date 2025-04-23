package com.jwt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.entity.Users;
import com.jwt.services.UsersService;

@RestController
@RequestMapping("/Home")
public class HomeController {

	@Autowired
	UsersService userService;
	
	//  http://localhost:8080/Home/User
	@GetMapping("/User")
	private List<Users> getUser() {
		List<Users> Users = userService.getallUsers();
		return Users;
	}
	
	//  http://localhost:8080/Home/insertUser
	@PostMapping(value ="/insertUser", consumes = "application/json")
	public String postMethodName(@RequestBody Users entity) {
		userService.insertUsers(entity);	
		return "saved";	
	}
}
