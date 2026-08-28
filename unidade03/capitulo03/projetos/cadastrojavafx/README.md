# Cadastro JavaFX

Aplicação desktop de **cadastro de pessoas**, feita em **Java** com interface gráfica **JavaFX**.

Este repositório é didático: o objetivo é aprender como uma tela, um controlador e um modelo se encaixam no padrão MVC, usando Maven e o NetBeans.

---

## O que você precisa ter instalado

Antes de abrir o projeto, confira:

1. **JDK 17 ou superior** (recomendado: JDK 21)  
   O `pom.xml` declara compilação para Java 11, mas as bibliotecas **JavaFX 21** funcionam melhor com JDK 17+.  
   Para conferir no terminal:

   ```bash
   java -version
   ```

2. **Apache Maven 3.8+** (se for rodar pelo terminal; o NetBeans já traz o Maven embutido)

   ```bash
   mvn -version
   ```

3. **Apache NetBeans** (opcional, mas é o IDE em que o projeto foi criado)

Não é preciso baixar o JavaFX na mão: o Maven baixa `javafx-controls` e `javafx-fxml` automaticamente na primeira compilação.

---

## Como executar

### Pelo NetBeans

1. Abra o NetBeans.
2. **File → Open Project** e escolha a pasta `cadastrojavafx`.
3. Clique com o botão direito no projeto → **Run** (ou o atalho de execução).

O NetBeans usa o arquivo `nbactions.xml`, que chama `mvn clean javafx:run`.

### Pelo terminal (Maven)

Na pasta do projeto:

```bash
mvn clean javafx:run
```

A janela **Cadastro de Pessoas** deve abrir (640×480).

### Compilar sem abrir a janela

```bash
mvn clean compile
```

Os arquivos compilados vão para a pasta `target/` (ela **não** entra no Git).

---

## Estrutura do projeto (o que cada pasta faz)

```
cadastrojavafx/
├── pom.xml                          # Receita Maven: Java, JavaFX e plugins
├── nbactions.xml                    # Atalhos Run / Debug do NetBeans
├── src/main/java/                   # Código Java
│   └── br/org/irede/grafico/cadastrojavafx/
│       ├── App.java                 # Ponto de entrada: abre a janela
│       ├── PrimaryController.java   # Responde a cliques e eventos da tela
│       ├── model/
│       │   └── Pessoa.java          # Dados de uma pessoa (id e nome)
│       └── module-info.java         # Módulo Java: o que o app precisa e exporta
└── src/main/resources/              # Arquivos que NÃO são Java
    └── br/org/irede/grafico/cadastrojavafx/
        └── primary.fxml             # Layout da tela (XML)
```

Regra prática:

| Onde está | Para que serve |
|-----------|----------------|
| `src/main/java` | Lógica (abrir janela, tratar botão, guardar dados) |
| `src/main/resources` | Layout FXML, imagens, CSS |
| `pom.xml` | Dependências e como compilar/rodar |
| `target/` | Resultado da compilação — pode apagar; o Maven recria |

---

## Conceitos para quem está começando

### Maven e o `pom.xml`

O Maven é o “gerente de obras” do projeto Java. O arquivo `pom.xml` diz:

- qual a versão do Java;
- quais bibliotecas usar (aqui: JavaFX Controls e FXML);
- como executar o app (`javafx-maven-plugin`, classe principal `App`).

Na primeira vez que você roda `mvn`, ele baixa as dependências da internet. Depois elas ficam no cache local (`~/.m2`).

### JavaFX, Stage, Scene e FXML

Pense na interface em três camadas:

1. **Stage** — a janela do sistema (título, botão fechar).
2. **Scene** — o conteúdo desenhado **dentro** da janela.
3. **FXML** — um XML que descreve botões, tabelas e layouts. O JavaFX transforma esse XML em objetos reais na tela.

A classe `App` carrega `primary.fxml`, coloca o resultado numa `Scene` e chama `stage.show()`. Sem o `show()`, a janela existe na memória mas não aparece.

O método `main` **não** chama `start()` direto: chama `launch()`, que inicia a plataforma JavaFX e, em seguida, o `start()`.

### MVC neste projeto

| Letra | Nome | Arquivo neste projeto |
|-------|------|------------------------|
| **M** | Model (dados) | `Pessoa.java` |
| **V** | View (tela) | `primary.fxml` |
| **C** | Controller (eventos) | `PrimaryController.java` |

A tela (`primary.fxml`) aponta para o controlador com:

```xml
fx:controller="br.org.irede.grafico.cadastrojavafx.PrimaryController"
```

Quando você ligar um botão no FXML (`onAction="#salvar"`), o método `salvar` precisa existir no `PrimaryController`.

### Por que `Pessoa` usa Property e não `int` / `String` puros?

No JavaFX, `SimpleIntegerProperty` e `SimpleStringProperty` são valores **observáveis**: a interface pode atualizar sozinha quando o dado muda.

Há dois tipos de getter:

- `getId()` / `getNome()` — valor comum, útil para o resto do Java (banco, impressão).
- `idProperty()` / `nomeProperty()` — a propriedade observável. A `TableView` usa isso (por exemplo com `PropertyValueFactory`).

O nome `idProperty()` não é capricho: é a convenção que o JavaFX procura por reflexão.

### Módulos (`module-info.java`)

O Java moderno pode empacotar o app como um **módulo**. Este arquivo declara:

- `requires javafx.controls` e `requires javafx.fxml` — bibliotecas necessárias;
- `opens ... to javafx.fxml` — o FXML precisa “enxergar” o controlador (campos `@FXML`);
- `exports` — o que outras partes do sistema podem usar.

Se você criar um pacote novo (por exemplo `model`) e o FXML ou a reflexão precisarem acessá-lo, pode ser necessário `opens` ou `exports` desse pacote também.

---

## Arquivos principais (resumo)

- **`App.java`** — inicia o JavaFX, carrega o FXML e mostra a janela. `setRoot()` troca o conteúdo da mesma janela sem abrir outra.
- **`primary.fxml`** — tela atual: um `BorderPane` com o título “Cadastro de Pessoas”. Campos, botões e tabela ainda podem ser acrescentados aqui.
- **`PrimaryController.java`** — ainda vazio; é o lugar dos métodos ligados aos controles da tela.
- **`Pessoa.java`** — modelo com `id` e `nome`, pronto para alimentar uma tabela.

---

## Dicas e problemas comuns

| Problema | O que conferir |
|----------|----------------|
| `Error: JavaFX runtime components are missing` | Rode com `mvn javafx:run`, não com `java -jar` simples (o plugin já configura o módulo JavaFX). |
| FXML não encontrado | O arquivo precisa estar em `src/main/resources/.../cadastrojavafx/` com o **mesmo** pacote da classe `App`. O nome no código é sem `.fxml` (`loadFXML("primary")`). |
| Tela em branco / controlador não dispara | Confira `fx:controller` no FXML e se os `fx:id` batem com os campos `@FXML` no Java. |
| Compilação Java 11 vs JavaFX 21 | Prefira JDK 17 ou 21 instalado e selecionado no NetBeans (**Tools → Java Platforms**). |

---

## Próximos passos sugeridos (para estudar)

1. Completar o FXML: campos de texto, botão Salvar e uma `TableView`.
2. No `PrimaryController`, criar uma lista observável (`ObservableList<Pessoa>`) e ligá-la à tabela.
3. Implementar incluir, editar e excluir pessoas em memória.
4. (Avançado) Persistir em arquivo ou banco de dados.

---

## Licença e origem

Projeto acadêmico / de estudo (IREDE — pacote `br.org.irede.grafico`). Use e adapte para aprendizado.
