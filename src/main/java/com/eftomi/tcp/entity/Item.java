package com.eftomi.tcp.entity;

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
	private int	pcsInBox;
	private double itemWeight;
	@ManyToOne
	private Box box;
	@ManyToOne
	private Pallet pallet;

	public Item(String itemNo, int pcsInBox, double itemWeight, Box box, Pallet pallet) {
		super();
		this.itemNo = itemNo;
		this.pcsInBox = pcsInBox;
		this.itemWeight = itemWeight;
		this.box = box;
		this.pallet = pallet;
	}

	public Item() {}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public int getPcsInBox() {
		return pcsInBox;
	}

	public void setPcsInBox(int pcsInBox) {
		this.pcsInBox = pcsInBox;
	}

	public double getItemWeight() {
		return itemWeight;
	}

	public void setItemWeight(double itemWeight) {
		this.itemWeight = itemWeight;
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
