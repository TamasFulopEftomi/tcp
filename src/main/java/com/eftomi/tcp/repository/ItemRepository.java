package com.eftomi.tcp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import com.eftomi.tcp.entity.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer>{
	List<Item> findAll();
	Optional<Item> findByItemNo(String ItemNo);
}
