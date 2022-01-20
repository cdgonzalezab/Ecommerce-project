
<template>
    <div class="signUpUser">
    <div class="containerSignUpUser">
      <img src="../../logos/E5.png" alt="logo" class="logo">
      <h1 class="title">Mi cuenta</h1>
      <form action="/" class="form">
        <div>
          <p>Nombre</p>
          <input type="text" id="name" placeholder="Escriba su nombre completo" class="input input-name">
          <p>Email</p>
          <input type="text" id="email" placeholder="correo@example.com" class="input input-email">
          <p>Contraseña</p>
          <input type="password" id="password" placeholder="*********" class="input input-password">
        </div>
        <input type="submit" value="Crear" class="primary-button login-button">
      </form>
    </div>
  </div>

</template>

<script>
    import gql from 'graphql-tag';
    export default {
        name: "SignUp",

        data: function(){
            return {
                user: {
                    username : "",
                    password : "",
                    name     : "",
                    email    : "",
                }
            }
        },

        methods: {
            processSignUp: async function(){
                await this.$apollo.mutate(
                    {
                        mutation: gql`
                            mutation Mutation($userInput: SignUpInput) {
                                signUpUser(userInput: $userInput) {
                                    refresh
                                    access
                                }
                            }
                        `,
                        variables:{
                            userInput: this.user,
                        }
                    }
                )
                .then((result) => {
                    let dataSignUp = {
                        username     : this.user.username,
                        tokenRefresh : result.data.signUpUser.refresh,
                        tokenAccess  : result.data.signUpUser.access
                    };

                    this.$emit("completedSignUp", dataSignUp);
                })
                .catch((error) => {
                    console.log(error);
                    alert("ERROR. Fallo en el registro. Intente de nuevo.")
                })
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
    .signUpUser {
      width: 100%;
      height: 100vh;
      display: grid;
      place-items: center;
    }
    .containerSignUpUser {
      display: grid;
      grid-template-rows: auto 1fr auto;
      width: 300px;
      top:100px;
      position: absolute;
    }
    .logo {
      width: 150px;
      margin-bottom: 48px;
      justify-self: center;
    }
    .form {
      display: flex;
      flex-direction: column;
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
    }
    .input {
      background-color: var(--text-input-field);
      border: none;
      border-radius: 8px;
      height: 30px;
      font-size: var(--md);
      padding: 6px;
      margin-bottom: 12px;
    }
    .input-email {
      margin-bottom: 22px;
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
    }
    .login-button {
      margin-top: 14px;
      margin-bottom: 30px;
    }
    @media (max-width: 640px) {
      .logo {
        display: block;
      }
    }
  </style>