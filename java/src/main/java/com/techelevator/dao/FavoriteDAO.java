package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Favorite;
import com.techelevator.model.Restaurant;

public interface FavoriteDAO {

	List<Favorite> getRestaurantById(int resId);

	Favorite getSingleRestaurantById(int resId);

	Favorite getSingleRestaurantByUser(int userId);

	List<Favorite> getRestaurantByUserId(int userId);

	List<Favorite> getAllFavRestaurant();

	void createRestaurant(Favorite saveRestaurant);

	void updateRestaurant(Favorite updateRestaurant);
	
	void removeFavorite(int userId, int resId);

	void createTheRestaurant(long userId, long resId);
}
