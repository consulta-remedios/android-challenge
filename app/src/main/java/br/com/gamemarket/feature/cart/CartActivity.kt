package br.com.gamemarket.feature.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.*
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.feature.endbuy.EndBuyActivity
import br.com.gamemarket.feature.main.MainActivity
import kotlinx.android.synthetic.main.activity_cart_content.*
import kotlinx.android.synthetic.main.bottom_sheet_cart.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class CartActivity : AppCompatActivity(), CartContract.View {

    override val presenter by inject<CartContract.Presenter> { parametersOf(this) }
    private val adapter by lazy { CartAdapter() }
    private var mCart: MutableList<ItemCart> = mutableListOf()

    companion object {
        fun startGameActivity(context: Context) {
            val intent = Intent(context, CartActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        if (savedInstanceState != null) {
            mCart = savedInstanceState.getParcelableArrayList("game_list_state")
            adapter.data = mCart
        } else {
            presenter.loadGames()
        }

        setupToolbar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("game_list_cart", ArrayList(mCart))
        super.onSaveInstanceState(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        tvContinueBuing.setOnClickListener{
            MainActivity.startGameActivity(this)
        }

        tvEndBuing.setOnClickListener{
            presenter.removeAll()
            EndBuyActivity.startGameActivity(this)
            finish()
        }

        setupRecyclerView()
        setupDataToPayment()
        setupDataToShipping()
        setupDataToAddress()
    }

    private fun setupToolbar() {
        setSupportActionBar(cartToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        cartToolbar.tcTxtTitle.text = resources.getString(R.string.title_cart_activity)
        cartToolbar.tcTxtTitle.setTextColor(resources.getColor(R.color.colorAccent))

        cartToolbar.tcImgCart.visibility = View.GONE
        cartToolbar.tcImgSearch.visibility = View.GONE
    }

    private fun setupDataToPayment() {
        gameTxtPrice.text = mCart.map { (it.price * it.quantity) }.sum().toCurrency()

        tvCardNumber.text = "5555111122223333".hideCardNumber()
        tvCardFlag.text = "Visa"
        tvEditPayment.setOnClickListener {
            //TODO: Change payment form
        }
    }

    private fun setupDataToShipping() {
        val total = mCart.map { (it.price * it.quantity) }.sum()
        val shipping = mCart.size * 10.0
        if (total > 250) {
            tvPriceShipping.text = resources.getString(R.string.label_free_shipping)
        } else {
            tvPriceShipping.text = shipping.toCurrency()
        }

    }

    private fun setupDataToAddress() {
        tvAddress.text = "5555-000\nRua Teste, 300\nSanta Rita - MG - Brasil"
        tvEditAddress.setOnClickListener {
            //TODO: Change address
        }
    }

    private fun setupRecyclerView() {
        val llManager = LinearLayoutManager(this)

        cartRecGames.layoutManager = llManager
        cartRecGames.adapter = adapter

        val touchHelper = ItemTouchHelper(SwipeCallback(adapter, this))
        touchHelper.attachToRecyclerView(cartRecGames)

        adapter.setOnChangeListener { game ->
            onRemoveUnityItemCart(game)
            if(mCart.size < 1){
                finish()
                showToast(resources.getString(R.string.message_empty_cart))
            }
        }
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
        mCart = cart as MutableList<ItemCart>
        adapter.data = mCart
        setupViews()
    }

    private fun onIncreaseQtdGame(item: ItemCart) {
        //TODO: Implement the button that will increase the number of the one item
    }

    private fun onDecreaseQtdGame(item: ItemCart) {
        //TODO: Implement the button that will decrease the number of the one item
    }

    private fun onRemoveUnityItemCart(item: ItemCart) {
        presenter.removeItemCard(item.toGame())
        showToast("O jogo ${item.name} foi removido do carrinho")
    }

}
