package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.CreditCard
import br.com.gamemarket.data.model.ServiceResponse

private val creditCardList = mutableListOf<CreditCard>()

class CreditCardStaticRepository: CreditCardRepository {

    override suspend fun getCreditCards(): ServiceResponse<List<CreditCard>> {
        return ServiceResponse.BODY(creditCardList)
    }

    override suspend fun add(creditCard: CreditCard): ServiceResponse<*> {
        val item = creditCardList.getItemId(creditCard)

        if (item == -1) {
            creditCardList.add(creditCard)
        } else {
            creditCardList[item] = creditCard
        }

        return ServiceResponse.OK
    }

    override suspend fun remove(creditCard: CreditCard): ServiceResponse<*> {
        return try {
            val index = creditCardList.getItemId(creditCard)

            creditCardList.removeAt(index)

            ServiceResponse.OK
        } catch (e: Exception) {
            ServiceResponse.ERROR(e.localizedMessage)
        }
    }

    private fun List<CreditCard>.getItemId(creditCard: CreditCard): Int {
        return this.indexOf(creditCard)
    }

}