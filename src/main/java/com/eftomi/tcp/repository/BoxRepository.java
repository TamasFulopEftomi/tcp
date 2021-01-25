package com.eftomi.tcp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eftomi.tcp.entity.Box;

@Repository
public interface BoxRepository extends CrudRepository<Box, Integer>{
	List<Box> findAll();
}
