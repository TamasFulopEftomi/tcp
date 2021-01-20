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
public class Box {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String boxType;
	private int boxesInRow;
	private int rowsOnPallet;
	private double boxWeight;
	@OneToMany(mappedBy = "box", cascade = CascadeType.ALL)
	private List<Item> items;
	
	public Box(String boxType, int boxesInRow, int rowsOnPallet, double boxWeight, List<Item> items) {
		super();
		this.boxType = boxType;
		this.boxesInRow = boxesInRow;
		this.rowsOnPallet = rowsOnPallet;
		this.boxWeight = boxWeight;
		this.items = items;
	}

	@Column(name = "box_type")
	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	@Column(name = "boxes_in_row")
	public int getBoxesInRow() {
		return boxesInRow;
	}

	public void setBoxesInRow(int boxesInRow) {
		this.boxesInRow = boxesInRow;
	}

	@Column(name = "rows_on_pallet")
	public int getRowsOnPallet() {
		return rowsOnPallet;
	}

	public void setRowsOnPallet(int rowsOnPallet) {
		this.rowsOnPallet = rowsOnPallet;
	}

	@Column(name = "box_weight")
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
