# Unidade 07 — Introdução ao ORM e ao Hibernate

Material interativo de apoio — Desenvolvimento Java

## Estrutura

| Pasta | Descrição |
|---|---|
| `capitulo01/` | Introdução ao ORM e ao Hibernate |

Segue o mesmo padrão de [`../unidade05/`](../unidade05/capitulo01/index.html): cada
`capituloNN/` tem um `index.html` autocontido (resumo, simulador, exercícios,
questionários e curiosidades) e seus arquivos `questionario-u7cN-0X.html`.

## Capítulos

### Capítulo 01 — Introdução ao ORM e ao Hibernate
Conteúdo: Mapeamento Objeto-Relacional (ORM) · Os Benefícios Estratégicos do ORM (Produtividade, Manutenibilidade, Independência do Banco, Performance) · Hibernate e JPA · Arquitetura em Camadas (Aplicação → JPA/API Nativa → Hibernate → JDBC → MySQL) · Arquitetura Principal: `SessionFactory`, `Session` e `Transaction` · Mapeamento de Entidades (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`) · Configuração com MySQL (`pom.xml` + `persistence.xml`) · Análise Comparativa: JDBC Manual vs. ORM

Banco de dados utilizado: **MySQL** (driver `mysql-connector-j`, Hibernate 6 / Jakarta Persistence).

Questionários:
- `questionario-u7c1-01.html` — Quiz 01: Conceito de ORM e seus Benefícios (8 V/F)
- `questionario-u7c1-02.html` — Quiz 02: Hibernate — SessionFactory, Session e Transaction (8 V/F)
- `questionario-u7c1-03.html` — Implementação: Hibernate com MySQL na Prática (5 questões)

Baseado no material de apoio `Java - U1C1O1S1 - Slide.pdf` (Introdução ao ORM e ao Hibernate).

## Navegação

- Módulo anterior: [`../unidade06/capitulo01/`](../unidade06/capitulo01/index.html)
