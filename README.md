# Índice

* [Introdução](#challenge-one---literalura)
* [Funcionalidades e Demonstração da Aplicação](#funcionalidades-e-demonstração-da-aplicação)
* [Acesso ao Projeto](#acesso-ao-projeto)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)

# Challenge ONE - LiterAlura

Neste emocionante desafio de programação, convidamos você a construir seu próprio catálogo de livros: o LiterAlura. Você aprenderá a realizar solicitações a uma API de livros, manipular dados JSON, armazená-los em um banco de dados e, por fim, a filtrar e mostrar os livros e autores de interesse.

## Objetivo

Desenvolver um Catálogo de Livros que ofereça interação textual (via console) com os usuários, proporcionando no mínimo 5 opções de interação. Os livros serão buscados através de uma API específica ([Gutendex](https://gutendex.com/)).

# Funcionalidades e Demonstração da Aplicação

O LiterAlura permite aos usuários interagir com um catálogo de livros através de um menu no console. As principais funcionalidades incluem:

- **Buscar livro pelo título:** Realiza uma busca na API de livros pelo título informado.
- **Listar livros registrados:** Exibe todos os livros armazenados no banco de dados.
- **Listar autores registrados:** Exibe todos os autores armazenados no banco de dados.
- **Listar autores com seus respectivos livros:** Exibe todos os autores com os livros que escreveram.
- **Listar autores vivos em um determinado ano:** Permite buscar autores que estavam vivos em um ano específico.
- **Listar livros por idioma:** Permite buscar livros em um idioma específico.

## Exemplo de Utilização

Abaixo seguem exemplos de utilização da aplicação:

<p>Menu Principal</p>

![menu](https://github.com/josuemleite/literalura/assets/84863364/8f9e75bf-ebe2-44b5-b525-d30d8e235a7d)

<p>Pesquisa por Livros</p>

![search](https://github.com/josuemleite/literalura/assets/84863364/618e6bc8-09de-4598-9573-7ce0689adb8d)

<p>Listagem de Livros Registrados</p>

![list_books](https://github.com/josuemleite/literalura/assets/84863364/34823728-73f6-4ded-be90-434d2ff2da36)

<p>Pesquisa por Ano</p>

![search_by_year](https://github.com/josuemleite/literalura/assets/84863364/0fe537ce-3c61-438f-8500-3677bf43035b)

<p>Pesquisa por Idioma</p>

![search_by_language](https://github.com/josuemleite/literalura/assets/84863364/c9f62ff3-32d6-435e-b85f-9df072072b22)

<p>Tratamento de Exceção</p>

![input_handler](https://github.com/josuemleite/literalura/assets/84863364/6bdb1c82-8cdd-494a-903e-8d6ab03e8cb0)

# Acesso ao Projeto

Para acessar o projeto, basta cloná-lo em uma máquina com o `git` ou baixá-lo diretamente pelo GitHub. Em seguida, execute a aplicação em uma IDE Java de sua preferência.

## Configuração do Ambiente

1. **Java 17:** Certifique-se de que a versão do Java instalada seja a 17.
2. **Dependências:** As dependências do projeto estão especificadas no arquivo `pom.xml` caso esteja utilizando Maven.

## Estrutura do Projeto

Os arquivos de código estão organizados em diretórios autoexplicativos. Valem menção:

- **ConsumerApi:** Classe responsável por fazer requisições à API externa.
- **DataConverter:** Classe responsável por converter os dados JSON retornados pela API.
- **Repositories:** Interfaces para acesso ao banco de dados (AuthorRepository e BookRepository).

# Tecnologias Utilizadas

- **Java 17**
- **Spring Boot:** Para facilitar a criação de APIs e a interação com o banco de dados.
- **JPA/Hibernate:** Para o mapeamento objeto-relacional e manipulação dos dados.
- **PostgreSQL:** Banco de dados para armazenamento de informações.
