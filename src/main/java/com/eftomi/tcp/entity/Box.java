package com.eftomi.tcp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Box {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String boxType;
	private int boxesInRow;
	private int rowsOnPallet;
	private double boxWeight;
	@OneToMany(mappedBy = "box")
	private List<Item> items;
	
	public Box(String boxType, int boxesInRow, int rowsOnPallet, double boxWeight) {
		super();
		this.boxType = boxType;
		this.boxesInRow = boxesInRow;
		this.rowsOnPallet = rowsOnPallet;
		this.boxWeight = boxWeight;
	}
	
	public Box() {}

	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	public int getBoxesInRow() {
		return boxesInRow;
	}

	public void setBoxesInRow(int boxesInRow) {
		this.boxesInRow = boxesInRow;
	}

	public int getRowsOnPallet() {
		return rowsOnPallet;
	}

	public void setRowsOnPallet(int rowsOnPallet) {
		this.rowsOnPallet = rowsOnPallet;
	}

	public double getBoxWeight() {
		return boxWeight;
	}

	public void setBoxWeight(double boxWeight) {
		this.boxWeight = boxWeight;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	
}
