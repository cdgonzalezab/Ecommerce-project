<template>

    <div class="container-grid">
        <h2>Usuarios</h2>
        <br>
        <div class="container" >
            <table class="table table-hover" >
            <thead>
                <tr>
                    <th scope="col">id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Email</th>
                    <th scope="col">Teléfono</th>
                    <th scope="col">IsAdmin?</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="userOne in users" v-bind:key="userOne.id">
                    <th scope="row">{{userOne.id}}</th>
                    <td>{{userOne.username}}</td>
                    <td>{{userOne.name}}</td>
                    <td>{{userOne.email}}</td>
                    <td>{{userOne.phone}}</td>
                    <td>{{userOne.admin}}</td>
                    <td>
                        <button v-on:click="getModiUserId(userOne)" type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal1" data-bs-whatever="@mdo">Modificar</button>
                    </td>

                </tr>
            </tbody>
            </table>
        </div>
    </div>

    <div class="modal fade" id="exampleModal1" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Escribe datos para modificar usuario "<span>{{userModi.username}}</span>"</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

                    
                <br>
                <form class="mx-1 mx-md-4">
                    <br>
                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                        <input v-model="modifyUser.username" type="text" id="form3Example1c" class="form-control" :placeholder="[[userModi.username]]" />
                        <label class="form-label" for="form3Example1c">Nombre de Usuario</label>
                    </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                        <input v-model="modifyUser.password" type="password" id="form3Example4cdf" class="form-control"/>
                        <label class="form-label" for="form3Example4cdf">Contraseña</label>
                    </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                        <input v-model="modifyUser.name" type="text" id="form3Example4cd" class="form-control" :placeholder="[[userModi.name]]" />
                        <label class="form-label" for="form3Example4cd">Nombre</label>
                    </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                        <input v-model="modifyUser.email" type="text" id="form3Example3c" class="form-control" :placeholder="[[userModi.email]]" />
                        <label class="form-label" for="form3Example3c">Email</label>
                    </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                    <div class="form-outline flex-fill mb-0">
                        <input v-model="modifyUser.phone" type="text" id="form3Example1c1" class="form-control" :placeholder="[[userModi.phone]]" />
                        <label class="form-label" for="form3Example1c1">Número telefónico</label>
                    </div>
                    </div>

                    <div class="d-flex flex-row align-items-center mb-4">
                    <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                    <select class="dropDownCentroOpciones" v-model="modifyUser.admin" :placeholder="[[userModi.admin]]">
                        <option selected :value="true">Admin</option>
                        <option :value="false">No admin</option>
                    </select>    
                    </div>

                </form>

            <div class="modal-footer">
                <button data-bs-dismiss="modal" type="button" class="btn btn-primary" v-on:click="modifyAnUser">Modificar</button>
            </div>
            </div>
        </div>
    </div>    

</template>


<script>   
import gql from "graphql-tag";

export default {
    name: "AdminUsers",

    data: function(){
        return {
            userOne: [],
            users: [],
            user: {
                id: "",
                username: "",
                name: "",
                email: "",
                phone: "",
                admin: "",
            },
            modifyUser: {
                username: "",
                password: "",
                name: "",
                email: "",
                phone: "",
                admin: false,
            },
            userModi: 0
        }
    },

    methods: {
        getUserList: async function(){
            await this.$apollo
            .query({
            query: gql`
                query UserList {
                    userList {
                        id
                        username
                        name
                        email
                        phone
                        admin
                    }
                }
                `,
                    
            })
            .then((result) => {
                this.users = result.data.userList;

            })
            .catch((error) => {
            alert("ERROR: Fallo geUserData");
            });
        },

        getModiUserId: function(user){
            this.userModi = user;
            console.log(this.userModi);
        },

        modifyAnUser: async function(){
            await this.$apollo
                .mutate({
                mutation: gql`
                mutation UpdateUser($putUser: SignUpInput!, $userId: Int) {
                    updateUser(putUser: $putUser, userId: $userId) {
                        id
                        username
                        name
                        email
                        phone
                        admin
                    }
                }
                `,
                variables:{
                    userId: this.userModi.id,
                    putUser: this.modifyUser
                }
            })
            .then((result) => {
                alert("Producto "+result.data.updateUser.id+" Modificado");
                this.getUserList();
                //this.$forceUpdate();
                this.modifyUser.username = "",
                this.modifyUser.password = "",
                this.modifyUser.name = "",
                this.modifyUser.email = "",
                this.modifyUser.phone = 0,
                this.modifyUser.admin = false
            })
            .catch((error) => {
                console.log(error)
                alert("ERROR: Fallo modificando producto");
            }); 

        }
    },
    created: async function(){
        this.getUserList();
    }
}


</script>


<style>
    *, ul , li {
        list-style: none;
    }
    ul li > a {
        text-decoration: none;
        color: #000;
    }
    ul {
        display: flex;
        justify-content: center;
        gap: 15px;
    }
    .greetings{
        margin: 0;
        padding: 0%;
        height: 100%;
        width: 100%;
    
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 9.5em;
    }

    .greetings h1{
        font-size: 50px;
        color: #283747;
    }

    .greetings span{
        color: crimson;
        font-weight: bold;
    }

    .container-grid {
        display: grid;
        grid: repeat(2, 1fr);
    }

    .btn .show:active {
        background: rgb(247, 63, 247);
    }

    .signUp_user{
        margin: 40px 0;
    }

    .container_signUp_user {
        border: 3px solid  #5b06a0;
        border-radius: 10px;
        width: 50%;
    }

    .signUp_user form{
        width: 95%;
    }

    .signUp_user input{
        height: 40px;
        width: 100%;
        box-sizing: border-box;
        padding: 10px 20px;
        margin: 5px 0;
        border: 1px solid #5b06a0;
    }

    .signUp_user button{
        width: 100%;
        height: 40px;
        color: #E5E7E9;
        background: #5b06a0;
        border: 1px solid #E5E7E9;
        border-radius: 5px;
        padding: 10px 25px;
        margin: 5px 0 25px 0;
    }

    .signUp_user button:hover{
        color: #E5E7E9;
        background: crimson;
        border: 1px solid #5b06a0;
    }

    textarea {
        width: 100%;
    }

    .dropDownCentroOpciones{
        width: 100%;
        width: 100%;
        height: 40px;
        border: 1px solid #9e9e9e;
        border-radius: 5px;
        padding: 10px 25px;
        margin: 5px 0 25px 0;
    }

</style>