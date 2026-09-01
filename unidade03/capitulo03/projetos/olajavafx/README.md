# Olajavafx — primeira aplicação JavaFX

Este repositório é um exemplo **mínimo** de interface gráfica em Java com **JavaFX**.
Ao executar o programa, abre-se uma janela com um botão escrito “Clique aqui!”.

O objetivo é servir de ponto de partida: você entende a estrutura de um projeto Maven + JavaFX e depois troca o botão por telas reais.

---

## O que você precisa saber antes (em 1 minuto)

| Conceito | O que é, na prática |
| --- | --- |
| **Java** | Linguagem em que o código está escrito. |
| **JavaFX** | Biblioteca para criar janelas, botões, formulários e gráficos (a “cara” do programa). |
| **Maven** | Ferramenta que baixa bibliotecas e **compila** o projeto. O arquivo `pom.xml` é a “receita” do Maven. |
| **Módulo** (`module-info.java`) | Desde o Java 9, o código pode declarar o que precisa importar. O JavaFX exige isso neste projeto. |
| **NetBeans** | IDE (editor) usada para abrir e rodar o projeto com um clique. Não é obrigatória: dá para usar o terminal. |

**Swing vs JavaFX:** o Swing é a biblioteca antiga de interfaces do Java. O JavaFX é o caminho moderno para desktop.

---

## Requisitos

Antes de rodar, instale:

1. **JDK 21** (Java Development Kit) — o `pom.xml` está configurado para a versão **21**.
2. **Maven** — ou use o Maven embutido do NetBeans (não precisa instalar à parte se for só pelo IDE).
3. Opcional: **Apache NetBeans** (o projeto já tem `nbactions.xml` para os botões Run / Debug).

Para conferir no terminal:

```bash
java -version
mvn -version
```

Ambos devem aparecer sem erro. O Java precisa ser **21** (ou compatível com 21).

---

## Como executar

### Pelo NetBeans

1. **File → Open Project** e escolha a pasta `olajavafx`.
2. Clique com o botão direito no projeto → **Run**.

O NetBeans usa o plugin JavaFX (`javafx:run`), definido em `nbactions.xml`.

### Pelo terminal (Maven)

Na pasta do projeto:

```bash
mvn clean javafx:run
```

- `clean` apaga a pasta `target/` (compilações antigas).
- `javafx:run` compila e abre a janela.

Para **depurar** (o programa espera o depurador na porta 8000):

```bash
mvn clean javafx:run@debug
```

---

## Estrutura das pastas

```
olajavafx/
├── pom.xml                          # receita Maven: Java 21, JavaFX, classe principal
├── nbactions.xml                    # o que o NetBeans faz ao clicar em Run / Debug
├── src/main/java/
│   ├── module-info.java             # declara o módulo e as dependências JavaFX
│   └── br/org/irede/grafico/olajavafx/
│       └── Olajavafx.java           # a janela e o botão
└── target/                          # gerado automaticamente (não edite; está no .gitignore)
```

Pasta `src/main/java` é o padrão Maven: **código-fonte** fica aqui. Classes compiladas vão para `target/`.

O pacote `br.org.irede.grafico.olajavafx` é só o “endereço” da classe. O nome do arquivo deve coincidir com o da classe: `Olajavafx.java` → `class Olajavafx`.

---

## Como o código funciona

Arquivo principal: `src/main/java/br/org/irede/grafico/olajavafx/Olajavafx.java`.

### 1. A classe herda de `Application`

```java
public class Olajavafx extends Application {
```

Toda app JavaFX **estende** `javafx.application.Application`. O JavaFX chama o método `start` quando a aplicação sobe.

### 2. O `main` só inicia o JavaFX

```java
public static void main(String[] args) {
    launch(args);
}
```

`launch` é o método da classe `Application`: sobe o runtime gráfico e, em seguida, chama `start`.

### 3. O método `start` monta a tela

O parâmetro `Stage` é a **janela** do sistema operacional (título, tamanho, fechar/minimizar).

Dentro dela você coloca, em camadas:

| Peça | Papel neste exemplo |
| --- | --- |
| `Button` | O botão “Clique aqui!”. |
| `StackPane` | Um **layout**: empilha filhos no centro. Aqui só tem o botão. |
| `Scene` | A “cena”: o conteúdo da janela (layout + tamanho 300×200). |
| `Stage` | A janela: título, cena, e `show()` para aparecer. |

Fluxo resumido:

```
Stage (janela)
  └── Scene (cena 300×200)
        └── StackPane (layout)
              └── Button ("Clique aqui!")
```

Hoje o botão **não faz nada** ao clicar. Para reagir ao clique, você ligaria um evento, por exemplo:

```java
botao.setOnAction(evento -> {
    System.out.println("Botão clicado!");
});
```

`setOnAction` recebe o que deve acontecer quando o usuário clica. O `evento` é o objeto do clique (neste exemplo não é usado).

---

## O arquivo `module-info.java`

```java
module br.org.irede.grafico.olajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    exports br.org.irede.grafico.olajavafx;
}
```

- **`module`**: nome do módulo (em geral igual ao pacote raiz).
- **`requires javafx.controls`**: precisamos de controles (botão, cena, layouts).
- **`requires javafx.fxml`**: reserva o FXML (telas em XML). Este exemplo ainda **não usa** FXML; a dependência já está no `pom.xml` para quando você quiser.
- **`exports`**: deixa o pacote visível para o plugin que inicia a aplicação.

Se você criar um **novo pacote** e o JavaFX (ou outra biblioteca) precisar vê-lo, pode ser necessário exportar esse pacote também.

---

## O arquivo `pom.xml` (Maven)

Pontos que um iniciante costuma mexer:

| Trecho | Significado |
| --- | --- |
| `groupId` / `artifactId` / `version` | Identidade do projeto (como um “nome + versão” da biblioteca). |
| `maven.compiler.source` e `target` = 21 | Compila como Java 21. |
| `exec.mainClass` | Classe com o `main`. |
| Dependências `javafx-controls` e `javafx-fxml` | Bibliotecas baixadas automaticamente. |
| Plugin `javafx-maven-plugin` | Permite o comando `mvn javafx:run`. |

Não é preciso copiar o JavaFX na mão: o Maven baixa na primeira compilação (precisa de internet).

---

## Próximos passos (quando quiser evoluir)

1. Fazer o botão **fazer alguma coisa** (`setOnAction`).
2. Trocar `StackPane` por `VBox` ou `HBox` para empilhar vários componentes.
3. Adicionar `TextField`, `Label` e `Alert` para um mini formulário.
4. Separar a tela em **FXML** (arquivo XML da interface) + **controller** (classe Java dos eventos) — o projeto já inclui `javafx-fxml`.
5. Ler a [documentação oficial do JavaFX](https://openjfx.io/).

---

## Problemas comuns

**`UnsupportedClassVersionError`**  
O JDK instalado é mais antigo que 21. Instale o JDK 21 e aponte o NetBeans / `JAVA_HOME` para ele.

**A janela não abre / erro de JavaFX no módulo**  
Rode com `mvn javafx:run`, não com `java -jar` simples. O plugin coloca o JavaFX no módulo-path.

**`mvn` não encontrado**  
Instale o Maven ou use o Run do NetBeans.

**Erro ao baixar dependências**  
Confira a internet e o proxy. O Maven precisa acessar o repositório central na primeira vez.

---

## Licença e origem

Projeto de estudo (`1.0-SNAPSHOT`). Use e altere à vontade para aprender.
