package br.com.gamemarket.feature.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.extra
import br.com.gamemarket.base.extensions.isVisible
import br.com.gamemarket.base.extensions.loadImage
import br.com.gamemarket.base.extensions.showToast
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.feature.cart.CartActivity
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.game_card_bottom.view.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class GameActivity : AppCompatActivity(), GameContract.View {
    override val presenter by inject<GameContract.Presenter> { parametersOf(this) }

    private val gameId by extra(EXTRA_GAME_ID, 0L)

    companion object {
        private const val EXTRA_GAME_ID = "game"

        fun startGameActivity(context: Context, gameId: Long) {
            val intent = Intent(context, GameActivity::class.java)

            intent.putExtra(EXTRA_GAME_ID, gameId)

            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        setupViews()
        presenter.loadGames(gameId)
    }

    override fun onResume() {
        super.onResume()

        presenter.loadCart()
    }

    private fun setupViews() {
        setSupportActionBar(gameToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gameToolbar.tcTxtTitle.setText(R.string.game_title)
        gameToolbar.tcImgCart.setOnClickListener {
            CartActivity.startCartActivity(this)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onSuccessfulLoadGame(game: Game) {
        gameToolbar.tcTxtTitle.text = game.platform.toUpperCase()

        gameBottomView.gamePrice.text = getString(R.string.item_price, game.price.toString())
        detailImgCover.loadImage(game.image)
        detailGameTitle.text = game.name
        detailGameDescription.text = game.description

        detailReadMore.setOnClickListener {
            detailGameDescription.maxLines = 30
            detailReadMore.visibility = View.GONE
        }

        gameBottomView.gameAddToCart.setOnClickListener {
            onAddUnityItemCart(game)
        }

        detailReadMore.visibility = View.VISIBLE
        gameBottomView.gamePrice.visibility = View.VISIBLE
        gameBottomView.gameFreightPrice.visibility = View.VISIBLE
        gameBottomView.gameFreightTitle.visibility = View.VISIBLE
    }

    override fun showLoadingGames() {
        gameViewLoading.isVisible = true
    }

    override fun hideLoadingGames() {
        gameViewLoading.isVisible = false
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
        val quantity = cart.sumBy { it.quantity }
        gameToolbar.tcTxtCartCount.isVisible = quantity > 0
        gameToolbar.tcTxtCartCount.text = quantity.toString()
    }

    override fun onFailuereLoadGames(message: String) {
        showToast(message)
    }

    private fun onAddUnityItemCart(item: Game) {
        presenter.addItemCard(item)
    }

}