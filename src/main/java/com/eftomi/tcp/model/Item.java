package com.eftomi.tcp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String itemNo;
	private String boxType;
	private String palletType;
	private int	pcsInBox;
	private double itemWeight;
	private int stock;
	@ManyToOne
	private Box box;
	@ManyToOne
	private Pallet pallet;
	public Item(String itemNo, String boxType, String palletType, int pcsInBox, double itemWeight, int stock, Box box,
			Pallet pallet) {
		super();
		this.itemNo = itemNo;
		this.boxType = boxType;
		this.palletType = palletType;
		this.pcsInBox = pcsInBox;
		this.itemWeight = itemWeight;
		this.stock = stock;
		this.box = box;
		this.pallet = pallet;
	}
	
	@Column(name = "item_no")
	public String getItemNo() {
		return itemNo;
	}
	
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	
	@Column(name = "get_box_type")
	public String getBoxType() {
		return boxType;
	}
	
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	
	@Column(name = "pallet_type")
	public String getPalletType() {
		return palletType;
	}
	
	public void setPalletType(String palletType) {
		this.palletType = palletType;
	}
	
	@Column(name = "pcs_in_box")
	public int getPcsInBox() {
		return pcsInBox;
	}
	
	public void setPcsInBox(int pcsInBox) {
		this.pcsInBox = pcsInBox;
	}
	
	@Column(name = "item_weight")
	public double getItemWeight() {
		return itemWeight;
	}
	
	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Box getBox() {
		return box;
	}
	
	public void setBox(Box box) {
		this.box = box;
	}
	
	public Pallet getPallet() {
		return pallet;
	}
	
	public void setPallet(Pallet pallet) {
		this.pallet = pallet;
	}
	
	

}
