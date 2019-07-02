package br.com.gamemarket.feature.cart

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.data.model.whenever
import kotlinx.coroutines.CoroutineDispatcher

class CartPresenter(
    override var view: CartContract.View,
    private val localRepository: CartRepository,
    private val dispatcherContext: CoroutineDispatcher
) : CartContract.Presenter {

    override fun loadCart() {
        dispatcherContext.launch {
            localRepository.getCart().whenever(
                isBody = { itemCart ->
                    view.onSuccessfulLoadGame(itemCart.toItems())
                }
            )
        }
    }

    override fun addItemCard(item: Game) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun List<ItemCart>.toItems(): List<ItemCart> {
        return this.map { it.toItem() }
    }

    private fun ItemCart.toItem(): ItemCart {
        return ItemCart(
            id,
            name,
            price,
            platform,
            image,
            quantity
        )
    }

}

