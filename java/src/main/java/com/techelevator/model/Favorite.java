package com.techelevator.model;

public class Favorite {

	private int userId;
	private int resId;

	public Favorite(int userId, int resId) {

		this.userId = userId;
		this.resId = resId;
	}

	public Favorite() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return resId;
	}

	public void setRestaurantId(int resId) {
		this.resId = resId;
	}

}
