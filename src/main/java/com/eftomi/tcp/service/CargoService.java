package com.eftomi.tcp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eftomi.tcp.dto.PackagingInstructionDTO;
import com.eftomi.tcp.entity.Box;
import com.eftomi.tcp.entity.Item;
import com.eftomi.tcp.repository.BoxRepository;
import com.eftomi.tcp.repository.CargoRepository;
import com.eftomi.tcp.repository.ItemRepository;
import com.eftomi.tcp.repository.PalletRepository;

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
	
	
}
