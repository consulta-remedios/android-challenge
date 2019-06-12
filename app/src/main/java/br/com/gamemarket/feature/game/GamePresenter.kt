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
    private val dispatcherContext: CoroutineDispatcher
) : GameContract.Presenter {

    override fun loadGames(gameId: Long) {
        view.showLoadingGames()

        dispatcherContext.launch {
            gameRepository.getGame(gameId).whenever(
                isBody = { gameDto ->
                    view.hideLoadingGames()
                    view.onSuccessfulLoadGame(gameDto.toGame())
                },
                isError = {message ->
                    view.hideLoadingGames()
                    view.onUnsuccessfulLoadGame(message)
                }
            )
        }
    }

    private fun GameDto.toGame(): Game {
        return Game(
            id,
            name,
            description,
            price,
            0,
            platform,
            image,
            emptyList()
        )
    }
}