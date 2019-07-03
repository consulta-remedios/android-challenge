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
import br.com.gamemarket.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_cart.*
import kotlinx.android.synthetic.main.cart_card_bottom.view.*
import kotlinx.android.synthetic.main.customer_info.view.*
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

    override fun onSuccessfulLoadGame(items: List<ItemCart>, presenter: CartPresenter) {
        adapter.data = items
        setPrices(items)
    }

    override fun onPurchase(cart: List<ItemCart>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onChangeCartSize() {
        presenter.loadCart()
      }

    private fun setupViews() {
        setSupportActionBar(cartToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cartToolbar.tcTxtTitle.setText(R.string.toolbar_cart_title)
        cartToolbar.tcImgCart.visibility = View.GONE
        cartToolbar.tcImgSearch.visibility = View.GONE

        cartBottomView.continuePurchase.setOnClickListener {
            MainActivity.startMainActivity(this)
        }

        cartList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        cartList.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun setPrices(items: List<ItemCart>) {
        var totalPrice = 0.0
        items.map { itemCart -> totalPrice += (itemCart.price * itemCart.quantity) }
        val totalPriceFormatter:Double = Math.round(totalPrice * 1000.0) / 1000.0
        cartBottomView.cartPrice.text = getString(R.string.item_price, totalPriceFormatter.toString())
        if (totalPrice < 250) {
            var totalAmount = 0
            items.map { itemCart -> totalAmount = (itemCart.quantity + totalAmount) }
            customerInformation.customerFreightPrice.text = getString(R.string.item_price, (totalAmount * 10).toString())
            return
        }
        customerInformation.customerFreightPrice.text = getString(R.string.free_label)
    }
}