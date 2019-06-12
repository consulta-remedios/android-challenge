package br.com.gamemarket.feature.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.extra
import br.com.gamemarket.base.extensions.isVisible
import br.com.gamemarket.base.extensions.toCurrency
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import kotlinx.android.synthetic.main.activity_game_content.*
import kotlinx.android.synthetic.main.bottom_sheet_detail_game.*
import kotlinx.android.synthetic.main.toolbar_cart.view.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class GameActivity : AppCompatActivity(), GameContract.View {

    override val presenter by inject<GameContract.Presenter> { parametersOf(this) }

    private val gameId by extra(EXTRA_GAME_ID, 0L)
    private lateinit var mGame: Game

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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> super.onOptionsItemSelected(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupViews() {
        setSupportActionBar(gameToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gameToolbar.tcTxtTitle.text = Html.fromHtml(getString(R.string.main_title))

        tvReadMoreLess.setOnClickListener {
            if (gameTxtDescription.maxLines == 6) {
                showDescriptionText()
            } else {
                hideDescriptionText()
            }
        }

        tvAddCart.setOnClickListener{
            onAddUnityItemCart()
        }
    }

    override fun onSuccessfulLoadGame(game: Game) {
        mGame = game

        gameToolbar.tcTxtTitle.text = game.platform.toUpperCase()
        gameToolbar.tcTxtTitle.setTextColor(resources.getColor(R.color.colorAccent))

        bsContainerGame.visibility = View.VISIBLE
        shadow.visibility = View.VISIBLE

        gameTxtName.text = game.name
        gameTxtDescription.text = game.description
        gameTxtPrice.text = game.price.toCurrency()
    }

    override fun onUnsuccessfulLoadGame(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onChangeCartSize(cart: List<ItemCart>) {
        val quantity = cart.sumBy { it.quantity }
        gameToolbar.tcTxtCartCount.isVisible = quantity > 0
        gameToolbar.tcTxtCartCount.text = quantity.toString()
    }

    override fun showLoadingGames() {
        gameViewLoading.isVisible = true
    }

    override fun hideLoadingGames() {
        gameViewLoading.isVisible = false
    }

    private fun hideDescriptionText() {
        tvReadMoreLess.text = resources.getString(R.string.label_read_more)
        gameTxtDescription.maxLines = 6
        gameTxtDescription.movementMethod = null
    }

    private fun showDescriptionText() {
        tvReadMoreLess.text = resources.getString(R.string.label_read_less)
        gameTxtDescription.maxLines = Integer.MAX_VALUE
        gameTxtDescription.movementMethod = ScrollingMovementMethod()
    }

    private fun onRemoveUnityItemCart(item: Game) {
        presenter.removeItemCard(item)
        Toast.makeText(this, "O jogo ${mGame.name} foi removido do carrinho",
            Toast.LENGTH_SHORT).show()
    }

    private fun onAddUnityItemCart() {
        presenter.addItemCard(mGame)
        Toast.makeText(this, "O jogo ${mGame.name} foi adicionado no carrinho",
            Toast.LENGTH_SHORT).show()
    }

}