# 🧠 Acolher Bem

**Acolher Bem** é um aplicativo educacional desenvolvido com o objetivo de promover o bem-estar emocional de alunos por meio de um ambiente acolhedor que conecta estudantes, professores e psicólogos. A plataforma permite registrar emoções, acompanhar o histórico emocional, e proporciona suporte psicológico de forma simples e acessível.

## ✨ Objetivo

Combater a evasão escolar e promover a saúde mental nas escolas públicas e privadas por meio de um canal seguro, acessível e offline para que os alunos possam expressar como estão se sentindo, sendo acolhidos por educadores e profissionais da psicologia.

## 🧩 Funcionalidades

- ✅ **Cadastro e login** de alunos, professores e psicólogos  
- ✅ Registro de emoções via **slider intuitivo**  
- ✅ Acompanhamento do histórico emocional dos alunos  
- ✅ Interface leve e acolhedora  
- ✅ Sistema de sessões por tipo de usuário  
- ✅ Armazenamento local usando **SQLite**

## 💡 Tecnologias Utilizadas

- **Java**
- **JavaFX (FXML)**
- **SQLite**
- **BCrypt** (para criptografia de senhas)
- **Padrão MVC**
- **Maven**
- **FXML + CSS** para uma interface moderna

## 📁 Estrutura do Projeto
📦 AcolherBem
- ┣ 📁 src
- ┃ ┗ 📁 main
- ┃ ┣ 📁 java
- ┃ ┃ ┗ 📁 com.project.project_healtheducation
- ┃ ┃ ┣ 📁 controllers ← Lógica das telas
- ┃ ┃ ┣ 📁 dao ← Comunicação com SQLite
- ┃ ┃ ┣ 📁 db ← Setup do banco de dados
- ┃ ┃ ┣ 📁 model ← Classes de dados (Aluno, Professor, etc)
- ┃ ┃ ┣ 📁 utils ← Sessão do usuário, utilitários
- ┃ ┃ ┗ 📄 MainApplication.java
- ┃ ┗ 📁 resources
- ┃ ┗ 📁 com.project.project_healtheducation
- ┃ ┣ 📁 view ← FXMLs (telas do sistema)
- ┃ ┗ 📁 style ← CSS personalizado
- ┣ 📄 pom.xml
- ┣ 📄 bancoDeDados.db
- ┗ 📄 README.md


---

## 🧠 Funcionalidades

- Cadastro e login de Aluno, Professor e Psicólogo  
- Registro de emoções com slider visual  
- Armazenamento local das emoções com data
- Autenticação segura com BCrypt
- Interface adaptável e acessível
- Suporte a diferentes perfis de usuário

---

## 🚀 Como executar

1. Clone o projeto:
   ```bash
   git clone https://github.com/lemirving/Projeto-AcolherBem.git

Abra no IntelliJ ou Eclipse

Rode o projeto pela classe MainApplication.java

O banco bancoDeDados.db será criado automaticamente, se não existir.

#### 💡Requer Java 17 e JavaFX configurado no ambiente

## 📌 Observações
As senhas são criptografadas antes de serem salvas.

O banco SQLite é local e acessado diretamente via DAO.

O projeto é modularizado, facilitando a expansão (ex: histórico emocional, chat privado etc).
