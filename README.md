# 🏛️ Institucional Repository

[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
![Java](https://img.shields.io/badge/Backend-Java-blue)
![Vue.js](https://img.shields.io/badge/Frontend-Vue.js-brightgreen)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![Build](https://img.shields.io/badge/Build-Passing-success)

> Repositório digital para gestão de **informações, processos e documentos institucionais** (PPG).  
> Protótipo seguindo boas práticas de **Engenharia de Software**, **versionamento** e **Engenharia de Prompt (IA assistiva)**.

---

## 🧭 Visão Geral

Este projeto tem como objetivo construir um **sistema centralizado** para o armazenamento, consulta e organização de documentos institucionais.  
O sistema combina **backend Java (Spring Boot)** e **frontend Vue.js**, com integração futura de **IA generativa** para consultas inteligentes.

---

## 🧠 Funcionalidades Planejadas

- 🔍 Busca semântica assistida por IA  
- 🗂️ Organização automática de documentos  
- 🧱 Controle de acesso por perfil (OAuth2 / JWT)  
- 📑 Histórico e versionamento de processos  
- ☁️ Deploy automatizado (CI/CD + Docker)

---

## ⚙️ Instalação e Execução

### 🔧 Backend (Java / Spring Boot)

```bash
cd backend
./gradlew clean build
./gradlew bootRun
````

> Servidor iniciado em: [http://localhost:8080](http://localhost:8080)

---

### 💻 Frontend (Vue.js)

```bash
cd frontend
npm install
npm run serve
```

> Interface disponível em: [http://localhost:5173](http://localhost:5173)

---

## 🧩 Estrutura do Projeto

```
📦 Institucional-Repo/
 ┣ 📂 backend/         # API REST em Java (Spring Boot)
 ┣ 📂 frontend/        # Interface em Vue.js
 ┣ 📂 docs/            # Documentação técnica e institucional
 ┣ 📄 LICENSE
 ┣ 📄 README.md
 ┗ 📄 .gitignore
```

---

## 🧪 Boas Práticas e Engenharia

* Arquitetura modular e escalável
* Separação clara entre camadas (MVC / REST)
* Versionamento semântico (`main`, `dev`, `feature/*`)
* Pipelines planejados com GitHub Actions
* Documentação viva em `/docs`

---

## 🤖 Engenharia de Prompt (IA)

O módulo de **Engenharia de Prompt** permitirá:

* Consulta inteligente de processos/documentos
* Geração de resumos automáticos
* Sugestões contextuais de classificação e metadados

*(Em desenvolvimento — integração futura com modelos LLM locais ou via API)*

---

## 🪪 Licença

Este projeto está licenciado sob os termos da [MIT License](LICENSE).

---

## 👥 Autores e Colaboradores

Desenvolvido por **Kiamisa** e colaboradores.
Contribuições são bem-vindas via *Pull Requests* e *Issues*.

---

## 🗓️ Histórico de Versões

| Versão |      Data     | Descrição                                             |
| :----: | :-----------: | :---------------------------------------------------- |
|  0.1.0 |   20/10/2025  | Estrutura inicial do repositório (backend + frontend) |
|  0.2.0 | *(planejada)* | Adição do módulo de IA e autenticação                 |

---

## 🛰️ Roadmap

* [ ] Implementar autenticação e controle de acesso
* [ ] Criar API de consulta a documentos
* [ ] Integrar IA de busca semântica
* [ ] Configurar CI/CD com GitHub Actions
* [ ] Publicar versão Beta

---

**Última atualização:** Outubro de 2025

