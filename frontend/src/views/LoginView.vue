<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Repositório Institucional</h2>
      <form @submit.prevent="fazerLogin">
        <div class="input-group">
          <label for="email">Email</label>
          <input type="email" id="email" placeholder="Digite seu email" />
        </div>
        <div class="input-group">
          <label for="password">Senha</label>
          <input type="password" id="password" placeholder="Digite sua senha" />
        </div>
        <button type="submit">Entrar</button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
  methods: {
    fazerLogin() {
      // Por enquanto, esta função apenas mostra um alerta.
      // Mais tarde, faremos a chamada para a API aqui.
      alert('Tentando fazer login...');
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