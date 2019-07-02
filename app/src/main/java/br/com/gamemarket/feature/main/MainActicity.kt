package br.com.gamemarket.feature.main

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.isVisible
import br.com.gamemarket.base.extensions.showToast
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.feature.cart.CartActivity
import br.com.gamemarket.feature.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainContract.View {
    override val presenter by inject<MainContract.Presenter> { parametersOf(this) }

    private val adapter by lazy { GameAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViews()
        presenter.loadGames()
    }

    override fun onResume() {
        super.onResume()

        presenter.loadCart()
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onSuccessfulLoadGames(games: List<Game>) {
        adapter.data = games
    }

    override fun showLoadingGames() {
        mainViewLoading.isVisible = true
    }

    override fun hideLoadingGames() {
        mainViewLoading.isVisible = false
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
        val quantity = cart.sumBy { it.quantity }
        mainToolbar.tcTxtCartCount.isVisible = quantity > 0
        mainToolbar.tcTxtCartCount.text = quantity.toString()
    }

    override fun onFailuereLoadGames(message: String) {
        showToast(message)
    }

    private fun setupViews() {
        setSupportActionBar(mainToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mainToolbar.tcTxtTitle.setText(R.string.main_title)
        mainToolbar.tcImgCart.setOnClickListener {
            CartActivity.startCartActivity(this)
        }

        mainRecGames.layoutManager = GridLayoutManager(this, 2)
        mainRecGames.adapter = adapter

        adapter.setOnItemClickListener { game ->
            GameActivity.startGameActivity(this, game.id)
        }
    }
}
