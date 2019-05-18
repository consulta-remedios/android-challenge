package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.data.model.ServiceResponse

private val cart = mutableListOf<ItemCart>()

class CartStaticRepository : CartRepository {
    override suspend fun getCart(): ServiceResponse<List<ItemCart>> {
        return ServiceResponse.BODY(cart)
    }

    override suspend fun addItem(game: Game): ServiceResponse<*> {

        val itemCard = cart.getItemIfExists(game.id)

        if (itemCard == null) {
            cart.add(game.toItemCart())
        } else {
            itemCard.quantity++
        }

        return ServiceResponse.OK
    }

    override suspend fun removeItem(id: Long): ServiceResponse<*> {
        return try {
            val itemCard = cart.getItemIfExists(id)
            itemCard?.quantity = (itemCard?.quantity ?: 1) - 1

            cart.removeAll { it.quantity < 1 }

            ServiceResponse.OK
        } catch (e: Exception) {
            ServiceResponse.ERROR("")
        }
    }

    override suspend fun removeItem(game: Game): ServiceResponse<*> {
        return removeItem(game.id)
    }

    private fun List<ItemCart>.getItemIfExists(id: Long): ItemCart? {
        return this.firstOrNull { it.id == id }
    }
}

private fun Game.toItemCart(): ItemCart {
    return ItemCart(
        id,
        name,
        price,
        platform,
        image,
        1
    )
}
