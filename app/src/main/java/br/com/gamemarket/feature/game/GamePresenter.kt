package br.com.gamemarket.feature.game

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.dto.GameDto
import br.com.gamemarket.data.model.whenever
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import kotlinx.coroutines.CoroutineDispatcher

class GamePresenter(
    override var view: GameContract.View,
    private val gameRepository: GameRepository,
    private val localRepository: CartRepository,
    private val dispacherContext: CoroutineDispatcher
) : GameContract.Presenter {

    override fun loadGames(gameId: Long) {
        view.showLoadingGames()

        dispacherContext.launch {
            gameRepository.getGame(gameId).whenever(
                isBody = { gameDto ->
                    view.hideLoadingGames()
                    view.onSuccessfulLoadGame(gameDto.toGame())
                },
                isError = {
                    view.hideLoadingGames()
                    // TODO implements
                }
            )
        }
    }

    override fun loadCart() {
        dispacherContext.launch {
            refreshCartItemCount()
        }
    }

    override fun addItemCard(item: Game) {
        dispacherContext.launch {
            localRepository.addItem(item)
            refreshCartItemCount()
        }
    }

    override fun removeItemCard(item: Game) {
        dispacherContext.launch {
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


    private fun GameDto.toGame() : Game {
        return Game(
            id,
            name,
            description,
            price,
            score,
            platform,
            image,
            images
        )
    }
}