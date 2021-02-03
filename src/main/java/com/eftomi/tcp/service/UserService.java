package com.eftomi.tcp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eftomi.tcp.entity.User;
import com.eftomi.tcp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userDAO;
	
	public boolean registration(String name, String email, String password) {	//check data!
		Optional<User> optUser = userDAO.findByEmail(email);
		if (optUser.isEmpty()) {
			userDAO.save(new User(name, email, password));
		}
		return true;
	}
	
	public boolean login(String email, String password) {
		Optional<User> optUser = userDAO.findByEmailAndPassword(email, password);
		return optUser.isPresent();
	}

	public Optional<User> getUser(String email) {
		return userDAO.findByEmail(email);
	}
}
