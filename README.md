# 🏢 Projeto de Cadastro de Empresas com Spring Boot

Este projeto é uma API REST desenvolvida em **Java com Spring Boot**, que permite realizar operações de CRUD (Create, Read, Update, Delete) para **empresas** e seus **funcionários**. Utiliza o padrão **DTO** para transferência de dados entre camadas, garantindo maior segurança e controle da exposição dos dados.

---

## 🚀 Tecnologias Utilizadas

- Java 17+ (ou compatível)
- Spring Boot 
- Spring Web
- Spring Data JPA
- H2 Database (ou outro banco relacional)
- Maven


---

## 🔧 Funcionalidades da API

| Método | Endpoint           | Descrição                             |
|--------|--------------------|----------------------------------------|
| GET    | `/empresas`        | Lista todas as empresas                |
| GET    | `/empresas/{id}`   | Busca uma empresa por ID               |
| POST   | `/empresas`        | Cria uma nova empresa                  |
| PUT    | `/empresas/{id}`   | Atualiza os dados de uma empresa       |
| DELETE | `/empresas/{id}`   | Remove uma empresa do sistema          |

---

## 📦 Exemplo de JSON para Criar Empresa

```json
{
  "nome": "Empresa XPTO",
  "cnpj": "12.345.678/0001-90",
  "funcionarios": [
    {
      "nome": "João da Silva",
      "cargo": "Gerente"
    },
    {
      "nome": "Maria Oliveira",
      "cargo": "Desenvolvedora"
    }
  ]
}
```

---

## 🛠 Como Executar

1. Clone este repositório:
```bash
git clone https://github.com/cristianlumertz/as
```

2. Acesse o diretório do projeto:
```bash
cd as
```

3. Compile e rode o projeto:
```bash
./mvnw spring-boot:run
```

4. Acesse a API via:
```
http://localhost:8080/empresas
```

---
