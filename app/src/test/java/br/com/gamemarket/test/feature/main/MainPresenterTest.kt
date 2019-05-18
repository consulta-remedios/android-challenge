package br.com.gamemarket.test.feature.main

import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.local.mockRepository
import br.com.gamemarket.data.remote.game.mockFailure
import br.com.gamemarket.data.remote.game.mockSuccessful
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import br.com.gamemarket.feature.main.MainContract
import br.com.gamemarket.feature.main.MainPresenter
import com.consultaremedios.base.extensions.mockito
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString

class MainPresenterTest {
    private val view: MainContract.View by mockito()
    private val repository: GameRepository by mockito()
    private val localRepository: CartRepository by mockito()

    private val presenter = MainPresenter(
        view,
        repository,
        localRepository,
        Dispatchers.Unconfined
    )

    @Before
    fun setup(){
        localRepository.mockRepository()
    }

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

    @Test
    fun `test load cart then refresh cart count`() {
        presenter.loadCart()

        verify(view).onChangeCartSize(anyList())
    }

    @Test
    fun `test add item then refresh cart count`() {
        presenter.addItemCard(mock())
        verify(view).onChangeCartSize(anyList())
    }

    @Test
    fun `test remove item then refresh cart count`() {
        presenter.addItemCard(mock())
        verify(view).onChangeCartSize(anyList())

    }
}