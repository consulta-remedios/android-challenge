package br.com.gamemarket.feature.game

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart

interface GameContract {
    interface View : BaseView<Presenter> {
        fun showLoadingGames()
        fun hideLoadingGames()
        fun onSuccessfulLoadGame(game: Game)
        fun onUnsuccessfulLoadGame(message: String)
        fun onChangeCartSize(cart: List<ItemCart>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadGames(gameId: Long)
        fun addItemCard(item: Game)
        fun removeItemCard(item: Game)
    }
}