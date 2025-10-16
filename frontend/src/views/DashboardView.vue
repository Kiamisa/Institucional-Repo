<template>
  <div class="dashboard-container">
    <header>
      <h1>Repositório PPG</h1>
      <button @click="fazerLogout">Sair</button>
    </header>
    <main>
      <h2>Bem-vindo!</h2>
      <p>Esta é a sua área principal. Em breve, aqui ficará a lista de documentos.</p>
    </main>
  </div>
</template>

<script>
import apiClient from '@/services/api';

export default {
  name: 'DashboardView',
  methods: {
    fazerLogout() {
      // 1. Remove o token do localStorage
      localStorage.removeItem('user-token');

      // 2. Remove o cabeçalho de autorização do Axios
      delete apiClient.defaults.headers.common['Authorization'];

      // 3. Redireciona de volta para a página de login
      this.$router.push('/login');
    }
  }
}
</script>

<style scoped>
.dashboard-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
  padding-bottom: 10px;
}
header button {
  padding: 8px 16px;
  border: none;
  background-color: #dc3545;
  color: white;
  border-radius: 4px;
  cursor: pointer;
}
main {
  padding-top: 20px;
}
</style>