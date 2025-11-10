<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Repositório Institucional PPG</h2>
      
      <form @submit.prevent="handleLogin" novalidate>
        <div class="input-group">
          <label for="email">Email Institucional</label>
          <input
            type="email"
            id="email"
            v-model.trim="formData.email"
            placeholder="Digite seu email"
            :disabled="isLoading"
            required
            autocomplete="email"
          />
        </div>

        <div class="input-group">
          <label for="password">Senha</label>
          <input
            type="password"
            id="password"
            v-model="formData.password"
            placeholder="Digite sua senha"
            :disabled="isLoading"
            required
            autocomplete="current-password"
          />
        </div>

        <div v-if="errorMessage" class="error-message" role="alert">
          {{ errorMessage }}
        </div>

        <button type="submit" :disabled="isLoading || !isFormValid">
          <span v-if="!isLoading">Entrar</span>
          <span v-else>Entrando...</span>
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '@/services/api'

const router = useRouter()

// Estado reativo
const formData = ref({
  email: '',
  password: ''
})
const errorMessage = ref('')
const isLoading = ref(false)

// Computed
const isFormValid = computed(() => {
  return formData.value.email.length > 0 && formData.value.password.length > 0
})

// Métodos
const handleLogin = async () => {
  errorMessage.value = ''
  
  if (!isFormValid.value) {
    errorMessage.value = 'Por favor, preencha todos os campos.'
    return
  }

  isLoading.value = true

  try {
    const response = await authService.login(
      formData.value.email,
      formData.value.password
    )

    // Armazena o token
    if (response.token) {
      localStorage.setItem('user-token', response.token)
      
      // Redireciona para o dashboard
      await router.push({ name: 'dashboard' })
    } else {
      throw new Error('Token não recebido')
    }
  } catch (error) {
    console.error('Erro ao fazer login:', error)
    
    if (error.response?.status === 401) {
      errorMessage.value = 'Email ou senha incorretos.'
    } else if (error.response?.status === 500) {
      errorMessage.value = 'Erro no servidor. Tente novamente mais tarde.'
    } else if (error.code === 'ECONNABORTED') {
      errorMessage.value = 'Tempo de conexão esgotado. Verifique sua internet.'
    } else {
      errorMessage.value = 'Erro ao conectar. Verifique sua conexão.'
    }
    
    // Limpa o token em caso de erro
    localStorage.removeItem('user-token')
  } finally {
    isLoading.value = false
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  padding: 20px;
}

.login-box {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
  width: 100%;
  max-width: 420px;
  text-align: center;
}

h2 {
  margin-bottom: 32px;
  color: #333;
  font-size: 24px;
  font-weight: 600;
}

.input-group {
  margin-bottom: 24px;
  text-align: left;
}

label {
  display: block;
  margin-bottom: 8px;
  color: #555;
  font-weight: 500;
  font-size: 14px;
}

input {
  width: 100%;
  padding: 12px 16px;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  box-sizing: border-box;
  font-size: 15px;
  transition: all 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
}

input:disabled {
  background-color: #f5f5f5;
  cursor: not-allowed;
  opacity: 0.6;
}

button {
  width: 100%;
  padding: 14px;
  border: none;
  border-radius: 8px;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  font-size: 16px;
  font-weight: 600;
  cursor: pointer;
  transition: all 0.3s ease;
  margin-top: 8px;
}

button:hover:not(:disabled) {
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(102, 126, 234, 0.4);
}

button:active:not(:disabled) {
  transform: translateY(0);
}

button:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.error-message {
  color: #dc3545;
  background-color: #ffe6e6;
  padding: 12px;
  border-radius: 6px;
  margin-bottom: 20px;
  font-size: 14px;
  border-left: 4px solid #dc3545;
  text-align: left;
}

@media (max-width: 480px) {
  .login-box {
    padding: 30px 24px;
  }

  h2 {
    font-size: 20px;
  }
}
</style>