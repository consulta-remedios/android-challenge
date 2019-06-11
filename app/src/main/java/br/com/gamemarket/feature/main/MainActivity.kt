package br.com.gamemarket.feature.main

import android.os.Bundle
import android.os.PersistableBundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.isVisible
import br.com.gamemarket.base.extensions.showToast
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.feature.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class MainActivity : AppCompatActivity(), MainContract.View {
    override val presenter by inject<MainContract.Presenter> { parametersOf(this) }

    private val adapter by lazy { GameAdapter() }

    private var mGames: List<Game> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            mGames = savedInstanceState.getParcelableArrayList("game_list_state")
            adapter.data = mGames
            hideLoadingGames()
        } else {
            presenter.loadGames()
        }

        setupViews()
    }

    override fun onResume() {
        super.onResume()
        presenter.loadCart()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("game_list_state", ArrayList(mGames))
        super.onSaveInstanceState(outState)
    }

    override fun onSuccessfulLoadGames(games: List<Game>) {
        mGames = games
        adapter.data = mGames
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
        mainToolbar.tcTxtTitle.text = Html.fromHtml(getString(R.string.main_title))

        val staggeredGridLayoutManager = StaggeredGridLayoutManager(2,
            LinearLayoutManager.VERTICAL)

        mainRecGames.layoutManager = staggeredGridLayoutManager
        mainRecGames.adapter = adapter

        adapter.setOnMinusClickListener {
            onRemoveUnityItemCart(it)
        }

        adapter.setOnPlusClickListener {
            onAddUnityItemCart(it)
        }

        adapter.setOnItemClickListener { game ->
            GameActivity.startGameActivity(this, game.id)
        }
    }

    private fun onRemoveUnityItemCart(item: Game) {
        presenter.removeItemCard(item)
    }

    private fun onAddUnityItemCart(item: Game) {
        presenter.addItemCard(item)
    }
}
