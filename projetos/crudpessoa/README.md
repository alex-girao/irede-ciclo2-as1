# CRUD de Pessoa

Projeto Java simples de CRUD de pessoas **em memória** (sem banco de dados), usado para praticar **testes unitários com JUnit 5**.

## Regras de negócio

A classe `PessoaService` implementa as operações de cadastro, busca, listagem, atualização e remoção, seguindo as regras:

- Nome é obrigatório (não pode ser nulo nem vazio).
- CPF deve ter exatamente 11 dígitos numéricos.
- CPF não pode se repetir.
- Buscar, atualizar ou remover uma pessoa inexistente lança `IllegalArgumentException`.

## Tecnologias

- Java 25
- Maven
- JUnit 5 (Jupiter)

## Estrutura

```
src
├── main/java/br/org/irede/crudpessoa
│   ├── Main.java                  # Exemplo de uso
│   ├── model/Pessoa.java          # Entidade
│   └── service/PessoaService.java # Regras de negócio (CRUD)
└── test/java/br/org/irede/crudpessoa
    └── service/PessoaServiceTest.java
```

## Como executar

Compilar e rodar a aplicação:

```bash
mvn compile exec:java
```

Executar os testes:

```bash
mvn test
```

## Desafio

1. **Criar os testes que faltam para a camada de Service.**
   Hoje, `PessoaServiceTest` cobre apenas parte do método `cadastrar`. Escreva testes para os demais cenários, por exemplo:
   - `cadastrar`: nome nulo, CPF nulo, CPF com letras, CPF com mais de 11 dígitos, CPF duplicado.
   - `buscarPorId`: pessoa existente e pessoa inexistente.
   - `listar`: lista vazia e lista com pessoas cadastradas.
   - `atualizarNome`: atualização válida, nome inválido e id inexistente.
   - `remover`: remoção válida e id inexistente.

2. **Adicionar o relatório de cobertura de testes.**
   Configure o plugin [JaCoCo](https://www.jacoco.org/jacoco/trunk/doc/maven.html) no `pom.xml` para gerar o relatório de cobertura ao rodar `mvn test`. O relatório deve ficar disponível em `target/site/jacoco/index.html`.
