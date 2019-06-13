package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.Address
import br.com.gamemarket.data.model.ServiceResponse

private val addressList = mutableListOf<Address>()

class AddressStaticRepository: AddressRepository{

    override suspend fun getAddress(): ServiceResponse<List<Address>> {
        return ServiceResponse.BODY(addressList)
    }

    override suspend fun add(address: Address): ServiceResponse<*> {
        val item = addressList.getItemId(address)

        if (item == -1) {
            addressList.add(address)
        } else {
            addressList[item] = address
        }

        return ServiceResponse.OK
    }

    override suspend fun remove(address: Address): ServiceResponse<*> {
        return try {
            val index = addressList.getItemId(address)

            addressList.removeAt(index)

            ServiceResponse.OK
        } catch (e: Exception) {
            ServiceResponse.ERROR(e.localizedMessage)
        }
    }

    private fun List<Address>.getItemId(address: Address): Int {
        return this.indexOf(address)
    }

}