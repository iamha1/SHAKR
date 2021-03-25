package com.techelevator.dao;

import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Favorite;

@Component
public class FavoriteJDBCDAO implements FavoriteDAO {

	private JdbcTemplate template;

	public FavoriteJDBCDAO(DataSource datasource) {
		template = new JdbcTemplate(datasource);
	}

	@Override
	public List<Favorite> getRestaurantById(int resId) {
		List<Favorite> allRestaurants = new ArrayList<>();
		String sqlGetFavorites = "SELECT * FROM favorite WHERE res_id =?";
		SqlRowSet result = template.queryForRowSet(sqlGetFavorites, resId);
		while (result.next()) {
			Favorite theFavorite = mapRowToFavorite(result);
		}
		return allRestaurants;
	}

	@Override
	public Favorite getSingleRestaurantById(int resId) {
		Favorite oneRestaurant = new Favorite();

		String sqlGetFavorites = "SELECT * FROM favorite WHERE res_id =?";
		SqlRowSet result = template.queryForRowSet(sqlGetFavorites, resId);
		if (result.next()) {
			oneRestaurant = mapRowToFavorite(result);
		}
		return oneRestaurant;
	}

	@Override
	public Favorite getSingleRestaurantByUser(int userId) {
		Favorite oneRestaurant = new Favorite();

		String sqlGetFavorites = "SELECT * FROM favorite WHERE user_id =?";
		SqlRowSet result = template.queryForRowSet(sqlGetFavorites, userId);
		if (result.next()) {
			oneRestaurant = mapRowToFavorite(result);
		}
		return oneRestaurant;
	}

	@Override
	public List<Favorite> getRestaurantByUserId(int userId) {
		List<Favorite> allRestaurantsById = new ArrayList<>();
		String sqlGetAllRestaurantById = "SELECT * FROM restaurant JOIN favorite ON favorite.res_id  = restaurant.res_id WHERE user_id = ?";
		SqlRowSet result = template.queryForRowSet(sqlGetAllRestaurantById, userId);
		while (result.next()) {
			Favorite theFavorite = mapRowToFavorite(result);
			allRestaurantsById.add(theFavorite);
		}
		return allRestaurantsById;
	}

	@Override
	public void createRestaurant(Favorite saveRestaurant) {
		String sqlAddRestaurant = "INSERT INTO favorite (user_id, res_id) VALUES (?, ?)";
		template.update(sqlAddRestaurant, saveRestaurant.getUserId(), saveRestaurant.getRestaurantId());
	}

	@Override
	public void createTheRestaurant(long userId, long resId) {
		String sqlAddRestaurant = "INSERT INTO favorite (user_id, res_id) VALUES (?, ?)";
		System.out.println("in Favorite JDBC DAO" + userId + " " + resId);
		template.update(sqlAddRestaurant, userId, resId);
	}

//	@Override
//	public void updateRestaurant(Favorite updateRestaurant) {
//		String sqlAddRestaurant = "UPDATE favorite SET restaurant_id=?, user_id=?, restaurant_name=?, cuisine=?, city_id=? WHERE username=?";	
//		template.update(sqlAddRestaurant, updateRestaurant.getRestaurantId(), updateRestaurant.getUserId());	
//	}

	@Override
	public void removeFavorite(int userId, int resId) {
		String sqlRemoveRestaurant = "DELETE FROM favorite WHERE user_id = ? and res_id = ?";
		template.update(sqlRemoveRestaurant, userId, resId);
	}

	@Override
	public List<Favorite> getAllFavRestaurant() {
		List<Favorite> allFavRestaurants = new ArrayList<>();
		String sqlGetAllFavRestaurant = "SELECT * FROM favorite WHERE username =?";
		SqlRowSet result = template.queryForRowSet(sqlGetAllFavRestaurant);
		while (result.next()) {
			Favorite theFavorite = mapRowToFavorite(result);
		}
		return allFavRestaurants;
	}

	private Favorite mapRowToFavorite(SqlRowSet result) {

		Favorite theFavorite = new Favorite();

		theFavorite.setUserId(result.getInt("user_id"));
		theFavorite.setRestaurantId(result.getInt("res_id"));
		return theFavorite;
	}

	private List<Favorite> mapResultToFavorite(SqlRowSet result) {
		List<Favorite> retrievedFavorites = new ArrayList<>();
		while (result.next()) {
			retrievedFavorites.addAll(mapResultToFavorite(result));
		}

		return retrievedFavorites;
	}

	@Override
	public void updateRestaurant(Favorite updateRestaurant) {
		// TODO Auto-generated method stub

	}

}
