<template>
  <div class="min-h-screen bg-gray-100 flex items-center justify-center p-4">
    <div class="bg-white p-8 rounded-lg shadow-md w-full max-w-md">
      <h2 class="text-2xl font-bold text-center text-gray-800 mb-6">Criar Conta - PPG</h2>
      <form @submit.prevent="fazerRegisto">
        <div class="mb-4">
          <label for="nome" class="block text-gray-700 text-sm font-bold mb-2">Nome Completo</label>
          <input type="text" id="nome" v-model="nome" placeholder="Seu nome completo" required
                 class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <div class="mb-4">
          <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email Institucional</label>
          <input type="email" id="email" v-model="email" placeholder="seuemail@ppg.uema.br" required
                 pattern=".+@ppg\.uema\.br" title="Deve ser um email @ppg.uema.br"
                 class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
          <p class="text-xs text-gray-500 mt-1">Obrigatório ser @ppg.uema.br</p>
        </div>
        <div class="mb-6">
          <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Senha</label>
          <input type="password" id="password" v-model="senha" placeholder="Crie uma senha forte" required minlength="6"
                 class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline">
        </div>
        <!-- Futuramente, adicionar campo para selecionar o Programa -->

        <div v-if="errorMessage" class="mb-4 text-red-500 text-sm text-center">
          {{ errorMessage }}
        </div>
        <div v-if="successMessage" class="mb-4 text-green-500 text-sm text-center">
          {{ successMessage }}
        </div>

        <div class="flex items-center justify-between">
          <button type="submit"
                  class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full transition duration-300">
            Registar
          </button>
        </div>
      </form>
      <div class="text-center mt-4">
        <router-link to="/login" class="inline-block align-baseline font-bold text-sm text-blue-600 hover:text-blue-800">
          Já tem conta? Faça Login
        </router-link>
      </div>
      <!-- O link redundante para /register foi removido daqui -->
    </div>
  </div>
</template>

<!-- O bloco <script> e <style> permanecem os mesmos -->
<script>
import apiClient from '@/services/api';
import { RouterLink } from 'vue-router';

export default {
  name: 'RegisterView',
  components: {
    RouterLink
  },
  data() {
    return {
      nome: '',
      email: '',
      senha: '',
      errorMessage: null,
      successMessage: null
    };
  },
  methods: {
    async fazerRegisto() {
      this.errorMessage = null;
      this.successMessage = null;

      if (!this.nome || !this.email || !this.senha) {
        this.errorMessage = 'Por favor, preencha todos os campos.';
        return;
      }
      if (!this.email.endsWith('@ppg.uema.br')) {
         this.errorMessage = 'O email deve ser do domínio @ppg.uema.br.';
         return;
      }

      try {
        await apiClient.post('/usuarios', {
          nome: this.nome,
          email: this.email,
          senha: this.senha,
          perfil: 'USUARIO', // Perfil padrão inicial
          programa: { id: 1 } // TEMPORÁRIO
        });

        this.successMessage = 'Registo realizado com sucesso! Faça o login.';
        this.nome = '';
        this.email = '';
        this.senha = '';

      } catch (error) {
        console.error('Erro ao registar:', error);
        if (error.response && error.response.data && typeof error.response.data === 'string') {
           this.errorMessage = error.response.data;
        } else if (error.response && error.response.data && error.response.data.message) {
           this.errorMessage = error.response.data.message;
        } else {
           this.errorMessage = 'Erro ao registar. Verifique os dados ou tente novamente mais tarde.';
        }
      }
    }
  }
}
</script>

<style scoped>
input:invalid {
  border-color: red;
}
</style>