package com.jwt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jwt.entity.Users;
import com.jwt.repository.UserRepository;

@Service
public class UsersService {

	@Autowired
	UserRepository userRepository;
	
	public Users insertUsers(Users user) {	
		Users user1 = userRepository.save(user);
		return user1;
	}
	
	public List<Users> getallUsers() {
		List<Users> user = userRepository.findAll();
		return user;
	}
}
