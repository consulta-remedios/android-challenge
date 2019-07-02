package br.com.gamemarket.feature.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gamemarket.R
import br.com.gamemarket.data.model.ItemCart
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CartActivity: AppCompatActivity(), CartContract.View {
    override val presenter by inject<CartContract.Presenter> { parametersOf(this) }

    private val adapter by lazy { CartAdapter() }

    companion object {
        fun startCartActivity(context: Context) {
            val intent = Intent(context, CartActivity::class.java)
            context.startActivity(intent)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        setupViews()
        presenter.loadCart()
    }

    override fun onSuccessfulLoadGame(items: List<ItemCart>) {
        adapter.data = items
    }

    override fun onPurchase(cart: List<ItemCart>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
      }

    private fun setupViews() {
        setSupportActionBar(cartToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cartToolbar.tcTxtTitle.setText(R.string.toolbar_cart_title)
        cartToolbar.tcImgCart.visibility = View.GONE
        cartToolbar.tcImgSearch.visibility = View.GONE

        cartList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        cartList.adapter = adapter
    }
}