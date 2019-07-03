Arquitetura:
    -Projeto foi utilizado arquitetura MVP como já havia sido proposta, ao imaginar que não traria ganhos significativos resolvi manter ela

Features:
    -Lista de jogos:
        * App lista todos jogos vindo da api com alguns detalhes
        * Ao clicar em um jogo redireciona o usuário para tela de detalhes
        * Ícone de carrinho no toolbar indica quantos itens adicionados no carrinho
        * Ao clicar no ícone de carrinho redireciona para tela do carrinho de compras

    -Detalhe do jogo:
        * Permite adicionar jogo no carrinho ao clicar no botão de adicionar
        * Permite ler um descrição resumida do jogo e quando clicado em "Ler mais" abre a descrição total em um scroll
        * Ícone de carrinho no toolbar indica quantos itens adicionados no carrinho
        * Ao clicar no ícone de carrinho redireciona para tela do carrinho de compras

    -Carrinho de compras:
        * Lista jogos adicionados no carrinho em uma lista com scroll horizontal
        * Exibe informações do usuário
        * Exibe preço do frete(10 reais por jogo ou grátis se acima de 250 reais)
        * Spinner exibe quantidade adicionada do jogo

Bibliotecas:
    -Me mantive com as bibliotecas já disponibilizadas para não adicionar mais complexidade no aplicativo sem necessidade

Pendências v1.1.0:
    -Carrousel de imagens na tela de detalhes
    -Ao mudar quantidade na tela do carrinho responder com mudança do preço, itens no carrinho e frete
    -Remover item do carrinho
    -Filtro de pesquisa na listagem de jogos
    -Label "quantidade" em spinner de quantidade
