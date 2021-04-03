package com.eftomi.tcp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eftomi.tcp.entity.User;
import com.eftomi.tcp.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	@Autowired
	private UserRepository userDAO;
	
	@Transactional
	public boolean registration(String name, String email, String password) {
		log.debug(String.format("registration('%s')", email));
		
		Optional<User> optUser = userDAO.findByEmail(email);
		if (optUser.isEmpty() && valid(name, email, password)) {
			userDAO.save(new User(name, email, password));
			return true;
		} else {
			return false;
		}
	}
	
	public boolean login(String email, String password) {
		log.debug(String.format("login('%s')", email));
		
		Optional<User> optUser = userDAO.findByEmailAndPassword(email, password);
		return optUser.isPresent();
	}

	public Optional<User> getUser(String email) {
		return userDAO.findByEmail(email);
	}
	
	private boolean valid(String name, String email, String password) {
		if (email.matches("[a-zA-Z0-9.@_]+") && email.contains("@") && email.contains(".") && password.length() > 2 && name.length() > 2) {
			return true;
		} else {
			return false;  //Regisztráció ellenőrzés kikapcsolása: true.
		}
	}
}
