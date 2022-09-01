import { 
  createRouter , 
  createWebHistory 
} from 'vue-router'

import Home from '@pages/Home.vue'
import Login from '@pages/Login.vue'

const routes = [
  { path: '/' , name: 'Home' , component: Home } ,
  { path: '/login' , name: 'Login' , component: Login }
]

const router = createRouter({
  history : createWebHistory() ,
  routes: routes
})

export default router