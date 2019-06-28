package br.com.gamemarket.feature.game

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import br.com.gamemarket.R
import br.com.gamemarket.base.extensions.extra
import br.com.gamemarket.base.extensions.isVisible
import br.com.gamemarket.base.extensions.loadImage
import br.com.gamemarket.base.extensions.toCurrency
import br.com.gamemarket.data.model.Game
import kotlinx.android.synthetic.main.activity_game.*
import kotlinx.android.synthetic.main.card_bottom.view.*
import kotlinx.android.synthetic.main.item_game.*
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

    private fun setupViews() {
        setSupportActionBar(gameToolbar as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        gameToolbar.tcTxtTitle.setText(R.string.game_title)
    }

    override fun onSuccessfulLoadGame(game: Game) {
        gameToolbar.tcTxtTitle.text = game.platform.toUpperCase()

//        gameTxtName.text = game.name
        gameBottomView.gamePrice.text = getString(R.string.item_game_price, game.price.toString())
        detailImgCover.loadImage(game.image)
//        gameTxtDescription.text = game.description
//        gameTxtPrice.text = game.price.toCurrency()
    }

    override fun showLoadingGames() {
        gameViewLoading.isVisible = true
    }

    override fun hideLoadingGames() {
        gameViewLoading.isVisible = false
    }

}