package br.com.gamemarket.feature.cart

import androidx.appcompat.app.AppCompatActivity
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CartActivity: AppCompatActivity(), CartContract.View {
    override fun onSuccessfulLoadGame(game: Game) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPurchase(cart: List<ItemCart>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val presenter by inject<CartContract.Presenter> { parametersOf(this) }
}