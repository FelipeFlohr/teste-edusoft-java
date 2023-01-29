# Teste Edusoft - Java
Repositório contendo o teste da Edusoft para a vaga de Denseolvedor Java Júnior.

# Índice
- [1. Relatório de execução](#1-relatório-de-execução)
- [2. Como rodar a aplicação (sem Docker)](#2-como-rodar-a-aplicação-sem-docker)
- [3. Como rodar a aplicação (com Docker)](#3-como-rodar-a-aplicação-com-docker)

## 1. Relatório de execução:

- **Tempo gasto em horas**: aproximadamente 16 horas gastas no decorrer de dois dias.

- **Dificuldades encontradas**: as maiores dificuldades encontradas foram em relação á Inversão de Controle, Injeção de Dependências e mapeamento de retornos da API.  Até então a minha única experiência com Inversão de Controle e Injeção de Dependências no Java foi utilizando a Spring Framework, porém, resolvi executar o projeto sem utilizar a mesma pois não vi necessidade para tal. Dito isso, fui atrás de alguma biblioteca de injeção de dependências e me deparei com a "*Guice*", uma biblioteca da Google. Como ela é similar à uma outra biblioteca de injeção de dependências que eu comumente utilizo no Node.js, consegui me acostumar mais facilmente. Em relação ao mapeamento de retornos da API, minha maior dificuldade foi na hora de transformar o retorno em JSON para objetos, na qual eu tive que aprender uma nova biblioteca: a "Gson" - também da Google - que serve para lidar com JSONs.

- **Descrição geral do projeto**: o projeto foi feito pensando em uma arquitetura limpa baseada em conceitos SOLID, para isso, foi aplicada regras de injeção de dependência e inversão de controle. Cada módulo da aplicação está disponível na pasta "*modules*" e suas respectivas implementações ficam dentro da pasta "*impl*". Abaixo você encontra informações de como rodar a aplicação.

## 2. Como rodar a aplicação (sem Docker)
Para rodar a aplicação sem Docker, você precisará dos seguintes itens:

- [JDK (Java Development Kit) 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- IDE de sua preferência ([Eclipse](https://www.eclipse.org/downloads/) é recomendado)
- Um clone desse repositório

Após isso, siga os seguintes passos:
1. Abra o Eclipse na pasta raíz desse repositório
2. Crie um arquivo chamado ***application.properties*** e coloque-o no diretório "*src/main/resources*". Nele, você deverá preencher as informações de autenticação espelhando-se no arquivo "*[application.properties.sample](./src/main/resources/application.properties.sample)*", localizado em "*src/main/resources/application.properties.sample*"
3. Instale as dependências do Maven
4. Execute o arquivo [Main](./src/main/java/br/com/edusoft/testejava/Main.java) localizado em "*src/main/java/br/com/edusoft/testejava/Main.java*".

## 3. Como rodar a aplicação (com Docker)
Para rodar a aplicação com o Docker, você precisará dos seguintes itens:

- [Docker](https://www.docker.com/)
- Habilitar a virtualização no computador
- Um clone desse repositório

Após isso, siga os seguintes passos:
1. Crie um arquivo chamado ***application.properties*** e coloque-o no diretório "*src/main/resources*". Nele, você deverá preencher as informações de autenticação espelhando-se no arquivo "*[application.properties.sample](./src/main/resources/application.properties.sample)*", localizado em "*src/main/resources/application.properties.sample*"
2. Abra um terminal na pasta raiz do repositório
3. Rode o comando `docker-compose up -d`
4. Veja a aplicação sendo executada no Docker Desktop.