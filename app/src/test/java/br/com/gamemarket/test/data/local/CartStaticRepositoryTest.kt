package br.com.gamemarket.test.data.local

import br.com.gamemarket.data.local.CartStaticRepository
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.data.model.ServiceResponse
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class CartStaticRepositoryTest {

    private val cartRepository = CartStaticRepository()

    @Test
    fun `test get cart`() {
        runBlocking {
            cartRepository.removeAll()

            val cart = forceGetList()

            assertEquals(cart.size, 0)
        }
    }

    @Test
    fun `test add same item cart then plus quantity`() {
        runBlocking {
            cartRepository.removeAll()

            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))

            val cart = forceGetList()

            assertEquals(cart.size, 1)
            assertEquals(cart.sumQuantity(), 3)
        }
    }

    @Test
    fun `test add diferents item cart then add games`() {
        runBlocking {
            cartRepository.removeAll()

            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(2))
            cartRepository.addItem(generateGame(3))

            val cart = forceGetList()

            assertEquals(cart.size, 3)
            assertEquals(cart.sumQuantity(), 3)

            cart.forEach {
                assertEquals(it.quantity, 1)
            }
        }
    }

    @Test
    fun `test remove some items then keep game and change quantity`() {
        runBlocking {
            cartRepository.removeAll()

            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))

            cartRepository.removeItem(1)
            cartRepository.removeItem(generateGame(1))

            val cart = forceGetList()

            assertEquals(cart.size, 1)
            assertEquals(cart.sumQuantity(), 2)
        }
    }

    @Test
    fun `test remove each item then remove game`() {
        runBlocking {
            cartRepository.removeAll()

            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))

            cartRepository.removeItem(1)
            cartRepository.removeItem(1)
            cartRepository.removeItem(generateGame(1))

            val cart = forceGetList()

            assertEquals(cart.size, 0)
            assertEquals(cart.sumQuantity(), 0)
        }
    }

    @Test
    fun `test remove all then cart is empty`() {
        runBlocking {

            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(1))
            cartRepository.addItem(generateGame(2))
            cartRepository.addItem(generateGame(2))
            cartRepository.addItem(generateGame(3))

            cartRepository.removeAll()

            val cart = forceGetList()

            assertEquals(cart.size, 0)
            assertEquals(cart.sumQuantity(), 0)
        }
    }

    private suspend fun forceGetList(): List<ItemCart> {
        return (cartRepository.getCart() as? ServiceResponse.BODY)?.value
            ?: throw RuntimeException()
    }

    private fun generateGame(id: Long): Game {
        return Game(
            id = id,
            name = "",
            description = "",
            price = 0.0,
            score = 1,
            platform = "",
            image = "",
            images = emptyList()
        )
    }

    private fun List<ItemCart>.sumQuantity() : Int {
        return this.sumBy { it.quantity }
    }
}