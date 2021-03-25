<template>
  <div>
    <form class="search" v-on:submit.prevent="searchCity">
      <section id="city-search">
        <h1>City:</h1>
        <input
          class="city"
          type="text"
          placeholder="City, State"
          v-model="search.city"
        />
      </section>
      <section id="cuisine-search">
        <h1>Cuisine:</h1>
        <input
          class="cuisine"
          type="text"
          placeholder="Cuisine"
          v-model="search.cuisine"
        />
      </section>
      <button
        class="search-button"
        v-on:click="
          searchCity();
          consoleLogData();
        "
      >
        Get Matching!
      </button>
    </form>
    <div class="tile-whole">
      <div class="back-tile">
        <p>Remember:</p>
        <p>Enter the city...</p>
        <p>Enter the cuisine...</p>
        <p>We'll take care of the rest!</p>
      </div>
      <div
        class="search-tile"
        v-for="data in restaurantResults"
        v-bind:key="data.id"
      >
        <h3>
          {{ data.restaurant.name }}
        </h3>
        <img
          class="featured_image"
          :src="data.restaurant.featured_image || 'Default.jpg'"
        />
        <p class="address">{{ data.restaurant.location.address }}</p>
        <p>average cost for two: ${{ data.restaurant.average_cost_for_two }}</p>
        <p>{{ data.restaurant.user_rating.aggregate_rating }}☆</p>
        <a class="link" v-bind:href="data.restaurant.url" target="_blank"
          >Restaurant Website</a
        >
        <div class="button-container" v-if="!enableAdd">
          <button class="mark-liked" v-on:click="saveRestaurant(data)">
            LIKE
          </button>
          <button
            class="mark-disliked"
            v-on:click="deleteRestaurant(data)"
          >
            DISLIKE
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import RestaurantService from "../services/RestaurantService.js";
import FavoriteService from "../services/FavoriteService.js";

export default {
  name: "search-results",
  props: {
    restaurant: Object,
    enableAdd: {
      type: Boolean,
      default: false,
    },
  },
  data() {
    return {
      search: {
        city: "",
        cuisine: "",
        cityID: "",
        results: "",
      },
      restaurantData: {
        resId: "",
        cityId: "",
        name: "",
        cuisines: "",
        priceRange: "",
        rating: "",
        image: "",
        address: "",
      },
      resId: "",
      restaurantResults: [],
    };
  },
  computed: {
    actionButtonDisabled() {
      if (this.selectedUserIDs.length > 0) {
        return false;
      }
      return true;
    },
  },
  methods: {
    searchCity() {
      RestaurantService.getCity(this.search.city).then((response) => {
        this.search.cityID = response.data.location_suggestions[0].id;

        RestaurantService.getCuisine(
          this.search.cityID,
          this.search.cuisine
        ).then((response) => {
          this.restaurantResults = response.data.restaurants;
        });
      });
    },
    consoleLogData() {
      RestaurantService.getCuisine(
        this.search.cityID,
        this.search.cuisine
      ).then((response) => {
        console.log(response.data);
      });
    },
    getAllRestaurants() {
      RestaurantService.getCuisine(
        this.search.cityID,
        this.search.cuisine
      ).then((response) => {
        this.search.results = response.data.restaurants;

        //   })
      });
    },
    saveRestaurant(data) {
      console.log("saving restaurant");
      const restaurantData = {
        resId: data.restaurant.R.res_id,
        cityId: data.restaurant.location.city_id,
        name: data.restaurant.name,
        cuisines: data.restaurant.cuisines,
        priceRange: data.restaurant.price_range,
        rating: data.restaurant.user_rating.aggregate_rating,
        image: data.restaurant.featured_image,
        address: data.restaurant.location.address,
      };
      if (data.restaurant.featured_image == "") {
        restaurantData.image = "Default.jpg";
      }
      console.log(restaurantData);
      FavoriteService.addOtherFavorite(this.$route.params.id, restaurantData)
        .then((response) => {
          console.log("Already in My Matches" + response.status);
          if (response.status === 201) {
            alert("added to faves!");
            this.restaurantResults.pop();
          }
        })
        .catch((error) => {
          if (error.response) {
            alert("You already liked this restaurant!");
            this.restaurantResults.pop();
          }
        });
    },
    deleteRestaurant: function () {
      this.restaurantResults.pop();
    },
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Oswald");

h1 {
  font-size: 51px;
  font-style: italic;
  font-weight: bolder;
  text-shadow: 3px 3px 1px #ffcccc;
  margin: 0 -6px;
  display: flex;
  color: #333;
}

h3 {
  font-size: 27px;
  font-style: italic;
  font-weight: bolder;
  text-shadow: 2px 2px 1px #ffcccc;
  margin: 0 0;
  display: flex;
  color: #333;
  justify-content: center;
}

form {
  position: relative;
  height: 420px;
  width: 150px;
  padding: 10px;
  border: 4.5px solid #333;
  border-radius: 6px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 3% 18%;
}

.city,
.cuisine,
.search-button {
  display: flex;
  justify-content: center;
  border: 3px solid #333;
  border-radius: 3px;
  margin: 20px 0;
}

.city,
.cuisine {
  height: 27px;
  width: 120px;
  font-size: 18px;
}

.search-button {
  height: auto;
  width: 132px;
  font-size: 21px;
  font-style: italic;
  font-weight: bolder;
  text-shadow: 2px 2px 1px #ffcccc;
  margin: 0px;
  display: flex;
  color: #333;
}

.questionnaire {
  font-size: 36px;
}

.search-tile {
  border: 6px solid #ffcccc;
  border-radius: 6px;
  box-shadow: 6px 6px 2px #dddddd;
  width: 360px;
  height: 570px;
  margin: 20px;
  word-wrap: break-word;
  position: fixed;
  z-index: auto;
  left: 50%;
  top: 61%;
  transform: translate(-50%, -50%);
}

.search-tile > h3 {
  font-size: 36px;
  display: flex;
  justify-content: center;
  font-size-adjust: inherit;
}

.search-tile > p {
  font-size: 18px;
  margin: 5px 10px;
  color: #333;
  font-size-adjust: inherit;
}

.search-tile.liked {
  background-color: lightgray;
}

div.button-container {
  display: flex;
  width: auto;
  height: auto;
  margin: 30px 50px 30px 50px;
  align-items: center;
  justify-content: space-around;
}

.mark-liked {
  font-size: 15px;
  font-weight: bolder;
  text-shadow: 1px 1px 1px #dddddd;
  height: auto;
  width: 60px;
  box-shadow: 3px 3px 1px #dddddd;
  border-radius: 6px;
}

.mark-disliked {
  font-size: 15px;
  font-weight: bolder;
  text-shadow: 1px 1px 1px #dddddd;
  height: auto;
  width: 60px;
  box-shadow: 3px 3px 1px #dddddd;
  border-radius: 6px;
}

.mark-disliked:hover {
  background-color: #ff6688;
}

.mark-liked:hover {
  background-color: #a7ff9c;
}

.search-button:hover {
  background-color: lightgrey;
}

.tile-whole {
  display: flex;
  flex-wrap: wrap;
  position: absolute;
}

.link {
  justify-content: center;
  font-size: 21px;
  margin-left: 10px;
}

.link:hover {
  font-size: 18px;
  text-shadow: 3px 3px 1px #ffcccc;
}

.featured_image {
  display: block;
  width: auto;
  max-width: 330px;
  max-height: 240px;
  height: auto;
  justify-content: center;
  margin: 15px auto;
}

.back-tile {
  border: 6px solid #ffcccc;
  border-radius: 6px;
  box-shadow: 6px 6px 2px #dddddd;
  width: 360px;
  height: 570px;
  margin: 20px;
  word-wrap: break-word;
  position: fixed;
  z-index: auto;
  left: 50%;
  top: 61%;
  transform: translate(-50%, -50%);
}

.back-tile > p {
  display: flex;
  justify-content: center;
  align-content: center;
  text-align: center;
  font-style: italic;
  font-weight: bolder;
  font-size: 33px;
  margin: 60px auto;
  color: #333;
  font-size-adjust: inherit;
}
</style>