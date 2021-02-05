package com.eftomi.tcp.dto;


public class CargoListDTO {
	private int nettoWeight;
	private int emptiesWeight;
	private int bruttoWeight;
	private int numberOfPallets;
	private int numberOfWholePallets;
	private int numberOfNotWholePallets;
	private int loadingSpace;
	
	public CargoListDTO(int nettoWeight, int emptiesWeight, int bruttoWeight,
			int numberOfPallets, int numberOfWholePallets, int numberOfNotWholePallets, int loadingSpace) {
		super();
		this.nettoWeight = nettoWeight;
		this.emptiesWeight = emptiesWeight;
		this.bruttoWeight = bruttoWeight;
		this.numberOfPallets = numberOfPallets;
		this.numberOfWholePallets = numberOfWholePallets;
		this.numberOfNotWholePallets = numberOfNotWholePallets;
		this.loadingSpace = loadingSpace;
	}
	
	public CargoListDTO() {
		super();
	}

	public int getNettoWeight() {
		return nettoWeight;
	}
	public void setNettoWeight(int nettoWeight) {
		this.nettoWeight = nettoWeight;
	}
	public int getEmptiesWeight() {
		return emptiesWeight;
	}
	public void setEmptiesWeight(int emptiesWeight) {
		this.emptiesWeight = emptiesWeight;
	}
	public int getBruttoWeight() {
		return bruttoWeight;
	}
	public void setBruttoWeight(int bruttoWeight) {
		this.bruttoWeight = bruttoWeight;
	}
	public int getNumberOfPallets() {
		return numberOfPallets;
	}
	public void setNumberOfPallets(int numberOfPallets) {
		this.numberOfPallets = numberOfPallets;
	}
	public int getNumberOfWholePallets() {
		return numberOfWholePallets;
	}
	public void setNumberOfWholePallets(int numberOfWholePallets) {
		this.numberOfWholePallets = numberOfWholePallets;
	}
	public int getNumberOfNotWholePallets() {
		return numberOfNotWholePallets;
	}
	public void setNumberOfNotWholePallets(int numberOfNotWholePallets) {
		this.numberOfNotWholePallets = numberOfNotWholePallets;
	}
	public int getLoadingSpace() {
		return loadingSpace;
	}
	public void setLoadingSpace(int loadingSpace) {
		this.loadingSpace = loadingSpace;
	}
	
	
}
