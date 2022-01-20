<template>

    <p class="text-center h1 fw-bold mb-5 mx-1 mx-md-4 mt-4">Ingresa</p>
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-md-9 col-lg-6 col-xl-5">
            <img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/draw2.png" class="img-fluid"
            alt="Sample image">
        </div>
        <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
            <form v-on:submit.prevent="processLogInUser">
            <div class="divider d-flex align-items-center my-4">
                <p class="text-center fw-bold mx-3 mb-0">Ingresar</p>
            </div>

            <!-- Email input -->
            <div class="form-outline mb-4">
                <input type="text" id="form3Example3" class="form-control form-control-lg"
                v-model="user.username"
                placeholder="Ingresa un username" />
                <label class="form-label" for="form3Example3">Username </label>
            </div>

            <!-- Password input -->
            <div class="form-outline mb-3">
                <input type="password" id="form3Example4" class="form-control form-control-lg"
                v-model="user.password"
                placeholder="Ingresa contraseña" />
                <label class="form-label" for="form3Example4">Password</label>
            </div>

            <div class="text-center text-lg-start mt-4 pt-2">
                <button type="submit" class="btn btn-primary btn-lg"
                 
                style="padding-left: 2.5rem; padding-right: 2.5rem;" >Ingresar</button>
                <p class="small fw-bold mt-2 pt-1 mb-0">¿No tienes una cuenta? <a href="/SignUp"
                    class="link-danger">Rgístrate</a></p>
            </div>

            </form>
        </div>
        </div>
    </div>


</template>

<script>
import gql from "graphql-tag";

export default {
    name: "SignUp",

    data: function(){
        return {
            user: {
                username:"",
                password:""
            },
        }
    },

    methods: {
        processLogInUser: async function() {
        await this.$apollo
            .mutate({
                mutation: gql`
                    mutation($credentials: CredentialsInput!) {
                    logIn(credentials: $credentials) {
                        refresh
                        access
                    }
                    }
                `,
                variables: {
                    credentials: this.user,
                },
                })
                .then((result) => {
                
                let dataLogIn = {
                    token_access: result.data.logIn.access,
                    token_refresh: result.data.logIn.refresh,
                };
                this.$emit("completedLogIn", dataLogIn);
                })
                .catch((error) => {
                alert("ERROR 401: Credenciales Incorrectas.");
                });
        },

    },
    created: async function(){
        
    }

}
</script>


<style>
.divider:after,
.divider:before {
  content: "";
  flex: 1;
  height: 1px;
  background: #eee;
}
.h-custom {
  height: calc(100% - 73px);
}
@media (max-width: 450px) {
  .h-custom {
    height: 100%;
  }
}

</style>