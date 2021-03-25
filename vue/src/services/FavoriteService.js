import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080/api"
});

export default {
    addRestaurant(restaurant, id) {
        console.log(restaurant.resId);
        return http.post(`/restaurant/${id}`, restaurant);
    },

    addFavorite(id, resId) {
        console.log(id + ":" + resId);
        return http.post(`/favorite/${id}`, resId)
    },

    deleteFavorite(id, resId) {
        console.log(id + ":" + resId);
        console.log("Card successfully deleted");
        return http.delete(`/favorite/${id}/${resId}`)
    },

    addOtherFavorite(id, restaurant) {
        console.log(restaurant.resId)
        return http.post(`/restaurant/create/${id}`, restaurant);
    },

    list() {
        return http.get('/restaurant');
    },

    get(id) {
        return http.get(`/favorite/${id}`);
    },

    delete(id) {
        return http.delete(`/favorite/${id}`);
    }
};