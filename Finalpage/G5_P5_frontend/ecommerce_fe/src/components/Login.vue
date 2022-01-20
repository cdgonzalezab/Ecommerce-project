<template>
    <div class="loginUser">
        <div class="containerLoginUser">
            <h2>Iniciar sesión</h2>
            <img src="../../logos/E5.png" alt="logo" class="logo" place-items: center>
              
                <form action="/" class="form">
                  <form v-on:submit.prevent="processLogInUser" method="POST">
                  <p>Usuario</p>
                  <input type="text" v-model="user.username" placeholder="Username" class="input input-email">

                  <p><strong>Contraseña</strong></p>
                  <input type="password" v-model="user.password" placeholder="*********" class="input input-password">
                <input type="submit" value="Ingresar" class="primary-button login-button">
                  
                </form>
                 <a href="/">Olvidé mi contraseña</a>
            </form>
        </div>
    </div>
</template>

<script>
    import gql from 'graphql-tag';
    export default {
        name: "LogIn",
        
        data: function(){
            return {
                user: {
                    username : "",
                    password : ""
                }
            }
        },

        methods: {
            processLogInUser: async function(){
                console.log(this.user);
                await this.$apollo.mutate(
                    {
                        mutation: gql`
                            mutation Mutation($credentials: CredentialsInput!) {
                                logIn(credentials: $credentials) {
                                    refresh
                                    access
                                }
                            }
                        `,
                        variables: {
                            credentials: this.user
                        }
                    }
                )
                .then((result) => {
                    let dataLogIn = {
                        username     : this.user.username,
                        tokenAccess  : result.data.logIn.access,
                        tokenRefresh : result.data.logIn.refresh,
                    }
                    this.$emit('completedLogIn', dataLogIn)
                })
                .catch((error) => {
                    console.log(error);
                    alert("ERROR 401: Credenciales Incorrectas.");
                });
            }
        }
    }
</script>


<style>
    :root {
      --white: #FFFFFF;
      --black: #000000;
      --very-light-pink: #C7C7C7;
      --text-input-field: #F7F7F7;
      --hospital-green: #ACD9B2;
      --sm: 14px;
      --md: 16px;
      --lg: 18px;
    }
    body {
      margin: 0;
      font-family: 'Quicksand', sans-serif;
    }
    .loginUser {
      width: 100%;
      height: 100vh;
      display: block;
      place-items: center;
    }
    .containerLoginUser {
      display: grid;
      grid-template-rows: auto 1fr auto;
      width: 300px;
      place-items: center;
      position: absolute;
      left:40%;
    }
    .logo {
      width: 150px;
      margin-bottom: 48px;
      justify-self: center;
    }
    .form {
      display: flex;
      flex-direction: column;
      place-items: center;
      
    }
    .form a {
      color: var(--hospital-green);
      font-size: var(--sm);
      text-align: center;
      text-decoration: none;
      margin-bottom: 52px;
    }
    .label {
      font-size: var(--sm);
      font-weight: bold;
      margin-bottom: 4px;
      place-items: center;
    }
    .input {
      background-color: var(--text-input-field);
      border: none;
      border-radius: 8px;
      height: 30px;
      font-size: var(--md);
      padding: 6px;
      margin-bottom: 12px;
      place-items: center;
    }
    .input-email {
      margin-bottom: 22px;
      place-items: center;
    }
    .primary-button {
      background-color: var(--hospital-green);
      border-radius: 8px;
      border: none;
      color: var(--white);
      width: 100%;
      cursor: pointer;
      font-size: var(--md);
      font-weight: bold;
      height: 50px;
      place-items: center;
    }
    .secondary-button {
      background-color: var(--white);
      border-radius: 8px;
      border: 1px solid var(--hospital-green);
      color: var(--hospital-green);
      width: 100%;
      cursor: pointer;
      font-size: var(--md);
      font-weight: bold;
      height: 50px;
      place-items: center;
    }
    .login-button {
      margin-top: 14px;
      margin-bottom: 30px;
      place-items: center;
    }
    @media (max-width: 640px) {
      .logo {
        display: block;
        place-items: center;
      }
    }
  </style>

