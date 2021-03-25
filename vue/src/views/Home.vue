<template>
  <div class="home">
    <h1 class="text" id="title">Find Your Soul(food)mate!</h1>
  <div class="About">
    <div class="text" id="description">
    <h2>How It Works :</h2>
      <p>Enter the city and state in which you're located and the style of food you've got a hankering for. After we've got this information, 
        we'll find your perfect match(es). From there, you will select either "like"
        or "dislike". The restaurants you "like", will be stored on the My Matches page, where you will be able to review them and make the final decision of what's for 
        breakfast/dinner/lunch. Click <router-link class="search-link" v-bind:to="{ name: 'search', params: { id: $store.state.userId } }">here</router-link>  to get started!
      </p>
  </div>
  </div>
  </div>
</template>

<script>
export default {
  name: "home"
};
</script>

<style>

.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #333;
}

#title {
  font-size: 54px;
}

.text {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  flex-wrap: wrap;
  width: 540px;
  border: 4.5px solid #333;
  border-radius: 6px;
  padding: 15px;
  font-size: 21px;
}

#description {
  margin-top: 30px;
}

h2 {
  margin-top: -10px;
  font-size: 36px;
}

p {
  margin-top: -10px;
}

.search-link:hover, .matches-link:hover {
  font-size: 21px;
  color: #ffcccc;
  text-shadow: none;
}

</style>
