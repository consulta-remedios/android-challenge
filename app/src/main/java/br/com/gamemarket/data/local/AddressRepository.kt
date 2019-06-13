package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.*

interface AddressRepository {
    suspend fun getAddress(): ServiceResponse<List<Address>>
    suspend fun add(address: Address): ServiceResponse<*>
    suspend fun remove(address: Address): ServiceResponse<*>
}