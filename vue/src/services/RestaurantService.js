import axios from 'axios';

const http = axios.create( {
    baseURL: "https://developers.zomato.com/api/v2.1",
    headers: {'user-key': 'abd7024a0b74862cd55f49f2530017ae'}
});

export default {
    getCity(city){
        return http.get('/cities?q=' + city)
    },
    getCuisine(cityID, cuisine){
        return http.get('/search?entity_id=' + cityID +
        '&entity_type=city&q=' + cuisine)
    },
    addFavorite(restaurant){
        return axios.post('/restaurant', restaurant);
    },
    getFavorites(id){ //id = user_id
        return axios.get(`/favorite/${id}`);
    }



}

