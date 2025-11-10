<template>
  <div class="dashboard-container">
    <header class="dashboard-header">
      <div class="header-content">
        <h1>Repositório PPG</h1>
        <div class="header-actions">
          <span class="user-info">Bem-vindo!</span>
          <button @click="handleLogout" class="logout-btn" :disabled="isLoggingOut">
            <span v-if="!isLoggingOut">Sair</span>
            <span v-else>Saindo...</span>
          </button>
        </div>
      </div>
    </header>

    <main class="dashboard-main">
      <div class="welcome-card">
        <h2>Bem-vindo ao Sistema!</h2>
        <p>Esta é a sua área principal do repositório institucional.</p>
        <p class="subtitle">Em breve, aqui ficará a lista de documentos e recursos disponíveis.</p>
        
        <div class="feature-grid">
          <div class="feature-item">
            <div class="feature-icon">📄</div>
            <h3>Documentos</h3>
            <p>Acesse e gerencie documentos</p>
          </div>
          
          <div class="feature-item">
            <div class="feature-icon">📊</div>
            <h3>Relatórios</h3>
            <p>Visualize relatórios e estatísticas</p>
          </div>
          
          <div class="feature-item">
            <div class="feature-icon">🔍</div>
            <h3>Pesquisa</h3>
            <p>Busque informações rapidamente</p>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { authService } from '@/services/api'

const router = useRouter()
const isLoggingOut = ref(false)

const handleLogout = async () => {
  if (isLoggingOut.value) return

  isLoggingOut.value = true

  try {
    // Remove o token e limpa autenticação
    authService.logout()
    
    // Pequeno delay para melhor UX
    await new Promise(resolve => setTimeout(resolve, 300))
    
    // Redireciona para login
    await router.push({ name: 'login' })
  } catch (error) {
    console.error('Erro ao fazer logout:', error)
  } finally {
    isLoggingOut.value = false
  }
}
</script>

<style scoped>
.dashboard-container {
  min-height: 100vh;
  background-color: #f0f2f5;
}

.dashboard-header {
  background: white;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  position: sticky;
  top: 0;
  z-index: 100;
}

.header-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

h1 {
  margin: 0;
  font-size: 24px;
  color: #333;
  font-weight: 600;
}

.header-actions {
  display: flex;
  align-items: center;
  gap: 16px;
}

.user-info {
  color: #666;
  font-size: 14px;
}

.logout-btn {
  padding: 10px 20px;
  border: none;
  background-color: #dc3545;
  color: white;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.3s ease;
}

.logout-btn:hover:not(:disabled) {
  background-color: #c82333;
  transform: translateY(-2px);
  box-shadow: 0 4px 12px rgba(220, 53, 69, 0.3);
}

.logout-btn:disabled {
  opacity: 0.6;
  cursor: not-allowed;
}

.dashboard-main {
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 24px;
}

.welcome-card {
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.welcome-card h2 {
  margin: 0 0 12px 0;
  color: #333;
  font-size: 28px;
}

.welcome-card p {
  color: #666;
  font-size: 16px;
  line-height: 1.6;
  margin: 8px 0;
}

.subtitle {
  color: #888;
  font-size: 14px;
  margin-bottom: 32px;
}

.feature-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
  gap: 24px;
  margin-top: 32px;
}

.feature-item {
  padding: 24px;
  background: #f8f9fa;
  border-radius: 8px;
  text-align: center;
  transition: all 0.3s ease;
}

.feature-item:hover {
  transform: translateY(-4px);
  box-shadow: 0 6px 16px rgba(0, 0, 0, 0.1);
}

.feature-icon {
  font-size: 48px;
  margin-bottom: 16px;
}

.feature-item h3 {
  margin: 0 0 8px 0;
  color: #333;
  font-size: 18px;
}

.feature-item p {
  margin: 0;
  color: #666;
  font-size: 14px;
}

@media (max-width: 768px) {
  .header-content {
    flex-direction: column;
    gap: 16px;
    align-items: flex-start;
  }

  .header-actions {
    width: 100%;
    justify-content: space-between;
  }

  h1 {
    font-size: 20px;
  }

  .welcome-card {
    padding: 24px;
  }

  .welcome-card h2 {
    font-size: 24px;
  }

  .feature-grid {
    grid-template-columns: 1fr;
  }
}
</style>