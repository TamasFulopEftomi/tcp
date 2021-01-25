package com.eftomi.tcp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eftomi.tcp.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findByEmail(String email);
	Optional<User> findByEmailAndPassword(String email, String password);
}
