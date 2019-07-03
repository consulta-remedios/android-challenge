package br.com.gamemarket.feature.main

import br.com.gamemarket.base.extensions.launch
import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.dto.SimpleGameDto
import br.com.gamemarket.data.model.whenever
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import kotlinx.coroutines.CoroutineDispatcher

class MainPresenter(
    override var view: MainContract.View,
    private val gameRepository: GameRepository,
    private val localRepository: CartRepository,
    private val dispacherContext: CoroutineDispatcher
) : MainContract.Presenter {

    override fun loadGames() {
        view.showLoadingGames()

        dispacherContext.launch {
            gameRepository.getGames().whenever(
                isBody = { simpleGameDto ->
                    view.hideLoadingGames()
                    view.onSuccessfulLoadGames(simpleGameDto.toGames())
                },
                isError = { msg ->
                    view.hideLoadingGames()
                    view.onFailuereLoadGames(msg)
                }
            )
        }
    }

    override fun loadCart() {
        dispacherContext.launch {
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

    private fun List<SimpleGameDto>.toGames(): List<Game> {
        return this.map { it.toGame() }
    }

    private fun SimpleGameDto.toGame(): Game {
        return Game(
            id,
            name,
            "",
            price,
            0,
            platform,
            image,
            emptyList()
        )
    }
}