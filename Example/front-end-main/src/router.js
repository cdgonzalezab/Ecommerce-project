import gql from "graphql-tag";
import { createRouter, createWebHistory } from "vue-router";
import { ApolloClient, createHttpLink, InMemoryCache } from '@apollo/client/core'

import Home from './components/Home.vue'
import LogIn from './components/LogIn.vue'
import products from './components/Products.vue'
import About from './components/About.vue'
import SignUp from './components/SignUp.vue'
import UserInfo from './components/UserInfo.vue'
import UserProducts from './components/UserProducts.vue'
import AdminProducts from './components/AdminProducts.vue'
import AdminUsers from './components/AdminUsers.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: Home,
    meta: { requiresAuth: false }
  },

  {
    path: '/About',
    name: 'about',
    component: About,
    meta: { requiresAuth: false }
  },
  
  {
    path: '/logIn',
    name: "logIn",
    component: LogIn,
    meta: { requiresAuth: false }
  },

  {
    path: '/SignUp',
    name: "signUp",
    component: SignUp,
    meta: { requiresAuth: false }
  },

  {
    path: '/products',
    name: "products",
    component: products,
    meta: { requiresAuth: false }
  },

  {
    path: '/UserProducts',
    name: "UserProducts",
    component: UserProducts,
    meta: { 
      requiresAuth: true
    }
  },

  {
    path: '/UserInfo',
    name: "UserInfo",
    component: UserInfo,
    meta: { 
      requiresAuth: true
    }
  },

  {
    path: '/AdminProducts',
    name: "AdminProducts",
    component: AdminProducts,
    meta: { 
      requiresAuth: true
    }
  },

  {
    path: '/AdminUsers',
    name: "AdminUsers",
    component: AdminUsers,
    meta: { 
      requiresAuth: true
    }
  }

];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

const apolloClient = new ApolloClient({
  link: createHttpLink({ uri: 'https://mintic-p5-g4-dw-apigateway.herokuapp.com/' }),
  cache: new InMemoryCache()
})

async function isAuth() {
  if (localStorage.getItem("token_access") === null || localStorage.getItem("token_refresh") === null) {
      return false;
  }

  try {
      var result = await apolloClient.mutate({
          mutation: gql `
            mutation RefreshToken($refresh: String!) {
              refreshToken(refresh: $refresh) {
                access
              }
            }
          `,
          variables: {
              refresh: localStorage.getItem("token_refresh"),
          },
      })
      localStorage.setItem("token_access", result.data.refreshToken.access);
      return true;

  } catch {
      localStorage.clear();
      alert("Su sesión expiró, por favor vuelva a iniciar sesión");
      return false;
  }
}

//  async function isAdmin() {
//      try {
//        var result = await apolloClient.query({
//            query: gql `
//              query UserDetailById {
//                  userDetailById {
//                      id
//                      username
//                      name
//                      email
//                      phone
//                      admin
//                  }
//              }
//            `,
//        })
//        localStorage.setItem("is_admin", result.data.userDetailById.admin);
//        return true;

//      } catch (error){
//          console.log(error)
//          alert("Su sesión expiró, por favor vuelva a iniciar sesión");
//          return false;
//      }
//  }


router.beforeEach(async(to, from) => {
  var is_auth = await isAuth();
  //var is_admin = await isAdmin();

  if (is_auth == to.meta.requiresAuth)
      return true
  
  //if (is_auth) return { name: "UserInfo" };
  //return { name: "logIn" };
})

export default router;
