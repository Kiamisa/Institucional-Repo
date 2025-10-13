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
              <input type="password" id="password" v-model="senha" placeholder="Digite sua senha" required>
            </div>
            <button type="submit">Entrar</button>
          </form>
        </div>
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
      senha: ''
    };
  },

  methods:{
    // reescreve o método de login para ser assicrono usando Axios
    async fazerLogin(){
      // validacao para campos vazios
      if (!this.email || !this.senha){
        alert('Favor preencher email e a senha.');
        return;
      }
      try{
        // Exibir os dados que serão enviados ao console
        console.log('Enviando dados para o login: ', {email : this.email, senha : this.senha});
        // TODO: Criar endpoint para /login POST
        const response = await apiClient.post('/login',{
          username: this.email,
          password: this.senha
        });

        //Se bem sucedido
        alert('Login bem sucedido!');
        console.log('Resposta do servidor:', response.data);
        //Redirecionar para o dashboard
      } catch(error){
        //Se falhar
        alert('Falha no login. Verifique email e senha.');
        console.error('Erro ao fazer login', error);
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
</style>