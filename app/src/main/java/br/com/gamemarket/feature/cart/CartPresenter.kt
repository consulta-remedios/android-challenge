package br.com.gamemarket.feature.cart

import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import kotlinx.coroutines.CoroutineDispatcher

class CartPresenter(
    override var view: CartContract.View,
    private val gameRepository: GameRepository,
    private val localRepository: CartRepository,
    private val dispatcherContext: CoroutineDispatcher
)  : CartContract.Presenter {

    override fun loadGames() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun incriaseQuantityGames() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun decreaseQuantityGames() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeItemCard(item: Game) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}