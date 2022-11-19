## Execução da base de dados:

1. Abrir Pasta .devcontainer
2. Executar ficheiro docker-compose.sh


Depois do Container Docker estar a funcionar, pode-se executar o ficheiro Servidor

## Execução de ficheiro Servidor

1. Abir um terminal na pasta atual
2. Executar no terminal "java -jar ScoreDei.war"

Deve ser possivel aceder ao programa no url "http://localhost:8080/"

Na primeira execução, pressionar o botão "Create Data", presente no topo direito do ecrã, 
para inserir dados da API.
Serão também criados utilizadores "Default" com os seguintes dados:

Admin 1:
	username: "12345"
	email: "12345@12345.com"
	password: "12345"

Admin 2:
	username: "adriana"
	email: "adriana@gmail.com"
	password: "adriana"

User 1:
	username: "duarte"
	email: "duarte@gmail.com"
	password: "duarte"
