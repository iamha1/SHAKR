<template>
  <div id="app">
  <div class="shaker-logo">
    <div class="animate__animated animate__shakeY">
    <img class="shaker-art" src="SaltShaker.png" /> 
    </div>
    <img class="logo" src="ShakrLogo.png" />
  </div>
    <nav>
      <router-link v-bind:to="{ name: 'home' }">Home</router-link>
      <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      <router-link v-bind:to="{ name: 'matches', params: { id: $store.state.userId } }">My Matches</router-link>
      <router-link v-bind:to="{ name: 'search', params: { id: $store.state.userId } }">Search</router-link>
    </nav>
    <router-view />
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Oswald');

*{
background-color: #fffdf7;
font-family: 'Oswald';
}

a {
  font-style: italic;
  text-decoration: none;
  text-decoration-color: none;
  color: #333;
  font-weight: bolder;
}

a:hover {
  font-size: 52.5px;
  text-shadow: 3px 3px 1px #FFCCCC;
}

nav {
  font-size: 51px;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin: 0 12% 3% 12%;
  text-decoration: none;
  text-decoration-color: none;
}

.shaker-logo {
  display: flex;
  justify-content: center;
  align-items: center;
  height: auto;
  margin: 0 0;
}

 .animate__animated {
  animation-duration: 1.5s; 
  transition-property: all;
 }

.animate__animated:hover {
  transform:rotateZ(180deg); 
  animation: shake;
}

.shaker-art {
  height: 108px;
}

.logo {
  height: 180px;
}

</style>
