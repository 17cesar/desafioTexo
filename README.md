# Desafio

Desenvolva uma API RESTful para possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards.

# Requisito do sistema:

1. Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao iniciar a
aplicação.

# Requisitos da API:

1. Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
obteve dois prêmios mais rápido, seguindo a especificação de formato definida na
página 2;


## pré requisitos
* java 17 
* H2 (DataBase)
* maven

## Como executar

* Clone o repositórios a partit do comando `git clone https://github.com/17cesar/desavioCesar.git`;
* Caso necessite alterar o arquivo para importação dos dados, o arquivo esta na pasta desafioTexo\src\main\resources com o nome movielist.csv;
* Para iniciar a aplicação e necessario acessar o diretorio do projeto e executar o comando `mvn spring-boot:run` 
* ou se preferir  acessar desafioTexo\target e executar java -jar desafioTexo-1.0.0-SNAPSHOT.jar;

* Para executar chamada da API do desafio basta aacessar o inserir link: http://localhost:8080/api/producer/winners-breaks.
 