package com.eftomi.tcp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eftomi.tcp.dto.PackagingInstructionDTO;
import com.eftomi.tcp.entity.Cargo;
import com.eftomi.tcp.entity.CargoItem;
import com.eftomi.tcp.entity.Item;
import com.eftomi.tcp.repository.CargoItemRepository;
import com.eftomi.tcp.repository.ItemRepository;
import com.eftomi.tcp.service.exception.CargoItemNotFoundException;

@Service
public class CargoService {
	
	@Autowired
	private ItemRepository itemDAO;
	
	@Autowired
	private CargoItemRepository cargoItemDAO;

	public List<Item> getAllItems() {
		return itemDAO.findAll();
	}
	
	public List<PackagingInstructionDTO> packagingInstruction() {
		List<Item> itemList = getAllItems();
		List<PackagingInstructionDTO> pIDTOList = new ArrayList<>();
		for (Item item : itemList) {
			PackagingInstructionDTO pIDTO = new PackagingInstructionDTO();
			pIDTO.setItemNo(item.getItemNo());
			pIDTO.setPalletType(item.getPallet().getPalletType());
			pIDTO.setBoxType(item.getBox().getBoxType());
			pIDTO.setPcsInBox(item.getPcsInBox());
			pIDTO.setBoxesInRow(item.getBox().getBoxesInRow());
			pIDTO.setRowsOnPallet(item.getBox().getRowsOnPallet());
			pIDTO.setStackable(item.getPallet().isStackable());
			pIDTOList.add(pIDTO);
		}
		return pIDTOList;
	}
	
	public Map<String, String> getItemNumberMap() {
		List<Item> itemList = getAllItems();
		Map<String, String> itemNumberMap = new TreeMap<>();
		for (Item item : itemList) {
			itemNumberMap.put(item.getItemNo(), item.getItemNo());  //expandable fe.: with cross reference
		}
		return itemNumberMap;
	}

	public void createDeliveryNote(Set<String> itemNumberSet) {
		for (String itemNumber : itemNumberSet) {
			cargoItemDAO.save(new CargoItem(itemNumber, 0, 0));
		}
	}
	
	public Iterable<CargoItem> getAllCargoItems() {
		return cargoItemDAO.findAll();
	}

	public void clearCargoItem() {
		cargoItemDAO.deleteAll();
		
	}
	
	public Optional<CargoItem> getCargoItem(int id) {
		return cargoItemDAO.findById(id);
	}
	
	public CargoItem update(CargoItem cargoItem) {
		Optional<CargoItem> optDbCargoItem = cargoItemDAO.findById(cargoItem.getId());
		if (optDbCargoItem.isPresent()) {
			CargoItem dbCargoItem = optDbCargoItem.get();
			dbCargoItem.setItemNumber(cargoItem.getItemNumber());
			dbCargoItem.setQtyNeeds(cargoItem.getQtyNeeds());
			return cargoItemDAO.save(dbCargoItem);
		} else {
			throw new CargoItemNotFoundException(cargoItem.getId());
		}

	}
	
	
	public void calculateQtyToBeDelivered(List<CargoItem> cargoItems) {
		for (CargoItem cargoItem : cargoItems) {
			
		}
	}
	
	public Cargo calculateCargo(List<CargoItem> cargoItems) {
		for (CargoItem cargoItem : cargoItems) {
			
		}
		return null;
	}
}
