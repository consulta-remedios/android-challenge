package br.com.gamemarket.test.feature.game

import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.local.mockRepository
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import br.com.gamemarket.feature.game.GameContract
import br.com.gamemarket.feature.game.GamePresenter
import com.consultaremedios.base.extensions.mockito
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyList

class GamePresenterTest {
    private val view: GameContract.View by mockito()
    private val repository: GameRepository by mockito()
    private val localRepository: CartRepository by mockito()

    private val presenter = GamePresenter(
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