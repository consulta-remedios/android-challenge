package br.com.gamemarket.data.local

import br.com.gamemarket.data.model.ServiceResponse
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking

fun CartRepository.mockRepository() {
    runBlocking {
        whenever(this@mockRepository.getCart())
            .thenReturn(
                ServiceResponse.BODY(mutableListOf())
            )

        whenever(this@mockRepository.addItem(any()))
            .thenReturn(
                ServiceResponse.OK
            )
    }
}