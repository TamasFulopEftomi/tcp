package com.eftomi.tcp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
	private double palletWeight;
	private double roofWeight;
	private boolean stackable;
	@OneToMany(mappedBy = "pallet", cascade = CascadeType.ALL)
	private List<Item> items;
	
	public Pallet(String palletType, double palletWeight, double roofWeight, boolean stackable) {
		super();
		this.palletType = palletType;
		this.palletWeight = palletWeight;
		this.roofWeight = roofWeight;
		this.stackable = stackable;
	}

	@Column(name = "pallet_type")
	public String getPalletType() {
		return palletType;
	}

	public void setPalletType(String palletType) {
		this.palletType = palletType;
	}

	@Column(name = "pallet_weight")
	public double getPalletWeight() {
		return palletWeight;
	}

	public void setPalletWeight(double palletWeight) {
		this.palletWeight = palletWeight;
	}

	@Column(name = "roof_weight")
	public double getRoofWeight() {
		return roofWeight;
	}

	public void setRoofWeight(double roofWeight) {
		this.roofWeight = roofWeight;
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
