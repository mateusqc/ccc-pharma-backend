# CCC PHARMA - BACK-END

Link para o Front-end: https://github.com/vitoriaHeliane/ccc-pharma

## Descrição

Uma nova farmácia vai ser aberta em Campina Grande e precisa de um sistema que gerencie o estoque de produtos vendidos. Neste sistema, o administrador deve obter uma visão geral e o controle sobre o funcionamento da farmácia, por exemplo, ele deve poder adicionar novos produtos, acompanhar quantas unidades do produto estão disponíveis, alterar preços, ser notificado sobre eventos críticos, gerenciar as vendas e oferecer alguns serviços personalizados para o cliente.

## Funcionalidades Implementadas

### Obrigatórias
- Eu, como administrador, gostaria de ter o sistema armazenando todos os seus dados de forma persistente em um banco de dados. 
- Eu, como usuário, gostaria de acessar o sistema através de um link na web, preferencialmente usando o Heroku.
- Eu, como administrador, gostaria de logar no sistema, para ter acesso às funcionalidades destinadas ao administrador.
- Eu, como cliente, gostaria de realizar o cadastro no sistema, para poder obter acesso ao sistema.
- Eu, como cliente, gostaria de logar no sistema, para poder ter acesso às funcionalidades destinadas ao cliente.

1) Eu, como administrador, gostaria de adicionar um novo produto no sistema, informando seu nome, código de barra, fabricante, situação e categoria. As categorias disponíveis na farmácia são: medicamentos, produtos de higiene pessoal, cosméticos e alimentos.
2) Eu, como administrador, gostaria de consultar a disponibilidade e o preço de cada produto da farmácia.
3) Eu, como administrador, gostaria de atribuir um preço (R$) a um determinado produto no sistema.
4) Eu, como administrador, gostaria de criar lotes associados aos produtos, informando a quantidade de itens disponíveis e a data de validade.
5) Eu, como cliente, gostaria de consultar a disponibilidade e o preço de cada produto da farmácia (não precisa estar logado).
7) Eu, como administrador, gostaria de atribuir descontos para cada categoria de produto, dessa forma, o cliente recebe um abatimento no valor final da compra. Tipos de desconto: sem desconto (0%), bom desconto (10%), ótimo desconto (25%) e super desconto (50%).
9) Eu, como administrador, gostaria que um produto fosse marcado como indisponível quando a quantidade de itens dele for igual a zero. Dessa forma, o seu preço não pode ser mais exibido para os clientes e ele deve ser adicionado na lista de produtos em falta.
10) Eu, como administrador, gostaria que um produto fosse marcado como indisponível quando todos os lotes do produto ultrapassarem a data de validade. Dessa forma, o seu preço não pode ser mais exibido para os clientes e ele deve ser adicionado na lista de produtos vencidos.
11) Eu, como administrador, gostaria de registrar uma venda, informando os produtos, a quantidade de itens e registrando o valor total da venda (descontos devem ser considerados). O número de itens do produto deve ser decrementado.
13) Eu, como administrador, gostaria de receber notificações na tela inicial do sistema, avisando quando a quantidade de itens de um produto está baixa (abaixo de 15 unidades).
14) Eu, como administrador, gostaria de receber notificações na tela inicial do sistema, avisando quando a validade de um lote está próxima ao vencimento (um mês de antecedência).
