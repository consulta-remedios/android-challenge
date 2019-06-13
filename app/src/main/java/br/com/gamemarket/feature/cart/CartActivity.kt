package br.com.gamemarket.feature.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.data.model.ItemCart
import kotlinx.android.synthetic.main.activity_cart_content.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CartActivity : AppCompatActivity(), CartContract.View {

    override val presenter by inject<CartContract.Presenter> { parametersOf(this) }
    private var mCart = mutableListOf<ItemCart>()

    companion object {
        fun startGameActivity(context: Context) {
            val intent = Intent(context, CartActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setupViews()
    }

    private fun setupViews() {
        setSupportActionBar(cartToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cartToolbar.tcTxtTitle.text = resources.getString(R.string.title_cart_activity)

        cartToolbar.tcImgCart.visibility = View.GONE
        cartToolbar.tcImgSearch.visibility = View.GONE
    }

    override fun showLoadingGames() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoadingGames() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSuccessfulLoadGame() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
        mCart = cart as MutableList<ItemCart>
    }

}
