package com.eftomi.tcp.dto;

public class PackagingInstructionDTO {
	private String itemNo;
	private double weight;
	private String palletType;
	private String boxType;
	private int pcsInBox;
	private int boxesInRow;
	private int rowsOnPallet;
	private boolean stackable;
	
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getPalletType() {
		return palletType;
	}
	public void setPalletType(String palletType) {
		this.palletType = palletType;
	}
	public String getBoxType() {
		return boxType;
	}
	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}
	public int getPcsInBox() {
		return pcsInBox;
	}
	public void setPcsInBox(int pcsInBox) {
		this.pcsInBox = pcsInBox;
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
	public boolean isStackable() {
		return stackable;
	}
	public void setStackable(boolean stackable) {
		this.stackable = stackable;
	}

}
