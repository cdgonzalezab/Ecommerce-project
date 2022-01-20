<template>


<!-- Shopping cart -->
<div class="modal fade bd-example-modal-lg" id="xdxd" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
    <div class="shopingCart">
        <div class="card-lg">
            <div class="row">
                <div class="col-md-8 cart">
                    <div class="title">
                        <div class="row">
                            <div class="col">
                                <h4><b>Carrito de Compras</b></h4>
                            </div>
                            <div class="col align-self-center text-right text-muted">{{cartProducts.length}} items</div>
                        </div>
                    </div>
                    <li v-for="cartProduct in cartProducts" v-bind:key="cartProduct.id">
                        <div class="row border-top border-bottom">
                            <div class="row main align-items-center">
                                <div class="col-2"><img class="img-fluid" :src="cartProduct.imgScr"></div>
                                <div class="col">
                                    <div class="row text-muted">{{cartProduct.name}}</div>
                                    <div class="row">{{cartProduct.service}}</div>
                                </div>
                                <div class="col"> <a href="#" v-on:click="subsOneQtty(cartProduct.id)">-</a><a href="#" class="border">{{cartProduct.quantity}}</a><a href="#" v-on:click="addOneQtty(cartProduct.id)">+</a> </div>
                                <div class="col">&dollar; {{cartProduct.subTotal}} <a href="#" v-on:click="deleteCartItem(cartProduct.id)"> <span class="close" >&#10005;</span></a></div>
                            </div>
                        </div>
                    </li>
                    <div class="back-to-shop" data-bs-dismiss="modal"><a href="#">&leftarrow;</a><span class="text-muted">Regresar</span></div>
                </div>
                <div class="col-md-4 summary">
                    <div>
                        <h5><b>Resumen</b></h5>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="col" style="padding-left:0;">Artículos {{cartProducts.length}}</div>
                        <div class="col text-right">&dollar; {{totalPrice}}</div>
                    </div>
                    <form>
                        <p>Dar Codigo</p> <input id="code" placeholder="Enter your code">
                    </form>
                    <div class="row" style="border-top: 1px solid rgba(0,0,0,.1); padding: 2vh 0;">
                        <div class="col">Precio total</div>
                        <div class="col text-right">&dollar; {{totalPrice}}</div>
                    </div> <button v-on:click="addUserProducts" data-bs-dismiss="modal" class="btn">Comprar</button>
                </div>
            </div>
        </div>
    </div>
    </div>
  </div>
</div>



<!--Products-->
<div class ="cuerpo">
    <br>
    <div class ="catalogue">
        <ul class="card-wrapper">
            
            <li class="card" v-for="product in products" v-bind:key="product.id">
                <img :src="product.imgSrc" alt=''>
                <div class="row">
                    <div class="col col align-self-center">
                        <h3>{{product.name}}</h3>
                        <p>${{product.price}}</p>
                    </div>
                    <div class="col col-lg-4 align-self-end">
                        <button v-on:click="addToCart(product)" class="btn btn-primary">Añadir</button>
                    </div>
                </div>
            </li>
            
        </ul>
    </div> 
</div>




</template> 

<script>
import gql from "graphql-tag";

    export default {
    name: 'Products',
    

    data: function(){
        return {
            product: [],
            products: [],
            popupAtivation: false,
            userProducts: [],
            cartProducts: [],
            cartProduct: {
                id: "",
                name: "",
                price: 0,
                service: false,
                imgSrc: "",
                quantity: 1,
                subTotal: 0
            },
            totalPrice:0
        }
    },

    methods: {
       getProductsList: async function(){
            await this.$apollo
            .query({
            query: gql`
                query Products {
                    products {
                        id
                        name
                        price
                        service
                        imgSrc
                    }
                }
                `,
                    
            })
            .then((result) => {
                this.products = result.data.products;
            })
            .catch((error) => {
            alert("ERROR: Fallo geUserData");
            });
        },

        getUserProducts: async function(id){
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
                getUserProductsByUserIdId: id
            },
            })
            .then((result) => {
                this.userProducts = result.data.getUserProductsByUserId
            })
            .catch((error) => {
            alert("ERROR: Fallo getUserData");
            });
        },

        updateUserProducts: async function(inputUserItemId, inputQuantityVal){
            await this.$apollo
            .mutate({
                mutation: gql`
                mutation UpdateUserProduct($inUpdateItem: inUserProduct!) {
                    updateUserProduct(inUpdateItem: $inUpdateItem) {
                        id
                        userId
                        productId
                        quantity
                        dateModified
                    }
                }
                `,
                variables: {
                    inUpdateItem: {
                        productId: inputUserItemId,
                        quantity: inputQuantityVal
                        }
                },
            })
            .then((result) => {
                //console.log(result.data.updateUserProduct);
            })
            .catch((error) =>{
                alert("error en modificación");
            })
        },

        addUserProduct: async function(itemId, itemQuantity){
            await this.$apollo
            .mutate({
            mutation: gql`
                mutation CreateUserProduct($inputItem: inUserProduct!) {
                    createUserProduct(inputItem: $inputItem) {
                        id
                        userId
                        productId
                        quantity
                        dateModified
                    }
                }
            `,
            variables: {
                inputItem:{
                    productId: itemId,
                    quantity: itemQuantity
                }
            },
            })
            .then((result) => {
                console.log("added user product")
                //result.data.createdUserProduct.productId
     
            })
            .catch((error) => {
                console.log(error);
                alert("ERROR: Fallo addUserData");
            });
        },

        addUserProducts: async function(){
            let isUser = "";
            isUser = localStorage.getItem("user_Id") || "";
            if ( (isUser == "") || (isUser == null )|| (isUser == false)){

                this.$router.push({name: "signUp"})
                this.cartProducts.length = 0;
                this.$emit("getCartItems",this.cartProducts.length)
            }

            else{
                console.log("on Else")
                const intIsUser = parseInt(isUser);
                await this.getUserProducts(intIsUser);

                if (this.userProducts.length == 0){
                    this.cartProducts.forEach(element => {
                        this.addUserProduct(element.id, element.quantity)
                    });
                }
                else {
                    let elementsId = [];
                    this.cartProducts.forEach(element => {
                        this.userProducts.forEach(item => {
                            if (element.id == item.productId){
                                const myQtty = item.quantity + element.quantity;
                                this.updateUserProducts(item.productId, myQtty)
                                elementsId.push(element.id)
                            }
                        })
                    });
                    elementsId.forEach(element => {
                        this.deleteCartItem(element)
                    });
                    console.log("second else")
                    this.cartProducts.forEach(element => {
                        this.addUserProduct(element.id, element.quantity)
                    });
                    alert("Comprados con éxito")
                }
                this.cartProducts.length = 0;
                //alert("Comprados con éxito");

            }

        },

        addToCart: function (myProductId){
            var object ={
                id: myProductId.id,
                name: myProductId.name,
                price: myProductId.price,
                service: myProductId.service,
                imgSrc: myProductId.service,
                quantity: 0
            }
            const myIndex = this.cartProducts.findIndex(cartProduct => cartProduct.id === object.id)
            if (myIndex == -1){
                this.cartProducts.push(object)
            } 
            this.addOneQtty(object.id)
            this.$emit("getCartItems",this.cartProducts.length)
        },

        subsOneQtty: function (myCartPrdct){
            const myIndex = this.cartProducts.findIndex(cartProduct => cartProduct.id === myCartPrdct)
            let myValue = this.cartProducts[myIndex].quantity
            myValue -= 1;
            if (myValue <=0)
                myValue = 1;
            this.cartProducts[myIndex].quantity = myValue;
            this.subTotalProducts(myCartPrdct);
            this.getSummary();
        },

        addOneQtty: function (myCartPrdct){
            const myIndex = this.cartProducts.findIndex(cartProduct => cartProduct.id === myCartPrdct)
            this.cartProducts[myIndex].quantity += 1;
            this.subTotalProducts(myCartPrdct);
            this.getSummary();
        },

        deleteCartItem: function(myCartPrdctId){
            const myIndex = this.cartProducts.findIndex(cartProduct => cartProduct.id === myCartPrdctId)
            this.cartProducts.splice(myIndex,1)
            this.$emit("getCartItems",this.cartProducts.length)
            this.getSummary();
        },

        subTotalProducts: function(myCartPrdctId){
            const myIndex = this.cartProducts.findIndex(cartProduct => cartProduct.id === myCartPrdctId);
            let myObject = this.cartProducts[myIndex];
            myObject.subTotal = myObject.quantity * myObject.price;
            this.cartProducts[myIndex].subTotal = myObject.subTotal;
        },

        getSummary: function(){
            let myTotal = 0;
            this.cartProducts.forEach(element => {
                myTotal += element.subTotal
            });
            this.totalPrice = myTotal;
        },

        cardActivate: function() {
            this.popupAtivation = !this.popupAtivation;
        }

    },

    created: async function(){
        this.getProductsList();
    },
  }
</script>
<style>

    .list-group{
        background-color: rgba(255, 255, 255, 0.534);
        border-radius: 0.5rem;
        box-shadow: 0.05rem 0.1rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45);
        padding-bottom: 1rem;
    }
    .list-group ul{
        display: flex;
        flex-direction: column;
        width: 130px;
        margin: 0;
        padding: 0;
    }
    .list-group ul button{
        width: 90%;
        margin: 0 auto;
        margin-top: 10px;
        border-radius: 5px;
        height: 30px;
    }
    .list-group ul button:hover{
        background-color: rgb(187, 187, 187);
        cursor: pointer;
    }
    .cuerpo{
        display: flex;
        flex-wrap:wrap;
        justify-content: center;
    }
    body{
        margin: 0 0 0 0;
    }
    .header{
        margin: 0;
        padding: 0;
        width: 100%;
        height: 10vh; 
        min-height: 100px;
        background-color: #283747 ;
        color:#ffffff  ;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .header h1{
        width: 20%;
        text-align: center;
    }
    .card {
    /*
    --card-gradient: rgba(0, 0, 0, 0.8);
    --card-gradient: #949494b7, #00000088;
    --card-blend-mode: overlay;
     */
    
    background-color: rgba(255, 255, 255, 0.534);
    border-radius: 0.5rem;
    box-shadow: 0.05rem 0.1rem 0.3rem -0.03rem rgba(0, 0, 0, 0.45);
    padding-bottom: 1rem;
    background-image: linear-gradient(
        var(--card-gradient),
        white max(9.5rem, 27vh)
    );
    overflow: hidden;
    }
    img {
    border-radius: 0.5rem 0.5rem 0 0;
    width: 100%;
    object-fit: cover;
    
    max-height: max(20rem, 30vh);
    aspect-ratio: 4/3;
    mix-blend-mode: var(--card-blend-mode);
    
    }
    :last-child {
    margin-bottom: 0;
    }
    :hover,
    :focus-within {
    --card-gradient: #00000075 max(8.5rem, 20vh);
    }
    
    /* Additional demo display styles */
    * {
    box-sizing: border-box;
    }

    .body {
    display: grid;
    place-content: center;
    justify-items: center;
    min-height: 100vh;
    margin: 0;
    padding: 1rem;
    line-height: 1.5;
    font-family: -apple-system, BlinkMacSystemFont, avenir next, avenir,
        helvetica neue, helvetica, Ubuntu, roboto, noto, segoe ui, arial, sans-serif;
    color: #444;
    background-color: #ffffff;
    }
    .card h3 {
    margin-top: 1rem;
    font-size: 1.25rem;
    }
    .card a {
    color: inherit;
    }
    .card-wrapper {
    list-style: none;
    padding: 0;
    margin: 0;
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(30ch, 1fr));
    grid-gap: 1.5rem;
    max-width: 100vw;
    width: 120ch;
    padding-left: 1rem;
    padding-right: 1rem;
    }

    html {
        background-color: white;
    }



img-replace {
    display: inline-block;
    overflow: hidden;
    text-indent: 100%;
    color: transparent;
    white-space: nowrap;
}


/* -------- Shoping Cart ------*/

.shopingCart body {
    background: #ddd;
    min-height: 100vh;
    vertical-align: middle;
    display: flex;
    font-family: sans-serif;
    font-size: 0.8rem;
    font-weight: bold
}

.shopingCart title {
    margin-bottom: 5vh
}

.shopingCart .card {
    margin: auto;
    max-width: 950px;
    width: 90%;
    box-shadow: 0 6px 20px 0 rgba(0, 0, 0, 0.19);
    border-radius: 1rem;
    border: transparent
}

@media(max-width:767px) {
    .shopingCart .card {
        margin: 3vh auto
    }
}

.shopingCart .cart {
    background-color: #fff;
    padding: 4vh 5vh;
    border-bottom-left-radius: 1rem;
    border-top-left-radius: 1rem
}

@media(max-width:767px) {
    .shopingCart .cart {
        padding: 4vh;
        border-bottom-left-radius: unset;
        border-top-right-radius: 1rem
    }
}

.shopingCart .summary {
    background-color: #ddd;
    border-top-right-radius: 1rem;
    border-bottom-right-radius: 1rem;
    padding: 4vh;
    color: rgb(65, 65, 65)
}

@media(max-width:767px) {
    .shopingCart .summary {
        border-top-right-radius: unset;
        border-bottom-left-radius: 1rem
    }
}

.shopingCart .summary .col-2 {
    padding: 0
}

.shopingCart .summary .col-10 {
    padding: 0
}

.shopingCart .row {
    margin: 0
}

.shopingCart .title b {
    font-size: 1.5rem
}

.shopingCart .main {
    margin: 0;
    padding: 2vh 0;
    width: 100%
}

.shopingCart .col-2,
.shopingCart .col {
    padding: 0 1vh
}

.shopingCart a {
    padding: 0 1vh
}

.shopingCart .close {
    margin-left: auto;
    font-size: 0.7rem
}

.shopingCart img {
    width: 3.5rem
}

.shopingCart .back-to-shop {
    margin-top: 4.5rem
}

.shopingCart h5 {
    margin-top: 4vh
}

.shopingCart hr {
    margin-top: 1.25rem
}

.shopingCart form {
    padding: 2vh 0
}

.shopingCart select {
    border: 1px solid rgba(0, 0, 0, 0.137);
    padding: 1.5vh 1vh;
    margin-bottom: 4vh;
    outline: none;
    width: 100%;
    background-color: rgb(247, 247, 247)
}

.shopingCart input {
    border: 1px solid rgba(0, 0, 0, 0.137);
    padding: 1vh;
    margin-bottom: 4vh;
    outline: none;
    width: 100%;
    background-color: rgb(247, 247, 247)
}

.shopingCart input:focus::-webkit-input-placeholder {
    color: transparent
}

.shopingCart .btn {
    background-color: #000;
    border-color: #000;
    color: white;
    width: 100%;
    font-size: 0.7rem;
    margin-top: 4vh;
    padding: 1vh;
    border-radius: 0
}

.shopingCart .btn:focus {
    box-shadow: none;
    outline: none;
    box-shadow: none;
    color: white;
    -webkit-box-shadow: none;
    /*-webkit-user-select: none;*/
    transition: none
} 


</style>