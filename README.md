# Sistema de Restaurante

Este projeto simula o funcionamento de um restaurante pelo terminal.

## O que o sistema faz

O sistema permite:

- Criar pedidos.
- Adicionar lanches ao pedido.
- Escolher lanches com ou sem ingredientes.
- Adicionar cheddar e bacon extra.
- Adicionar ou remover itens de um pedido.
- Cancelar um pedido.
- Ver os pedidos do dia.
- Ver o total vendido.
- Ver as combinações de lanches mais pedidos.

## Cardápio

| Lanche | Preço |
|---|---:|
| X-Burger | R$ 16,00 |
| X-Salada | R$ 18,00 |
| X-Bacon | R$ 22,00 |
| X-Especial | R$ 25,00 |
| X-Frango | R$ 20,00 |
| X-Egg | R$ 19,00 |
| Pastel | R$ 10,00 |

Também é possível adicionar os seguintes adicionais:

| Adicional | Preço |
|---|---:|
| Cheddar | R$ 4,00 |
| Bacon extra | R$ 6,00 |

## Como executar

O projeto precisa do Java 17 e do Maven.

Para compilar e executar o programa, use este comando na pasta do projeto:

```bash
mvn compile exec:java
```

Para gerar o arquivo executável:

```bash
mvn clean package
```

Depois, execute o arquivo gerado:

```bash
java -jar target/sistema-restaurante-1.0.0.jar
```

## Como usar

Ao iniciar o programa, o menu principal mostra as opções disponíveis.

Para registrar um pedido:

1. Escolha a opção `1`.
2. Digite o nome do cliente.
3. Escolha um lanche do cardápio.
4. Responda se deseja manter cada ingrediente.
5. Responda se deseja adicionar cheddar ou bacon extra.
6. Repita o processo para adicionar mais itens, se quiser.
7. Ao responder que não deseja adicionar outro item, o pedido será registrado.

A opção `2` mostra os pedidos do dia.

A opção `3` permite buscar um pedido pelo número. Nela, é possível adicionar itens, remover itens ou cancelar o pedido inteiro.

A opção `4` mostra o total vendido, a quantidade de combinações diferentes e quais são essas combinações.

Para encerrar o programa, escolha a opção `0`.

## Organização do projeto

Os pacotes foram separados para deixar cada parte do sistema com uma função clara.

- `app`: contém o programa principal, o menu do terminal e a fachada que organiza as operações.
- `cardapio`: contém os lanches, os adicionais, os preços e a factory que cria os lanches do cardápio.
- `pedido`: contém o pedido e o gerenciador que guarda todos os pedidos do restaurante.
- `financeiro`: está reservado para uma possível implementação de pagamentos e gorjetas.
- `util`: está reservado para validações e funções de apoio.

## Padrões de projeto

### Factory

A classe `LancheFactory` cria os lanches do cardápio. Cada chamada cria um lanche novo. Isso evita que a personalização de um pedido altere os lanches de outro pedido.

### Facade

A classe `RestauranteFacade` reúne as operações do restaurante e serve como ponto de entrada para o menu. O menu não precisa conhecer todos os detalhes do funcionamento interno.

### Decorator

Os adicionais `ComCheddar` e `ComBaconExtra` envolveem um lanche e acrescentam uma descrição e um preço. O lanche original não precisa ser alterado para receber um adicional.

## Testes

O projeto possui cinco testes automatizados com JUnit. Eles verificam:

- Se a soma de dois e dois é quatro.
- Se um lanche com preço negativo é recusado.
- Se o preço de um lanche com cheddar está correto.
- Se a descrição e o preço de um lanche com cheddar e bacon extra estão corretos.
- Se os novos itens do cardápio são criados com o nome e o preço corretos.

Para executar os testes, use:

```bash
mvn test
```

## Vídeo

Link do vídeo: https://youtu.be/dBQs1M3tmpI

## Bônus

Os bônus da atividade não foram implementados.
