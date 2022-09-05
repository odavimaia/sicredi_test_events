# *Teste Sicredi - Events*

*Este projeto tem como objetivo consumir os dados de uma api e:*

* Listar os eventos na tela
* Oferecer ao usuário a opção de tocar no evento
* Fornecer os detalhes do evento quando selecionado
* Oferecer a opção de fazer check-in no evento
* Oferecer a opção de compartilhar o evento

além de ter uma opção adicional:

* Fornecer a localização do evento para o usuário;

#### O app foi construido para atender a api 19+ do android, além de ser baseado em <br>
#### MVVM e Clean Archtecture. As bibliotecas utilizadas na construção do app foram:

Bibliotecas    |  Função
---------------|---------
Retrofit       | Comunicação com a API
Koin           | Injeção de dependências
Glide          | Tratamento das imagens <br> recebidas da api
Google Maps SDK| Para tratar a geolocalização <br> recebida da api

Para utilizar o app, basta clonar o repositório e executá-lo pela _branch master_. Algumas imagens podem não ser retornadas <br>
e deve ser algo relacionado a biblioteca ou a api. Pretendo continuar mexendo nesse projeto para aprimorar ainda mais (de acordo com)<br>
a evolução do meu conhecimento durante os estudos. (apesar da entrega já ter sido realizada).

# As telas presentes no app são: 

## A tela principal;
![mainScreen](https://user-images.githubusercontent.com/31328575/188358243-3f70483c-2679-4834-8804-e79421c1a206.png)


## A tela de detalhes do evento selecionado;
![eventDetailed](https://user-images.githubusercontent.com/31328575/188358300-8373d235-f7dd-4e4f-acb0-5d2f456641e6.png)


## A tela para realizar o check-in;
![checkinScreen](https://user-images.githubusercontent.com/31328575/188358352-503ec2dc-26c4-44ac-9cf2-f15f00957895.png)


## A tela de opções de compartilhamento (padrão do android);
![shareScreen](https://user-images.githubusercontent.com/31328575/188358418-404f517c-f43b-4a5b-b3b4-7a8b892dd0db.png)


## A tela de localização é o próprio app do Google Maps;
![mapsScreen](https://user-images.githubusercontent.com/31328575/188358486-a65079da-7f85-45c4-8a69-4371d345e73a.png)
