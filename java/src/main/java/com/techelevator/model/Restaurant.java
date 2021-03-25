package com.techelevator.model;

import java.math.BigDecimal;

public class Restaurant {

	private long resId;
	private long cityId;
	private String name;
	private String cuisines;

	private BigDecimal priceRange;
	private float rating;
	private String image;
	private String address;

	public Restaurant(long resId, long cityId, String name, String cuisines, BigDecimal priceRange, float rating,
			String image, String address) {
		this.resId = resId;
		this.cityId = cityId;
		this.name = name;
		this.cuisines = cuisines;
		this.priceRange = priceRange;
		this.rating = rating;
		this.image = image;
		this.address = address;
	}

	public long getResId() {
		return resId;
	}

	public void setResId(long resId) {
		this.resId = resId;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisines() {
		return cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public BigDecimal getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(BigDecimal priceRange) {
		this.priceRange = priceRange;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Restaurant() {
		// TODO Auto-generated constructor stub
	}

}
