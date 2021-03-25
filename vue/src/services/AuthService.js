import axios from 'axios';

export default {

  login(user) {
    return axios.post('http://localhost:8080/login', user)
  },

  register(user) {
    return axios.post('http://localhost:8080/register', user)
  }

}
