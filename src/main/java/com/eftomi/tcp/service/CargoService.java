package com.eftomi.tcp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eftomi.tcp.entity.Box;
import com.eftomi.tcp.entity.Item;
import com.eftomi.tcp.repository.BoxRepository;
import com.eftomi.tcp.repository.CargoRepository;
import com.eftomi.tcp.repository.ItemRepository;
import com.eftomi.tcp.repository.PalletRepository;
import com.eftomi.tcp.repository.WarehouseRepository;

@Service
public class CargoService {
	
	@Autowired
	private BoxRepository boxDAO;
	
	@Autowired
	private CargoRepository cargoDAO;
	
	@Autowired
	private ItemRepository itemDAO;
	
	@Autowired
	private PalletRepository palletDAO;
	
	@Autowired
	private WarehouseRepository warehouseDAO;

	public List<Item> getAllItems() {
		return itemDAO.findAll();
	}
	
	public List<Box> getAllBoxes() {
		return boxDAO.findAll();
	}
}
