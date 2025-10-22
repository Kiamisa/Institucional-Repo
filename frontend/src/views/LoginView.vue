<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Repositório Institucional</h2>
          <!-- <label for="email">Email</label>
          <input type="email" id="email" placeholder="Digite seu email" />
        </div>
        <div class="input-group">
          <label for="password">Senha</label>
          <input type="password" id="password" placeholder="Digite sua senha" /> -->
          <form @submit.prevent="fazerLogin">
            <div class="input-group">
              <label for="email">Email Institucional</label>
              <input type="email" id="email" v-model="email" placeholder="Digite seu email" required>
            </div>            
            <div class="input-group">
              <label for="password">senha</label>
              <input type="password" id="password" v-model="password" placeholder="Digite sua senha" required>
            </div>
            <div v-if="errorMessage" class="error-message">{{ errorMessage }}</div>
            <button type="submit">Entrar</button>
          </form>
        </div>
    </div>
    <div class="text-center mt-4">
    <router-link to="/register" class="inline-block align-baseline font-bold text-sm text-blue-600 hover:text-blue-800">
      Não tem conta? Registe-se aqui
    </router-link>
    </div>
</template>

<script>
import apiClient from '@/services/api'; // importação do serviço da  API

export default{
  name: 'LoginView',
  data(){
    return{
      // cria as variaveis que serao ligadas aos inputs com v-model
      email: '',
      password: '',
      errorMessage: null
    };
  },

  methods:{
    // reescreve o método de login para ser assicrono usando Axios
    async fazerLogin(){
      this.errorMessage = null;
      // validacao para campos vazios
      if (!this.email || !this.password){
        alert('Favor preencher email e a senha.');
        return;
      }
      try{
        // Exibir os dados que serão enviados ao console
        const response = await apiClient.post('/auth/login',{
          email: this.email,
          password: this.password
        });
        //Para extração do token
        const token = response.data.token;

        //Para guardar o token
        localStorage.setItem('user-token', token);

        //Configuração para enviar o token para futuras requisições
        apiClient.defaults.headers.common['Authorization'] = `Bearer ${token}`;

        //Redirecionar o utilizador para o dashboard
        this.$router.push('/dashboard');

        //Se bem sucedido
        alert('Login bem sucedido!');
        //Redirecionar para o dashboard
      } catch(error){
        //Se falhar
        this.errorMessage = 'Falha no login. Verifique as suas credenciais.';
        console.error('Erro ao fazer login:', error);
        // Garante que qualquer token antigo seja removido em caso de falha
        localStorage.removeItem('user-token');
      }
    }
  }
}
</script>

<style scoped>
/* O 'scoped' garante que este CSS só se aplica a esta página */
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.login-box {
  background: white;
  padding: 40px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
  text-align: center;
}
h2 {
  margin-bottom: 24px;
}
.input-group {
  margin-bottom: 20px;
  text-align: left;
}
label {
  display: block;
  margin-bottom: 8px;
}
input {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}
button {
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 4px;
  background-color: #007bff;
  color: white;
  font-size: 16px;
  cursor: pointer;
}

.error-message{
  color: red;
  margin-bottom:15px;
  font-size: 14.px;
}
</style>