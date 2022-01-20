import { createRouter, createWebHashHistory } from 'vue-router'
import App                                    from './App.vue'

import Login                                  from './components/Login.vue'
import SignUp                                 from './components/SignUp.vue'
import Home                                   from './components/Home.vue'
import Account                                from './components/Account.vue'
import Transaction                            from './components/Transaction.vue'

const routes = [
  {
    path: '/',
    name: 'root',
    component: App
  },
  {
    path: '/user/login',
    name: "login",
    component: Login
  },
  {
    path: '/user/signUp',
    name: "signUp",
    component: SignUp
  },
  {
    path: '/user/home',
    name: "home",
    component: Home
  },
  {
    path: '/user/account',
    name: "account",
    component: Account
  },
  {
    path: '/user/transaction',
    name: "transaction",
    component: Transaction
  }
];

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
