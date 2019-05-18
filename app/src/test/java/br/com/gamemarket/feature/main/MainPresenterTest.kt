package br.com.gamemarket.feature.main

import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.remote.game.mockFailure
import br.com.gamemarket.data.remote.game.mockSuccessful
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import com.consultaremedios.base.extensions.mock
import com.nhaarman.mockitokotlin2.inOrder
import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString

class MainPresenterTest {
    private val view: MainContract.View by mock()
    private val repository: GameRepository by mock()
    private val localRepository: CartRepository by mock()

    private val presenter = MainPresenter(view, repository, localRepository, Dispatchers.Unconfined)

    @Test
    fun `test get games with success then show games`() {
        repository.mockSuccessful()

        presenter.loadGames()

        inOrder(view) {
            verify(view).showLoadingGames()
            verify(view).hideLoadingGames()
            verify(view).onSuccessfulLoadGames(anyList())
        }
    }

    @Test
    fun `test get games with error then show message`() {
        repository.mockFailure()

        presenter.loadGames()

        inOrder(view) {
            verify(view).showLoadingGames()
            verify(view).hideLoadingGames()
            verify(view).onFailuereLoadGames(anyString())
        }
    }
}