import { createRouter, createWebHistory } from 'vue-router'
import { authService } from '@/services/api'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/dashboard'
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/LoginView.vue'),
      meta: { requiresGuest: true }
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: () => import('@/views/DashboardView.vue'),
      meta: { requiresAuth: true }
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '/login'
    }
  ]
})

// Navigation Guards
router.beforeEach((to, from, next) => {
  const isAuthenticated = authService.isAuthenticated()

  // Se a rota requer autenticação e o usuário não está autenticado
  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'login' })
  }
  // Se a rota é para convidados e o usuário está autenticado
  else if (to.meta.requiresGuest && isAuthenticated) {
    next({ name: 'dashboard' })
  }
  // Caso contrário, permite a navegação
  else {
    next()
  }
})

export default router