package br.com.gamemarket.feature.cart

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart

interface CartContract {
    interface View : BaseView<Presenter> {
        fun onChangeCartSize(cart: List<ItemCart>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadGames()
        fun increaseQuantityGames(game: Game)
        fun decreaseQuantityGames(game: Game)
        fun removeItemCard(item: Game)
        fun removeAll()
    }
}