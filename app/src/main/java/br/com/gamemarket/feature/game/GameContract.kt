package br.com.gamemarket.feature.game

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView
import br.com.gamemarket.data.model.Game

interface GameContract {
    interface View : BaseView<Presenter> {
        fun showLoadingGames()
        fun hideLoadingGames()
        fun onSuccessfulLoadGame(game: Game)
        fun onUnsuccessfulLoadGame(message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun loadGames(gameId: Long)
    }
}