package br.com.gamemarket.feature.game

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.whenever
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import br.com.gamemarket.data.model.GameDto
import kotlinx.coroutines.CoroutineDispatcher

class GamePresenter(
    override var view: GameContract.View,
    private val gameRepository: GameRepository,
    private val localRepository: CartRepository,
    private val dispatcherContext: CoroutineDispatcher
) : GameContract.Presenter {

    override fun loadGames(gameId: Long) {
        view.showLoadingGames()

        dispatcherContext.launch {
            refreshCartItemCount()
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

    override fun addItemCard(item: Game) {
        dispatcherContext.launch {
            localRepository.addItem(item)
            refreshCartItemCount()
        }
    }

    override fun removeItemCard(item: Game) {
        dispatcherContext.launch {
            localRepository.removeItem(item)
            refreshCartItemCount()
        }
    }

    private suspend fun refreshCartItemCount() {
        localRepository.getCart().whenever(
            isBody = { cart ->
                view.onChangeCartSize(cart)
            }
        )
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