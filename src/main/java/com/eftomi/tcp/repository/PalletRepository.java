package com.eftomi.tcp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eftomi.tcp.entity.Pallet;

@Repository
public interface PalletRepository extends CrudRepository<Pallet, Integer>{
	List<Pallet> findAll();
}
