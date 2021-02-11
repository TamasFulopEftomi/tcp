package com.eftomi.tcp.dto;

public class CargoItemDTO {
	private Integer id;
	private String itemNumber;
	private Integer qtyNeeds;
	private Integer qtyToBeDelivered;
	private String qtyNeedsString;
	
	public CargoItemDTO(Integer id, String itemNumber, Integer qtyNeeds, Integer qtyToBeDelivered,
			String qtyNeedsString) {
		super();
		this.id = id;
		this.itemNumber = itemNumber;
		this.qtyNeeds = qtyNeeds;
		this.qtyToBeDelivered = qtyToBeDelivered;
		this.qtyNeedsString = qtyNeedsString;
	}

	public CargoItemDTO() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(String itemNumber) {
		this.itemNumber = itemNumber;
	}

	public Integer getQtyNeeds() {
		return qtyNeeds;
	}

	public void setQtyNeeds(Integer qtyNeeds) {
		this.qtyNeeds = qtyNeeds;
	}

	public Integer getQtyToBeDelivered() {
		return qtyToBeDelivered;
	}

	public void setQtyToBeDelivered(Integer qtyToBeDelivered) {
		this.qtyToBeDelivered = qtyToBeDelivered;
	}

	public String getQtyNeedsString() {
		return qtyNeedsString;
	}

	public void setQtyNeedsString(String qtyNeedsString) {
		this.qtyNeedsString = qtyNeedsString;
	}
	
	
}