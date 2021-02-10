package com.eftomi.tcp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String palletType;
	private String palletName;
	private double palletWeight;
	private double lidWeight;
	private boolean stackable;
	@OneToMany(mappedBy = "pallet")
	private List<Item> items;

	public Pallet(String palletName, String palletType, double palletWeight, double lidWeight, boolean stackable) {
		super();
		this.palletName = palletName;
		this.palletType = palletType;
		this.palletWeight = palletWeight;
		this.lidWeight = lidWeight;
		this.stackable = stackable;
	}

	public Pallet() {}

	public String getPalletName() {
		return palletName;
	}

	public void setPalletName(String palletName) {
		this.palletName = palletName;
	}

	public String getPalletType() {
		return palletType;
	}

	public void setPalletType(String palletType) {
		this.palletType = palletType;
	}

	public double getPalletWeight() {
		return palletWeight;
	}

	public void setPalletWeight(double palletWeight) {
		this.palletWeight = palletWeight;
	}

	public double getLidWeight() {
		return lidWeight;
	}

	public void setLidWeight(double lidWeight) {
		this.lidWeight = lidWeight;
	}

	public boolean isStackable() {
		return stackable;
	}

	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
}
