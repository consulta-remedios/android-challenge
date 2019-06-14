package br.com.gamemarket.base.extensions

import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart


fun Game.toItemCart(): ItemCart {
    return ItemCart(
        id,
        name,
        price,
        platform,
        image,
        1
    )
}


fun ItemCart.toGame(): Game {
    return Game(
        id = id,
        name = name,
        description = "",
        price = price,
        score = 1,
        platform = platform,
        image = image,
        images = emptyList()
    )
}