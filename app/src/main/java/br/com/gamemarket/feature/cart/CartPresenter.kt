package br.com.gamemarket.feature.cart

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.data.model.whenever
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import kotlinx.coroutines.CoroutineDispatcher

class CartPresenter(
    override var view: CartContract.View,
    private val localRepository: CartRepository,
    private val gameRepository: GameRepository,
    private val dispatcherContext: CoroutineDispatcher
) : CartContract.Presenter {

    override fun loadCart() {
        dispatcherContext.launch {
            localRepository.getCart().whenever(
                isBody = {
                    view.onSuccessfulLoadGame(it.toItems())
                }
            )
        }
    }

    override fun purchaseItems() {
        dispatcherContext.launch {
            gameRepository.finishPurchase().whenever(
                isBody = {
                    view.onFinishPurchase()
                    cleanCart()
                },
                isError = {
                    view.onFailuereLoadGames("Falha ao finalizar a compra.")
                }
            )
        }
    }

    override fun cleanCart() {
        dispatcherContext.launch {
            localRepository.removeAll()
        }
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

