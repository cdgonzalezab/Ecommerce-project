<template>
  <div id="app" class="app">
    <div class="header">
      <h1>Merge<br /></h1>
      <nav>
        <button v-if="isAuth" v-on:click="loadHome"> Inicio </button>
        <button v-if="isAuth" v-on:click="loadAccount"> Cuenta </button>
        <button v-if="isAuth" v-on:click="loadTransaction"> Transacción </button>
        <button v-if="isAuth" v-on:click="logOut"> Cerrar Sesión </button>

        <button v-if="!isAuth" v-on:click="loadLogIn"> Iniciar Sesión </button>
        <button v-if="!isAuth" v-on:click="loadSignUp"> Registrarse </button>
        
      </nav>
    </div>

    <div class="main-component">
      <router-view
        v-on:completedLogIn="completedLogIn"
        v-on:completedSignUp="completedSignUp"
        v-on:logOut="logOut"
      >
      </router-view>
    </div>

    <div class="footer">
      <h2>La mejor tecnología la tiene <strong> MERGE</strong></h2>
    </div>
  </div>
</template>


<script>
  export default{
    name: 'App',

    data: function(){
      return {
        isAuth: false
      }
    },

    components:{
    },

    methods:{
      verifyAuth: function(){
        this.isAuth = localStorage.getItem("isAuth") || false;
        if(this.isAuth == false){
          this.$router.push({name: "login"})
        }
        else{
          this.$router.push({name: "home"});
        }
      },
      
      loadHome: function(){
        this.$router.push({name: "home"});
      },

      loadAccount: function(){
        this.$router.push({name: "account"});
      },

      loadTransaction: function(){
        this.$router.push({name: "transaction"});
      },

      logOut: function(){
        localStorage.clear();
        alert("Sesión terminada");
        this.verifyAuth();
      },

      loadLogIn: function(){
        this.$router.push({name: "login"})
      },

      loadSignUp: function(){
        this.$router.push({name: "signUp"})
      },

      completedLogIn: function(data){
        localStorage.setItem('username', data.username);
        localStorage.setItem('tokenRefresh', data.tokenRefresh);
        localStorage.setItem('tokenAccess', data.tokenAccess);
        localStorage.setItem('isAuth', true);
        alert("Autenticación exitosa");
        this.verifyAuth();
      },

      completedSignUp: function(data){
        alert("Registro exitoso");
        this.completedLogIn(data);
      },
      
    },

    created: function(){
      this.verifyAuth();
    }
  }
</script>

<style>
  body {
    max-width: 0 0 0 0;
  }

  .header{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 10vh;
    min-height: 50px;
    background-color: #19dad0 ;
    color:#E5E7E9 ;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .header h1{
    width: 15%;
    text-align: center;
  }

  .header nav {
    height: 100%;
    width: 15%;
    display: flex;
    justify-content: space-around;
    align-items: center;
    font-size: 20px;
  }

  .header nav button{
    color: #E5E7E9;
    background: #1de7cc;
    border: 1px solid #E5E7E9;
    border-radius: 5px;
    padding: 10px 20px;
  }

  .header nav button:hover{
    color: #1de7cc;
    background: #E5E7E9;
    border: 1px solid #E5E7E9;
  }

  .main-component{
    height: 75vh;
    margin: 0%;
    padding: 0%;
    background: #FDFEFE ;
    align-content: center;
  }

  .footer{
    margin: 0;
    padding: 0;
    width: 100%;
    height: 10vh;
    min-height: 100px;
    background-color: #1de7cc;
    color: #E5E7E9;
  }

  .footer h2{
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }
</style>
