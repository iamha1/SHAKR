package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.FavoriteDAO;
import com.techelevator.dao.RestaurantDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.model.Favorite;
import com.techelevator.model.Restaurant;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RestaurantMainController {

	private RestaurantDAO theRestaurant;
	private FavoriteDAO theFavorite;
	private UserDAO theUser;

	public RestaurantMainController(RestaurantDAO theRestaurant, FavoriteDAO theFavorite, UserDAO theUser) {
		this.theRestaurant = theRestaurant;
		this.theFavorite = theFavorite;
		this.theUser = theUser;
	}

	@RequestMapping(path = "/favorite/{id}", method = RequestMethod.GET)
	public List<Restaurant> getTransferById(@PathVariable int id) {
		List<Restaurant> possibleObj = theRestaurant.getRestaurantForUser(id);
		System.out.println(possibleObj);

		System.out.println(theRestaurant.getRestaurantForUser(id));
		return possibleObj;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(path = "/favorite/{id}/{resId}", method = RequestMethod.DELETE)
	public void removeRestaurantById(@PathVariable("id") int userId, @PathVariable int resId) {
		System.out.println("Deleting Restaurant" + userId + " " + resId);
		theFavorite.removeFavorite(userId, resId);
	}

	@RequestMapping(path = "/restaurant", method = RequestMethod.GET)
	public List<Restaurant> showAllRests() {
		List<Restaurant> allRests = theRestaurant.getAllRestaurants();

		return allRests;
	}

	@RequestMapping(path = "/restaurant/{id}", method = RequestMethod.GET)
	public Restaurant getOneRest(@PathVariable int id) {

		return theRestaurant.getRestaurant(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/restaurant/{id}", method = RequestMethod.POST) // path should be changed to search
	public void createOneRestaurant(@RequestBody Restaurant restaurant, @PathVariable int id) {
		System.out.println("inController" + restaurant.getResId());
		theRestaurant.createRestaurant(restaurant);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/favorite/{id}", method = RequestMethod.POST)
	public void createOneFavorite(@RequestBody Restaurant restaurant, @PathVariable int id) {
		System.out.println("inUser" + id);
		System.out.println(restaurant.getResId());
		theFavorite.createTheRestaurant(id, restaurant.getResId());

	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/favorite", method = RequestMethod.POST)
	public void createFavorite(Favorite restaurant) {
		System.out.println("inUser" + restaurant.getUserId());
		theFavorite.createRestaurant(restaurant);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/restaurant/create/{id}", method = RequestMethod.POST)
	public void checkRest(@RequestBody Restaurant restaurant, @PathVariable int id) {
		long resId = restaurant.getResId();
		Restaurant possibleObj = theRestaurant.getRestaurant(restaurant.getResId());
		System.out.println("in controller - resId : " + possibleObj.getResId());
		if (resId != possibleObj.getResId()) {
			System.out.println("inController checkRest" + resId);
			System.out.println("Inside");
			theRestaurant.createARestaurant(resId, restaurant.getCityId(), restaurant.getName(),
					restaurant.getCuisines(), restaurant.getPriceRange(), restaurant.getRating(), restaurant.getImage(),
					restaurant.getAddress());
		}
		System.out.println("inUser" + id);
		theFavorite.createTheRestaurant(id, resId);
	}

}
