<template>

  <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Regístrate</p>

  <div class="row justify-content-center">
    <div class="col-md-10 col-lg-6 col-xl-5 order-2 order-lg-1">
      <form v-on:submit.prevent="processSignUp" class="mx-1 mx-md-4">
        <div class="d-flex flex-row align-items-center mb-4">
          <i class="fas fa-user fa-lg me-3 fa-fw"></i>
          <div class="form-outline flex-fill mb-0">
            <input v-model="user.username" type="text" id="form3Example1cxd" class="form-control" />
            <label class="form-label" for="form3Example1cxd">Tu nombre de Usuario</label>
          </div>
        </div>

        <div class="d-flex flex-row align-items-center mb-4">
          <i class="fas fa-lock fa-lg me-3 fa-fw"></i>
          <div class="form-outline flex-fill mb-0">
            <input v-model="user.password" type="password" id="form3Example4c" class="form-control" />
            <label class="form-label" for="form3Example4c">Tu contraseña</label>
          </div>
        </div>

        <div class="d-flex flex-row align-items-center mb-4">
          <i class="fas fa-key fa-lg me-3 fa-fw"></i>
          <div class="form-outline flex-fill mb-0">
            <input v-model="user.name" type="text" id="form3Example4cd" class="form-control" />
            <label class="form-label" for="form3Example4cd">Tu nombre</label>
          </div>
        </div>

        <div class="d-flex flex-row align-items-center mb-4">
          <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
          <div class="form-outline flex-fill mb-0">
            <input v-model="user.email" type="text" id="form3Example3c" class="form-control" />
            <label class="form-label" for="form3Example3c">Your Email</label>
          </div>
        </div>

        <div class="d-flex flex-row align-items-center mb-4">
          <i class="fas fa-user fa-lg me-3 fa-fw"></i>
          <div class="form-outline flex-fill mb-0">
            <input v-model="user.phone" type="text" id="form3Example1c" class="form-control" />
            <label class="form-label" for="form3Example1c">Tu número telefónico</label>
          </div>
        </div>

        <div class="d-flex justify-content-center mx-4 mb-3 mb-lg-4">
          <button type="submit" class="btn btn-primary btn-lg">Registrarse</button>
        </div>

      </form>

    </div>
    <div class="col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-1 order-lg-2">

      <img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-registration/draw1.png" class="img-fluid" alt="Sample image">

    </div>
  </div>

  
 


</template>

<script>
import gql from "graphql-tag";

export default {
  name: 'signUp',
    data: function() {
        return {
          user: {
              username: "",
              password: "",
              name: "",
              email: "",
              phone: "",
              admin: false
          },
        };
    },

  methods: {
    processSignUp: async function() {
      await this.$apollo
        .mutate({
          mutation: gql`
          mutation CreateUserProduct($userInput: SignUpInput!) {
            signUpUser(userInput: $userInput) {
              refresh
              access
            }
          }
          `,
          variables: {
            userInput: this.user,
          },
        })
        .then((result) => {
          let dataLogIn = {
            username: this.user.username,
            token_access: result.data.signUpUser.access,
            token_refresh: result.data.signUpUser.refresh,
          };

          this.$emit("completedSignUp", dataLogIn);
        })
        .catch((error) => {
          alert("ERROR: Fallo en el registro.");
        });
    },

  },  

}
</script>

<style>
/*
h2{
    text-align: center;
}
.container{
    height: 100%;
    width: 100%;
}

table{
    margin: 0 auto;
}

th{
    text-align: center;
}
*/

.vh-100{
      color: white;
    /*position: fixed;*/

    display: flex;
    flex-direction: column;
}

</style>