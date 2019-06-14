package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.data.model.ServiceResponse

interface CartRepository {
    suspend fun getCart(): ServiceResponse<List<ItemCart>>
    suspend fun addItem(game: Game): ServiceResponse<*>
    suspend fun remove(game: Game): ServiceResponse<*>
    suspend fun removeItem(id: Long): ServiceResponse<*>
    suspend fun removeItem(game: Game): ServiceResponse<*>
    suspend fun removeAll(): ServiceResponse<*>
}