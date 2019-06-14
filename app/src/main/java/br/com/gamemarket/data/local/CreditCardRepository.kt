package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.CreditCard
import br.com.gamemarket.data.model.Game
import br.com.gamemarket.data.model.ItemCart
import br.com.gamemarket.data.model.ServiceResponse

interface CreditCardRepository {
    suspend fun getCreditCards(): ServiceResponse<List<CreditCard>>
    suspend fun add(creditCard: CreditCard): ServiceResponse<*>
    suspend fun remove(creditCard: CreditCard): ServiceResponse<*>
}