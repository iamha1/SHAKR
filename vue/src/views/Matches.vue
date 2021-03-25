<template>
<div>
    <h1 class="faves">Your Favorite Restaurants</h1>
    <display-favorites/>
    </div>
</template>

<script>
import DisplayFavorites from '../components/DisplayFavorites.vue';

export default {
    components: { DisplayFavorites },
}
</script>

<style>

.faves {
    display: flex;
    justify-content: center;
    align-content: center;
    border: 4.5px solid #333;
    border-radius: 6px;
    margin: 0 30% 0 30%;
    padding: auto;
}



</style>