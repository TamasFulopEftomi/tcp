package com.eftomi.tcp.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cargo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer weightOfGoods;
	private Integer weightOfPallets;
	private Integer numberOfPallets;
	private Integer numberOfWholePallets;
	private Integer numberOfNotWholePallets;
	private Integer boxesNumberOnNotWholePallets;
	@OneToMany(mappedBy ="cargo")
	private List<CargoItem> cargoItemList;
	
	public Cargo(Integer weightOfGoods, Integer weightOfPallets, Integer numberOfPallets, Integer numberOfWholePallets,
			Integer numberOfNotWholePallets, Integer boxesNumberOnNotWholePallets) {
		super();
		this.weightOfGoods = weightOfGoods;
		this.weightOfPallets = weightOfPallets;
		this.numberOfPallets = numberOfPallets;
		this.numberOfWholePallets = numberOfWholePallets;
		this.numberOfNotWholePallets = numberOfNotWholePallets;
		this.boxesNumberOnNotWholePallets = boxesNumberOnNotWholePallets;
	}

	public Cargo() {
		super();
	}

	public Integer getWeightOfGoods() {
		return weightOfGoods;
	}

	public void setWeightOfGoods(Integer weightOfGoods) {
		this.weightOfGoods = weightOfGoods;
	}

	public Integer getWeightOfPallets() {
		return weightOfPallets;
	}

	public void setWeightOfPallets(Integer weightOfPallets) {
		this.weightOfPallets = weightOfPallets;
	}

	public Integer getNumberOfPallets() {
		return numberOfPallets;
	}

	public void setNumberOfPallets(Integer numberOfPallets) {
		this.numberOfPallets = numberOfPallets;
	}

	public Integer getNumberOfWholePallets() {
		return numberOfWholePallets;
	}

	public void setNumberOfWholePallets(Integer numberOfWholePallets) {
		this.numberOfWholePallets = numberOfWholePallets;
	}

	public Integer getNumberOfNotWholePallets() {
		return numberOfNotWholePallets;
	}

	public void setNumberOfNotWholePallets(Integer numberOfNotWholePallets) {
		this.numberOfNotWholePallets = numberOfNotWholePallets;
	}

	public Integer getBoxesNumberOnNotWholePallets() {
		return boxesNumberOnNotWholePallets;
	}

	public void setBoxesNumberOnNotWholePallets(Integer boxesNumberOnNotWholePallets) {
		this.boxesNumberOnNotWholePallets = boxesNumberOnNotWholePallets;
	}

	public List<CargoItem> getCargoItemList() {
		return cargoItemList;
	}

	public void setCargoItemList(List<CargoItem> cargoItemList) {
		this.cargoItemList = cargoItemList;
	}

}
