<h2 align="center">Notificação do Clima</h2>

Para construção da aplicação a seguir, foram consideradas as principais necessidades do produto. Por este motivo, foi selecionado o Websocket para fazer o envio das notificações via web.
Em paralelo a isso, foi utilizado o scheduler para garantir que fosse monitorado minuto a minuto todas as solicitações de mensagem para que fossem enviadas com pouco delay.
Para resiliencia, utilizado fallback com 3 tentativas de 5 minutos, garantindo a funcionalidade da aplicação com tolerancia a falhas.
Criado testes para os principais metodos para garantir a funcionalidade da aplicação e evitar erros.

<h2 align="center">Fluxo da aplicação</h2>

Abaixo o diagrama do fluxo de registro, chamadas de API referente a aplicação de envio de notificações do clima via websocket.

![fluxograma](https://github.com/user-attachments/assets/61dd968c-e4a8-42d5-9300-a8699cd3cf7f)


<h2 align="center">Funcionalidades</h2>

- Cadastro, edição e remoção de usuários
- Integração com API do CPTEC
- Notificação automática com dados climáticos via scheduler (`@Scheduled`)
- Suporte a notificações por cidade e previsao de ondas caso litoral
- Tolerância a falhas com (`@Retryable`)


<h2 align="center">Requisitos</h2>
- Java 17+
- Maven 3.9.9
- Docker 25.0.4


<h2 align="center">Como rodar o projeto</h2>

1. Clone o repositório
2. Mvn Clean Package
3. Ative o plugin do docker.
4. no terminal digite: docker-compose up
5. acesse a aplicação para receber as mensagens através da url http://localhost:8080/ws.html
6. cadastre e edite os usuarios através das collections abaixo.

<h2 align="center">Collection para chamadas dos endpoints:</h2>

Abaixo um arquivo das collections utilizadas para registro dos usuarios e edição e consumo das informações da Ctpec.

[Mercado Livre.postman_collection.json](https://github.com/user-attachments/files/20325127/Mercado.Livre.postman_collection.json)
