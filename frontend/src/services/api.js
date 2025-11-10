import axios from 'axios'

const apiClient = axios.create({
  baseURL: import.meta.env.VITE_API_URL || 'http://localhost:8080',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// Interceptor de requisição
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('user-token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error) => Promise.reject(error)
)

// Interceptor de resposta
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    // Se o token expirou ou é inválido
    if (error.response?.status === 401) {
      localStorage.removeItem('user-token')
      // Redirecionar para login se não estiver já lá
      if (window.location.pathname !== '/login') {
        window.location.href = '/login'
      }
    }
    return Promise.reject(error)
  }
)

export default apiClient

// Serviços específicos
export const authService = {
  async login(email, password) {
    const response = await apiClient.post('/auth/login', { email, password })
    return response.data
  },

  logout() {
    localStorage.removeItem('user-token')
    delete apiClient.defaults.headers.common['Authorization']
  },

  isAuthenticated() {
    return !!localStorage.getItem('user-token')
  }
}