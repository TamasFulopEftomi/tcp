package com.eftomi.tcp.dto;

import java.util.Map;

public class CargoListDTO {
	private double nettoWeight;
	private double emptiesWeight;
	private double bruttoWeight;
	private int numberOfPallets;
	private int numberOfWholePallets;
	private int numberOfNotWholePallets;
	private int loadingSpace;
	private Map<String, Integer> empties;

	public CargoListDTO(double nettoWeight, double emptiesWeight, double bruttoWeight, int numberOfPallets,
			int numberOfWholePallets, int numberOfNotWholePallets, int loadingSpace, Map<String, Integer> empties) {
		super();
		this.nettoWeight = nettoWeight;
		this.emptiesWeight = emptiesWeight;
		this.bruttoWeight = bruttoWeight;
		this.numberOfPallets = numberOfPallets;
		this.numberOfWholePallets = numberOfWholePallets;
		this.numberOfNotWholePallets = numberOfNotWholePallets;
		this.loadingSpace = loadingSpace;
		this.empties = empties;
	}

	public CargoListDTO() {
		super();
	}

	public double getNettoWeight() {
		return nettoWeight;
	}

	public void setNettoWeight(double nettoWeight) {
		this.nettoWeight = nettoWeight;
	}

	public double getEmptiesWeight() {
		return emptiesWeight;
	}

	public void setEmptiesWeight(double emptiesWeight) {
		this.emptiesWeight = emptiesWeight;
	}

	public double getBruttoWeight() {
		return bruttoWeight;
	}

	public void setBruttoWeight(double bruttoWeight) {
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

	public Map<String, Integer> getEmpties() {
		return empties;
	}

	public void setEmpties(Map<String, Integer> empties) {
		this.empties = empties;
	}

	

}
