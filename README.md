# 🚀 Desafio posto de combustivel
Aplicação simples em Java para cadastro e consulta de abastecimentos em um posto de combustível, com armazenamento em banco de dados e exibição dos dados via Java Swing ou API REST

[Link do Desafio](https://drive.google.com/file/d/1syA06wnmP2z_vd3Gl8DJnxbPw-EVeAin/view)

[Mapa Conceitual](Docs/img/mapaConceitual.png)

[Diagrama de classe UML](Docs/img/DiagramaDeClasse.png)

## 🛠 Tecnologias
- Java 17
- PostgreSQL
- Docker


## ⚙️ Funcionalidades
- [x] CRUD - Tipos de Combustível
- [x] CRUD - Bombas de Combustível
- [x] CRUD - Abastecimentos
- [x] Consulta de todos os dados
- [x] Persistência dos dados
- [x] Projeto Java usando Maven ou Gradle
- [x] Relacionamentos entre entidades corretamente implementados
- [x] Interface gráfica Java Swing ou API HTTP para cadastro e consulta
- [x] Código comentado
- [x] API RESTful simples com rotas GET, POST, PUT
- [x] Boas práticas de organização de código (DAO, camada de serviço, etc.)
- [x] Persistência dos dados (em caso de restart da aplicação manter os dados)

## Rotas da api 
[ACESSAR ROTAS](Docs/apiroutes.md)


## 🚀 Como Executar
- Via terminal
```bash
# Clonar o repositório
    git clone https://github.com/fcss-dev/itau-challenge-backend.git
    
# Entrar na pasta
    cd itau-challenge-backend 
     
# rodar o comando no terminal para subir o banco postgresql 
    docker-compose up --build
  
# Executar o projeto com Maven
    mvn spring-boot:run
# testar as rotas disponiveis 
    [Rotas da api]
```


  
