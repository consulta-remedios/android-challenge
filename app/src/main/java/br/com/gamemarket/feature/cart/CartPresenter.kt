package br.com.gamemarket.feature.cart

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.whenever
import kotlinx.coroutines.CoroutineDispatcher

class CartPresenter(
    override var view: CartContract.View,
    private val localRepository: CartRepository,
    private val dispatcherContext: CoroutineDispatcher
)  : CartContract.Presenter {

    override fun loadGames() {
        dispatcherContext.launch {
            refreshCartItemCount()
        }
    }

    override fun increaseQuantityGames(game: Game) {
        dispatcherContext.launch {
            localRepository.addItem(game)
            refreshCartItemCount()
        }
    }

    override fun decreaseQuantityGames(game: Game) {
        dispatcherContext.launch {
            localRepository.removeItem(game)
            refreshCartItemCount()
        }
    }

    override fun removeItemCard(game: Game) {
        dispatcherContext.launch {
            localRepository.remove(game)
            refreshCartItemCount()
        }
    }

    override fun removeAll() {
        dispatcherContext.launch {
            localRepository.removeAll()
            refreshCartItemCount()
        }
    }

    private suspend fun refreshCartItemCount() {
        localRepository.getCart().whenever(
            isBody = { cart ->
                view.onChangeCartSize(cart)
            }
        )
    }
}