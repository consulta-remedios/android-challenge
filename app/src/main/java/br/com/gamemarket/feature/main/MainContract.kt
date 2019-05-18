package br.com.gamemarket.feature.main

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showLoadingGames()
        fun hideLoadingGames()
        fun onSuccessfulLoadGames(games: List<Game>)
        fun onFailuereLoadGames(message: String)
        fun onChangeCartSize(cart: List<ItemCart>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadGames()
        fun loadCart()
        fun addItemCard(item: Game)
        fun removeItemCard(item: Game)
    }
}