package com.karatsin.onlinemoviestore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rental_packages")
public class RentalPackage {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_package_id",nullable = false)
	private int rentalPackageId;
	
	@Column(name="rental_economic_price",nullable = false)
	private float rentalEconomicPrice;
	
	@Column(name="rental_additional_price",nullable = false)
	private float additionalPrice;
	
	@Column(name="rental_package_name",nullable = false)
	private String rentalPackageName;

	public RentalPackage() {}
	
	public RentalPackage(int rentalPackageId, float rentalEconomicPrice, float additionalPrice,
			String rentalPackageName) {
		this.rentalPackageId = rentalPackageId;
		this.rentalEconomicPrice = rentalEconomicPrice;
		this.additionalPrice = additionalPrice;
		this.rentalPackageName = rentalPackageName;
	}

	public int getRentalPackageId() {
		return rentalPackageId;
	}

	public void setRentalPackageId(int rentalPackageId) {
		this.rentalPackageId = rentalPackageId;
	}

	public float getRentalEconomicPrice() {
		return rentalEconomicPrice;
	}

	public void setRentalEconomicPrice(float rentalEconomicPrice) {
		this.rentalEconomicPrice = rentalEconomicPrice;
	}

	public float getAdditionalPrice() {
		return additionalPrice;
	}

	public void setAdditionalPrice(float additionalPrice) {
		this.additionalPrice = additionalPrice;
	}

	public String getRentalPackageName() {
		return rentalPackageName;
	}

	public void setRentalPackageName(String rentalPackageName) {
		this.rentalPackageName = rentalPackageName;
	}
	
	
	
}
