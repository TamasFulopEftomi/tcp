package com.eftomi.tcp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CargoItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String itemNumber;
	private Integer qtyNeeds;
	private Integer qtyToBeDelivered;
	@ManyToOne
	private Cargo cargo;
	
	public CargoItem(String itemNumber, Integer qtyNeeds, Integer qtyToBeDelivered, Cargo cargo) {
		super();
		this.itemNumber = itemNumber;
		this.qtyNeeds = qtyNeeds;
		this.qtyToBeDelivered = qtyToBeDelivered;
		this.cargo = cargo;
	}
	
	public CargoItem() {
		super();
	}
	
	public CargoItem(String itemNumber, Integer qtyNeeds, Integer qtyToBeDelivered) {
		this.itemNumber = itemNumber;
		this.qtyNeeds = qtyNeeds;
		this.qtyNeeds = qtyNeeds;
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
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
