<template>
  <div class="min-h-screen bg-gray-100 flex flex-col items-center p-6">
    <header class="w-full max-w-4xl bg-white p-6 rounded-lg shadow-md mb-6 flex justify-between items-center">
      <div>
        <h1 class="text-3xl font-bold text-blue-800">Repositório PPG - UEMA</h1>
        <p class="text-gray-600">Produções Acadêmicas e Editais</p>
      </div>
      <router-link to="/login">
        <button class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded transition duration-300">
          Login (PPG)
        </button>
      </router-link>
    </header>

    <main class="w-full max-w-4xl bg-white p-6 rounded-lg shadow-md">
      <h2 class="text-2xl font-semibold mb-4 text-gray-800">Bem-vindo!</h2>
      <p class="text-gray-700 mb-4">
        Este é o portal de acesso às produções acadêmicas e editais do Programa de Pós-Graduação da UEMA.
      </p>
      <p class="text-gray-700">
        Navegue pelas informações públicas ou utilize o botão de login (exclusivo para pessoal do PPG) para aceder à área de gestão.
      </p>
      </main>
  </div>
</template>

<script>
import { RouterLink } from 'vue-router'; // Importar se usar <script setup> não for o caso

export default {
  name: 'HomeView',
  components: {
    RouterLink // Registrar se não usar <script setup>
  }
  // Nenhuma lógica JavaScript necessária por enquanto
}
</script>

<style scoped>
/* Podemos manter o scoped vazio ou adicionar estilos muito específicos se necessário */
</style>