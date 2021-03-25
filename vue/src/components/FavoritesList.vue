<template>
  <div class="faves-container">
    <search v-bind:restaurant="restaurant" v-for="restaurant in $store.state.restaurants" v-bind:key="restaurant.name" />
  </div>
</template>

<script>
import Search from '../components/Search';

export default {
    name: 'favorites-list',
    components: {
        Search
    },
    methods: {
    }
}

</script>

<style>
.book-container {
    display:flex;
    justify-content: space-evenly;
    flex-wrap: wrap;
}
</style>