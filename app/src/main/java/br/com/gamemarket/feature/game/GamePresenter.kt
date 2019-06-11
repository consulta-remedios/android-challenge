package br.com.gamemarket.feature.game

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.whenever
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import br.com.gamemarket.data.model.GameDto
import kotlinx.coroutines.CoroutineDispatcher

class GamePresenter(
    override var view: GameContract.View,
    private val gameRepository: GameRepository,
    private val dispacherContext: CoroutineDispatcher
) : GameContract.Presenter {

    override fun loadGames(gameId: Long) {
        view.showLoadingGames()

        dispacherContext.launch {
            gameRepository.getGame(gameId).whenever(
                isBody = { gameDto ->
                    view.hideLoadingGames()
                    view.onSuccessfulLoadGame(gameDto.toMockGame())
                },
                isError = {message ->
                    view.hideLoadingGames()
                    view.onUnsuccessfulLoadGame(message)
                }
            )
        }
    }

    // TODO implements
    private fun GameDto.toMockGame() : Game {
        return Game(
            id,
            "Super Mario Odyssey",
            "Lorem ipsum dolor sit amet, Super Mario Odyssey, consectetur adipiscing elit",
            100.0,
            1,
            "ps4",
            "https://raw.githubusercontent.com/ConsultaRemedios/frontend-challenge/master/assets/super-mario-odyssey.png",
            emptyList()
        )
    }
}