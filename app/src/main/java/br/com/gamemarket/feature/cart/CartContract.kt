package br.com.gamemarket.feature.cart

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart

interface CartContract {
    interface View : BaseView<Presenter> {
        fun showLoadingGames()
        fun hideLoadingGames()
        fun onSuccessfulLoadGame()
        fun onChangeCartSize(cart: List<ItemCart>)
    }

    interface Presenter : BasePresenter<View> {
        fun loadGames()
        fun incriaseQuantityGames()
        fun decreaseQuantityGames()
        fun removeItemCard(item: Game)
    }
}