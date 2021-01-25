package com.eftomi.tcp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eftomi.tcp.entity.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer>{

}
