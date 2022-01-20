<template>
    <section class="main-container">
        <div class="cards-container">

          <div class="product-card">
            <img src="../../logos/asus.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$1400,00</p>
                <p>Portátil ASUS</p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/sony.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$110,00</p>
                <p>Celular sony </p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/xiaomi.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$700,00</p>
                <p>Xiaomi RedMi 10 </p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/iphone.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$1200,00</p>
                <p>Iphone 13</p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/huaweip30.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$600,00</p>
                <p>Huawei P30 lite</p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/galaxy.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$1200,00</p>
                <p>Galaxy Note 10</p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/macbook.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$899,00</p>
                <p>MacBook Pro </p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/ps5.png" alt="">
            <div class="product-info">
              <div>
                <p>$400,00</p>
                <p>PlayStation 5</p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>

          <div class="product-card">
            <img src="../../logos/xbox.jpg" alt="">
            <div class="product-info">
              <div>
                <p>$400,00</p>
                <p>Xbox One 1 TB</p>
              </div>
              <figure>
                <img src="../../icons/bt_add_to_cart.svg" alt="">
              </figure>
            </div>
          </div>
        </div>
    </section>
</template>


<script>
    import jwt_decode from "jwt-decode";
    import gql        from "graphql-tag";
    export default{
        name: "Home",
        data: function(){
            return {
                userId         : jwt_decode(localStorage.getItem("tokenRefresh")).user_id,
                userDetailById : {
                    name     : "",
                    email    : "",
                    username : ""
                }
            }
        },
        apollo: {
            userDetailById : {
                query: gql`
                    query Query($userId: Int!) {
                        userDetailById(userId: $userId) {
                            name
                            email
                            username
                    }
}               `,
                variables() {
                    return {
                        userId: this.userId,
                    }
                }
            },
        }
    };
</script>


<style>
    .information {
        margin: 0;
        padding: 0%;
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }
    
    .information h1 {
        font-size: 60px;
        color: #283747;
    }
    .information h2 {
        font-size: 40px;
        color: #283747;
    }
    .information span {
        color: crimson;
        font-weight: bold;
    }
    .details h3 {
        font-size: 35px;
        color: #283747;
        text-align: center;
    }
    .details h2 {
        font-size: 35px;
        color: #283747;
    }
    .details {
        border: 3px solid rgba(0, 0, 0, 0.3);
        border-radius: 20px;
        padding: 30px 80px;
        margin: 30px 0 0 0;
    }
</style>