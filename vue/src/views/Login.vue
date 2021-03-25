<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="sign-in">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      ></div>
      <label for="username" class="sr-only"></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only"></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <router-link class="register" :to="{ name: 'register' }">Need an <br/> account?</router-link>
      <button class="btn-sign-in" type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>
<style>
  .form-signin {
    display: flex;
    flex-direction: row;
    height: auto;
    width: 1008px;
    margin: auto;
    padding: auto;
    justify-content: space-around;
    align-items: center;
  }

  input {
    height: 45px;
    font-size: 21px;
    border: 3px solid #333;
    border-radius: 6px;
  }

  .register:hover {
    font-size: 18px;
  }

  .btn-sign-in {
    height: 45px;
    border: 3px solid #333;
    border-radius: 3px;
    font-size: 21px;
    color:#333;
  }

  .btn-sign-in:hover {
    background-color: #DDDDDD;
  }
</style>
