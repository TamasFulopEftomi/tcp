package com.eftomi.tcp.dto;

import java.util.Map;
import java.util.TreeMap;

public class ItemNumberMapDTO {
	private Map<String, String> itemNumberMap;

	public ItemNumberMapDTO() {
		itemNumberMap =new TreeMap<>();
	}

	public Map<String, String> getItemNumberMap() {
		return itemNumberMap;
	}

	public void setItemNumberMap(Map<String, String> itemNumberMap) {
		this.itemNumberMap = itemNumberMap;
	}
	
}
