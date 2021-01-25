package com.eftomi.tcp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eftomi.tcp.entity.Warehouse;

@Repository
public interface WarehouseRepository extends CrudRepository<Warehouse, Integer>{

}
