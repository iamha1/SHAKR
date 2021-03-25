<template>
  <div id="main">
    <div class="tile-whole">
      <div class="faves-tile" v-for="restaurant in allRestaurants" :key="restaurant.id">
         <h3>
        {{ restaurant.name }}
        </h3>
        <p class="tile-cuisine">Cuisine: {{ restaurant.cuisines }}</p>
        <p> Address: {{ restaurant.address }}</p>
        <p>Price Range: {{ restaurant.priceRange }}</p>
       <p> Rating: {{ restaurant.rating }}</p>
       <!-- <p> Image: ~Insert Image Here~ {{ restaurant.image }} </p>  -->
        <img class="image" :src=restaurant.image />
        <br />
        <button class="mark-discard" v-on:click="deleteFavorite(restaurant)">DISCARD</button>
      </div>
    </div>
  </div>
</template>

<script>
import FavoriteService from '../services/FavoriteService.js';



export default {
    data() {
        return {
            allRestaurants: [], favoriteRest: {} // added favoriteRest object here
        };
    },
        created() {
            FavoriteService
          .get(this.$route.params.id)
          .then(restaurantData => {
            console.log("Display favorites id" + this.$route.params.id);
            console.log(restaurantData);
            this.allRestaurants = restaurantData.data;
          })
          .catch((err) => {
            console.error(err + " uh oh missing data!");
          });
        },
    methods: {
        deleteFavorite(restaurant) { 
          console.log("Display user Id " + this.$route.params.id);
          console.log("Display resturant Id " + restaurant.resId);
        if (
            confirm(
                "Are you sure you want to delete this restaurant from your favorites?"
          )
        ) {
            FavoriteService.deleteFavorite(this.$route.params.id, restaurant.resId).then(response => {
            if (response.status === 204) {
              alert("Card successfully deleted");
              // this.$router.push("/matches/" + this.$route.params.id);
              this.$router.push("/");
              // location.reload();
            }
          })
          .catch(error => {
            if (error.response) {
              this.errorMsg =
                "Error deleting card. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting card. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting card. Request could not be created.";
            }
          });
      }
    },
}
}
</script>

<style>
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



.faves-tile {
  border: 3px solid #ffcccc;
  border-radius: 3px;
  box-shadow: 6px 6px 2px #dddddd;
  width: 300px;
  height: 420px;
  margin: 30px;
  word-wrap: break-word;
  position: relative;
}

.tile-whole {
  display: flex;
  justify-content: center;
  margin: 20px 120px;
}


.faves-tile > h1 {
  display: flex;
  justify-content: center;
}

/* .card > p {
  margin: 5px 10px;
} */


.faves-tile > p {
  margin: 6px 12px;
}

.faves-tile.liked {
  background-color: lightgray;
}

.faves-tile-whole {
  display: flex;
}

.mark-discard {
  display: flex;
  font-size: 15px;
  font-weight: bolder;
  text-shadow: 1px 1px 1px #dddddd;
  height: auto;
  width: 100px;
  box-shadow: 3px 3px 1px #dddddd;
  border-radius: 100px;
  padding-top: 0px;
  
  margin: auto;
  justify-content: center;
}

.mark-discard:hover {
  background-color: #ff6688;
}

.image {
  display: block;
  width: 210px;
  height: auto;
  justify-content: center;
  margin: 30px auto;
}
</style>