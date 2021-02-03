package com.eftomi.tcp.dto;

import java.util.List;

import com.eftomi.tcp.entity.Item;


public class CargoDTO {
	private List<Item> itemList;

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemNoList(List<Item> itemList) {
		this.itemList = itemList;
	}
	
	
}
