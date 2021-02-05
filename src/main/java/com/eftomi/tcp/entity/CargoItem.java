package com.eftomi.tcp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CargoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String itemNumber;
	private Integer qtyNeeds;
	private Integer qtyToBeDelivered;

	public CargoItem(String itemNumber, Integer qtyNeeds, Integer qtyToBeDelivered) {
		super();
		this.itemNumber = itemNumber;
		this.qtyNeeds = qtyNeeds;
		this.qtyToBeDelivered = qtyToBeDelivered;
	}

	public CargoItem() {}

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
	
	
}
