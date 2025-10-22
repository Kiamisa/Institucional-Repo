import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue' // Importa a Home
import LoginView from '../views/LoginView.vue'
import RegisterView from '../views/RegisterView.vue' // Importa o Registo
import DashboardView from '../views/DashboardView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/', // Rota raiz agora aponta para a Home
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'login',
      component: LoginView
    },
    {
      path: '/register', // Nova rota para Registo
      name: 'register',
      component: RegisterView
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: DashboardView,
      // Futuramente, adicionaremos uma verificação aqui para garantir que só utilizadores logados acedam
      // meta: { requiresAuth: true }
    }
  ]
})

// Futuramente, adicionaremos a lógica de guarda de rotas (navigation guard) aqui

export default router