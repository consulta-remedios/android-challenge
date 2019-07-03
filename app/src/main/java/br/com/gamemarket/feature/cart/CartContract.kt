package br.com.gamemarket.feature.cart

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView
import br.com.gamemarket.data.model.ItemCart

interface CartContract {
    interface View : BaseView<Presenter> {
        fun onSuccessfulLoadGame(items: List<ItemCart>)
        fun onFinishPurchase()
        fun onChangeCartSize()
        fun onFailuereLoadGames(msg: String)
    }

    interface Presenter : BasePresenter<View> {
        fun loadCart()
        fun purchaseItems()
        fun cleanCart()
    }
}