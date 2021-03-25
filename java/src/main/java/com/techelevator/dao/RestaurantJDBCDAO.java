package com.techelevator.dao;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Restaurant;

@Component
public class RestaurantJDBCDAO implements RestaurantDAO {

	private JdbcTemplate template;

	public RestaurantJDBCDAO(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		List<Restaurant> showAllRests = new ArrayList<>();
		String sqlGetAllRests = "SELECT * FROM restaurant";
		SqlRowSet result = template.queryForRowSet(sqlGetAllRests);
		while (result.next()) {
			showAllRests.add(mapRowToRestaurant(result));
		}
		return showAllRests;
	}

	@Override
	public List<Restaurant> getRestaurantForUser(long id) {
		List<Restaurant> allRestaurantsByUser = new ArrayList<>();
		String sqlRestaurantsForUser = "SELECT * FROM restaurant JOIN favorite ON favorite.res_id  = restaurant.res_id WHERE user_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlRestaurantsForUser, id);

		while (result.next()) {
			Restaurant allRestaurants = mapRowToRestaurant(result);
			allRestaurantsByUser.add(allRestaurants);
		}
		return allRestaurantsByUser;
	}

	@Override
	public void createARestaurant(long resId, long cityId, String name, String cuisines, BigDecimal priceRange,
			float rating, String image, String address) {
		String sqlAddRestaurant = "INSERT INTO restaurant(res_id, city_id, name, cuisines, price_range, rating, image, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		template.update(sqlAddRestaurant, resId, cityId, name, cuisines, priceRange, rating, image, address);
	}

	@Override
	public void createRestaurant(Restaurant restaurant) {
		String sqlAddRestaurant = "INSERT INTO restaurant(res_id, city_id, name, cuisines, price_range, rating, image, address) VALUES(?, ?, ?, ?, ?, ?, ?, ?);";
		template.update(sqlAddRestaurant, restaurant.getResId(), restaurant.getCityId(), restaurant.getName(),
				restaurant.getCuisines(), restaurant.getPriceRange(), restaurant.getRating(), restaurant.getImage(),
				restaurant.getAddress());
	}

	@Override
	public Restaurant getRestaurantId(long resId) {
		Restaurant getId = new Restaurant();
		String sqlGetId = "SELECT res_id FROM restaurant WHERE res_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlGetId, resId);
		if (result.next()) {
			getId = mapRowToRestaurant(result);
		}
		return getId;
	}

	@Override
	public Restaurant getRestaurant(long resId) {
		Restaurant getOne = new Restaurant();
		String sqlGetRestaurant = "SELECT * FROM restaurant WHERE res_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlGetRestaurant, resId);
		if (result.next()) {
			getOne = mapRowToRestaurant(result);
		}
		return getOne;
	}

	@Override
	public Restaurant getOneRestaurantByCityId(int cityId) {
		Restaurant getOneByCity = new Restaurant();
		String sqlGetRestaurant = "SELECT * FROM restaurant WHERE city_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlGetRestaurant, cityId);
		if (result.next()) {
			getOneByCity = mapRowToRestaurant(result);
		}
		return getOneByCity;
	}

	@Override
	public List<Restaurant> getAllRestaurantsByCity(int cityId) {
		List<Restaurant> getAllRestaurants = new ArrayList<>();
		String sqlGetAll = "SELECT * FROM restaurant WHERE city_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlGetAll, cityId);
		while (result.next()) {
			Restaurant theRestaurant = mapRowToRestaurant(result);
		}
		return getAllRestaurants;
	}

	@Override
	public void updateRestaurant(Restaurant restaurantToUpdate) {
		String sqlUpdateRest = "UPDATE restaurant SET name = ?, cuisines = ? WHERE res_id = ?";
		template.update(sqlUpdateRest, restaurantToUpdate.getName(), restaurantToUpdate.getCuisines(),
				restaurantToUpdate.getResId());

	}

	@Override
	public void removeRestaurant(int resId) {
		String sqlRemoveRest = "DELETE FROM restaurant WHERE res_id = ?";
		template.update(sqlRemoveRest, resId);

	}

	private Restaurant mapRowToRestaurant(SqlRowSet result) {
		Restaurant theRestaurant = new Restaurant();
		theRestaurant.setResId(result.getLong("res_id"));
		theRestaurant.setCityId(result.getLong("city_id"));
		theRestaurant.setName(result.getString("name"));
		theRestaurant.setCuisines(result.getString("cuisines"));
		theRestaurant.setPriceRange(result.getBigDecimal("price_range"));
		theRestaurant.setRating(result.getFloat("rating"));
		theRestaurant.setImage(result.getString("image"));
		theRestaurant.setAddress(result.getString("address"));

		return theRestaurant;
	}

}
