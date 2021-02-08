package com.eftomi.tcp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eftomi.tcp.dto.CargoListDTO;
import com.eftomi.tcp.dto.PackagingInstructionDTO;
import com.eftomi.tcp.entity.Box;
import com.eftomi.tcp.entity.CargoItem;
import com.eftomi.tcp.entity.Item;
import com.eftomi.tcp.repository.BoxRepository;
import com.eftomi.tcp.repository.CargoItemRepository;
import com.eftomi.tcp.repository.ItemRepository;
import com.eftomi.tcp.service.exception.CargoItemNotFoundException;
import com.eftomi.tcp.service.exception.ItemNotFoundException;

@Service
public class CargoService {
	
	@Autowired
	private ItemRepository itemDAO;
	
	@Autowired
	private BoxRepository boxDAO;
	
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
			pIDTO.setWeight(item.getItemWeight());
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
	
	public List<Box> emptiesList() {
		return boxDAO.findAll();
	}
	
	public Map<String, String> getItemNumberMap() {
		List<Item> itemList = getAllItems();
		Map<String, String> itemNumberMap = new TreeMap<>();
		for (Item item : itemList) {
			itemNumberMap.put(item.getItemNo(), item.getItemNo());  //expandable fe.: with cross reference
		}
		return itemNumberMap;
	}

	public void cargoListCreate(Set<String> itemNumberSet) {
		for (String itemNumber : itemNumberSet) {
			cargoItemDAO.save(new CargoItem(itemNumber, 0, 0));
		}
	}
	
	public List<CargoItem> getAllCargoItems() {
		return cargoItemDAO.findAll();
	}

	public void clearCargoItem() {
		cargoItemDAO.deleteAll();
	}
	
	public Optional<CargoItem> getCargoItem(int id) {
		return cargoItemDAO.findById(id);
	}
	
	public void calculateCargoItemQuantities(CargoItem cargoItem) {
		String itemNumber = cargoItem.getItemNumber();
		int pcsInBox;
		int qtyNeeds = cargoItem.getQtyNeeds();
		Optional<Item> optItem = itemDAO.findByItemNo(itemNumber);
		if (optItem.isPresent()) {
			Item item = optItem.get();
			pcsInBox = item.getPcsInBox();
		} else {
			throw new ItemNotFoundException(cargoItem.getId());
		}
		int qtyToBeDelivered;
		int wholeBoxesNumber = qtyNeeds / pcsInBox;
		if (qtyNeeds % pcsInBox == 0) {
			qtyToBeDelivered = wholeBoxesNumber * pcsInBox;
		} else {
			qtyToBeDelivered = (wholeBoxesNumber + 1) * pcsInBox;
		}
		cargoItem.setQtyToBeDelivered(qtyToBeDelivered);
		update(cargoItem);
	}
	
	private void update(CargoItem cargoItem) {
		Optional<CargoItem> optDbCargoItem = cargoItemDAO.findById(cargoItem.getId());
		if (optDbCargoItem.isPresent()) {
			CargoItem dbCargoItem = optDbCargoItem.get();
			dbCargoItem.setItemNumber(cargoItem.getItemNumber());
			dbCargoItem.setQtyNeeds(cargoItem.getQtyNeeds());
			dbCargoItem.setQtyToBeDelivered(cargoItem.getQtyToBeDelivered());
			cargoItemDAO.save(dbCargoItem);
		} else {
			throw new CargoItemNotFoundException(cargoItem.getId());
		}

	}
	
	public CargoListDTO calculateCargo(List<CargoItem> cargoItems) {
		Map<String, Integer> empties = new TreeMap<>();
		double nettoWeight = 0;
		int boxesNumber = 0;
		double emptiesWeight = 0;
		int numberOfWholePallets = 0;
		int numberOfNotWholePallets = 0;
		int numberOfPallets = 0;
		int numberOfStackablePallets = 0;
		
		for (CargoItem cargoItem : cargoItems) {
			String itemNo = cargoItem.getItemNumber();
			int qtyTBD = cargoItem.getQtyToBeDelivered();
			Optional<Item> optItem = itemDAO.findByItemNo(itemNo);
			Item item = optItem.get();
			nettoWeight += qtyTBD * item.getItemWeight();
			boxesNumber = qtyTBD / item.getPcsInBox();
			empties = addEmpties(empties, item.getBox().getBoxName(), boxesNumber);
			
			int boxesToBeDelivered = cargoItem.getQtyToBeDelivered() / item.getPcsInBox();
			int boxesOnPallet = item.getBox().getBoxesInRow() * item.getBox().getRowsOnPallet();
			numberOfWholePallets += boxesToBeDelivered / boxesOnPallet;
			numberOfNotWholePallets += (boxesToBeDelivered % boxesOnPallet == 0) ? 0 : 1;
			
			emptiesWeight += boxesToBeDelivered * item.getBox().getBoxWeight() + numberOfPallets * 
					item.getPallet().getPalletWeight() + numberOfWholePallets * item.getPallet().getRoofWeight();

			int boxesToBeDeliveredOnStackablePallet = 0;
			int boxesOnStackablePallet = 0;
			if (item.getPallet().isStackable()) {
				boxesToBeDeliveredOnStackablePallet = cargoItem.getQtyToBeDelivered() / item.getPcsInBox();
				boxesOnStackablePallet = item.getBox().getBoxesInRow() * item.getBox().getRowsOnPallet();
				numberOfStackablePallets += boxesToBeDeliveredOnStackablePallet / boxesOnStackablePallet;
				}
			
			
			}

		double bruttoWeight = nettoWeight + emptiesWeight;
		numberOfPallets = numberOfWholePallets + numberOfNotWholePallets;
		int half;
		int subtraction;
		
		half = numberOfPallets / 2;
		subtraction = half < numberOfStackablePallets ? half : numberOfStackablePallets;
		int loadingSpace = numberOfPallets - subtraction;
		
		CargoListDTO cargoListDTO = new CargoListDTO();
		cargoListDTO.setNettoWeight(nettoWeight);
		cargoListDTO.setEmptiesWeight(emptiesWeight);
		cargoListDTO.setBruttoWeight(bruttoWeight);
		cargoListDTO.setNumberOfNotWholePallets(numberOfNotWholePallets);
		cargoListDTO.setNumberOfWholePallets(numberOfWholePallets);
		cargoListDTO.setNumberOfPallets(numberOfPallets);
		cargoListDTO.setLoadingSpace(loadingSpace);
		cargoListDTO.setEmpties(empties);
		return cargoListDTO;
	}

	private Map<String, Integer> addEmpties(Map<String, Integer> empties, String box, Integer qty) {
		Map<String, Integer> tmp;;
		tmp = empties;
		if (tmp.containsKey(box)) {
			int a = tmp.get(box);
			int sum = a + qty;
			tmp.put(box, sum);
		} else if (qty > 0) {
			tmp.put(box, qty);
		}
		return tmp;
	}
	
}
/*	
	private  void calculateLoadingSpace() {
        for (Pick pick : pickList) {
            if (pick.isStackable()) {
                numberOfStackablePallets += pick.getNumberOfWholePallets();
            }
        }
        int half;
        int subtraction;
        half = sumPallets / 2;
        subtraction = half < numberOfStackablePallets ? half : numberOfStackablePallets;
        loadingSpace = sumPallets - subtraction;
    }*/

