package com.techelevator.dao;

import java.math.BigDecimal;
import java.util.List;
import com.techelevator.model.Restaurant;

public interface RestaurantDAO {

	Restaurant getRestaurant(long resId);

	Restaurant getOneRestaurantByCityId(int cityId);
	
	Restaurant getRestaurantId(long resId);

	List<Restaurant> getAllRestaurantsByCity(int cityId);

	List<Restaurant> getAllRestaurants();

	void createRestaurant(Restaurant restaurant);

	void updateRestaurant(Restaurant restaurantToUpdate);

	void removeRestaurant(int resId);
	
	void createARestaurant(long resId, long cityId, String name, String cuisines, BigDecimal priceRange, float rating, String image, String address);

	List<Restaurant> getRestaurantForUser(long id);

}
