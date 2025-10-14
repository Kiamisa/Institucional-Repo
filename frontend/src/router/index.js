import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '../views/LoginView.vue'
import DashboardView from '@/views/DashboardView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      // Se o utilizador aceder à raiz do site, redireciona para /login
      path: '/',
      redirect: '/login' 
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView // Mostra o componente LoginView nesta rota
    },
    {
      path:'/dashboard',
      name:'dashboard',
      component: DashboardView
    }
  ]
})

export default router