<template>
    <br>
    <h2>Mis Datos</h2>
    <br>
    <div class="container">
		<div class="main-body">
            <div class="card-body">

                <div class="row mb-3">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Username</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <input type="text" v-model="modifyUser.username" class="form-control" :placeholder="[[userInfo.username]]">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Nombre</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <input type="text" v-model="modifyUser.name" class="form-control" :placeholder="[[userInfo.name]]">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Email</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <input type="email" v-model="modifyUser.email" class="form-control" :placeholder="[[userInfo.email]]">
                    </div>
                </div>

                <div class="row mb-3">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Telefono</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <input type="number" v-model="modifyUser.phone" class="form-control" :placeholder="[[userInfo.phone]]">
                    </div>
                </div>

                <div class="row mb-3" v-if="userInfo.admin">
                    <div class="col-sm-3">
                        <h6 class="mb-0">Estado Admin</h6>
                    </div>
                    <div class="col-sm-9 text-secondary">
                        <input type="text" v-model="modifyUser.admin" class="form-control" :placeholder="[[userInfo.admin]]">
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-9 text-secondary">
                        <input type="button" class="btn btn-primary px-4" value="Modificar" data-bs-toggle="modal" data-bs-target="#Modify" data-bs-whatever="@mdo">
                    </div>
                </div>

                <div class="row">
                    <div class="col-sm-3"></div>
                    <div class="col-sm-9 text-secondary">
                        <input type="button" class="btn btn-danger px-4" value="Eliminar" data-bs-toggle="modal" data-bs-target="#Delete" data-bs-whatever="@mdo">
                    </div>
                </div>

            </div>	
		</div>
	</div>

        <div class="modal fade" id="Modify" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Modificar usuario "<span>{{userInfo.username}}</span>"</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <!-- form --> 

                        <br>
                        <form class="mx-1 mx-md-4">
                            <br>
                            <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                            <div class="form-outline flex-fill mb-0">
                                <input v-model="modifyUser.username" type="text" id="form3Example1c" class="form-control" :placeholder="[[userInfo.username]]" />
                                <label class="form-label" for="form3Example1c">Nombre de Usuario</label>
                            </div>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                            <div class="form-outline flex-fill mb-0">
                                <input v-model="modifyUser.password" type="password" id="form3Example4cad" class="form-control"/>
                                <label class="form-label" for="form3Example4cad">Contraseña</label>
                            </div>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                            <div class="form-outline flex-fill mb-0">
                                <input v-model="modifyUser.name" type="text" id="form3Example4cd" class="form-control" :placeholder="[[userInfo.name]]" />
                                <label class="form-label" for="form3Example4cd">Nombre</label>
                            </div>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-envelope fa-lg me-3 fa-fw"></i>
                            <div class="form-outline flex-fill mb-0">
                                <input v-model="modifyUser.email" type="text" id="form3Example3c" class="form-control" :placeholder="[[userInfo.email]]" />
                                <label class="form-label" for="form3Example3c">Email</label>
                            </div>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4">
                            <i class="fas fa-user fa-lg me-3 fa-fw"></i>
                            <div class="form-outline flex-fill mb-0">
                                <input v-model="modifyUser.phone" type="text" id="form3Example1c1" class="form-control" :placeholder="[[userInfo.phone]]" />
                                <label class="form-label" for="form3Example1c1">Número telefónico</label>
                            </div>
                            </div>

                            <div class="d-flex flex-row align-items-center mb-4" v-if="userInfo.admin">
                            <i class="fas fa-key fa-lg me-3 fa-fw"></i>
                            <select class="dropDownCentroOpciones" v-model="modifyUser.admin" :placeholder="[[userInfo.admin]]">
                                <option selected :value="true">Admin</option>
                                <option :value="false">No admin</option>
                            </select>    
                            </div>

                        </form>

            <div class="modal-footer">
                <button data-bs-dismiss="modal" v-on:click="modifyAnUser" type="submit" class="btn btn-primary" >Modificar</button>
            </div>
            </div>
        </div>
    </div>  

    <div class="modal fade" id="Delete" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Escribe id para borrar</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form>
                    <h3> ¿Estas seguro de borrar tu usuario "<span>{{userInfo.username}}</span>"?</h3>
                <!-- <div class="mb-3">
                    <label for="recipient-name" class="col-form-label">Id:</label>
                    <input type="text" class="form-control" id="recipient-name" >
                </div> -->
                <!-- <div class="mb-3">
                    <label for="message-text" class="col-form-label">Message:</label>
                    <textarea class="form-control" id="message-text"></textarea>
                </div> -->
                </form>
            </div>
            <div class="modal-footer">
                <button data-bs-dismiss="modal" v-on:click="deleteMyUser" type="button" class="btn btn-danger">Borrar</button>
            </div>
            </div>
        </div>
    </div>

</template>


<script>
import gql from "graphql-tag";

export default {
  name: 'UserInfo',

    data: function() {
        return {
          userInfo: {
              id: 0,
              username: "",
              name: "",
              email: "",
              phone: "",
              admin: ""
          },
            modifyUser: {
                username: "",
                password: "",
                name: "",
                email: "",
                phone: 0,
                admin: false,
            },
        };
    },

  methods: {

     getUserData: async function(){

         await this.$apollo
         .query({
         query: gql`
             query UserDetailById {
                 userDetailById {
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
             let dataGet = {
                     id: result.data.userDetailById.id,
                     username: result.data.userDetailById.username,
                     name: result.data.userDetailById.name,
                     email: result.data.userDetailById.email,
                     phone: result.data.userDetailById.phone,
                     admin: result.data.userDetailById.admin
             };
             this.userInfo = dataGet;
         })
         .catch((error) => {
             console.log(error)
           alert("ERROR: Fallo geUserData");
         });
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
                    userId: this.userInfo.id,
                    putUser: this.modifyUser
                }
            })
            .then((result) => {
                alert("Producto "+result.data.updateUser.id+" Modificado");
                this.getUserData();
                this.modifyUser = "";
                this.$emit("logOut");
            })
            .catch((error) => {
                console.log(error)
            alert("ERROR: Fallo modificando producto");
            });

        },

        deleteMyUser: async function(){
            await this.$apollo
                .mutate({
                mutation: gql`
                mutation Mutation {
                    deleteUser
                }
                `,
            })
            .then((result) => {
                this.getUserData();
                alert("Eliminado con éxito");
                this.modifyUser = "";
                this.$emit("logOut");
                localStorage.clear();
            })
            .catch((error) => {
            alert("ERROR: Fallo eliminando producto");
            });

        }

  },
  
  mounted: async function(){
      this.getUserData();
  },

}
</script>

<style>
body{
    background: #f7f7ff;
    margin-top:20px;
}
.card {
    position: relative;
    display: flex;
    flex-direction: column;
    min-width: 0;
    word-wrap: break-word;
    background-color: #fff;
    background-clip: border-box;
    border: 0 solid transparent;
    border-radius: .25rem;
    margin-bottom: 1.5rem;
    box-shadow: 0 2px 6px 0 rgb(218 218 253 / 65%), 0 2px 6px 0 rgb(206 206 238 / 54%);
}
.me-2 {
    margin-right: .5rem!important;
}
</style>
