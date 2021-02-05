package com.eftomi.tcp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eftomi.tcp.entity.CargoItem;

@Repository
public interface CargoItemRepository extends CrudRepository<CargoItem, Integer>{

}
