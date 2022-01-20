<template>

    <div class="container-grid">
        <h2>Mis Productos</h2>
       
        <div class="container" >
            <table class="table table-hover" >
            <thead>
                <tr>
                    <th scope="col">Id Producto</th>
                    <th scope="col">Cantidad</th>
                    <!-- <th scope="col">imgSource</th> -->
                    <th scope="col">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="userProduct in userProducts" v-bind:key="userProduct.id">
                    <td>{{userProduct.productId}}</td>
                    <td>{{userProduct.quantity}}</td>
                    <!-- <td>{{product.imgSrc.substring(0,3)}}</td> -->
                    <td>
                        <button v-on:click="getDeleteProductId(userProduct.productId)" type="bustton" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#Delete">Borrar</button>
                    </td>
                </tr>
            </tbody>
            </table>
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
                    <h3> ¿Estas seguro de borrar tu producto? "<span>{{idUserProductDelete}}</span>"?</h3>
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
                <button data-bs-dismiss="modal" v-on:click="deleteUserProduct" type="button" class="btn btn-danger">Borrar</button>
            </div>
            </div>
        </div>
    </div>



</template>


<script>   

import gql from "graphql-tag";
export default {
    name: "UserProducts",

    // computed: {
    //     reloadUserProducts:{
    //         get: async function(){
    //             await this.userProducts();
    //             return userProducts;
    //         }
    //     }
    // },

    data: function(){
        return {
            userProducts: [],
            userProduct: {
                id: "",
                userId: "",
                productId: "",
                quantity: "",
                dateModified:""
            },
            idUserProductDelete: "",
            userLogged: ""
        }
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
            this.userLogged = result.data.userDetailById.id
         })
         .catch((error) => {
             console.log(error)
           alert("ERROR: Fallo getUserData");
         });
     },

        getUserProducts: async function(){
            await this.$apollo
            .query({
            query: gql`
                query GetUserProductsByUserId($getUserProductsByUserIdId: Int!) {
                    getUserProductsByUserId(id: $getUserProductsByUserIdId) {
                        id
                        userId
                        productId
                        quantity
                        dateModified
                    }
                }
            `,
            variables: {
                getUserProductsByUserIdId: this.userLogged
            },
            })
            .then((result) => {
                this.userProducts = result.data.getUserProductsByUserId

            })
            .catch((error) => {
                console.log(error)
            alert("ERROR: Fallo getUserData");
            });
        },

        deleteUserProduct: async function(){
            await this.$apollo
            .mutate({
            mutation: gql`
            mutation DeleteUserProduct($productId: Int!) {
                deleteUserProduct(productId: $productId)
            }
            `,
            variables: {
                productId: this.idUserProductDelete
            }
            })
            .then((result) => {
                alert("result.data.deleteUserProduct");
                this.getUserProducts();
                location.reload();
            })
            .catch((error) => {
                console.log(error)
            alert("ERROR: Fallo elimnando producto de usuario");
            location.reload();
            });

            
        },

        getDeleteProductId: function(id){
            this.idUserProductDelete = id;
        }

    },
    created: async function(){
        await this.getUserData();
        await this.getUserProducts();
    }
}


</script>


<style>


</style>