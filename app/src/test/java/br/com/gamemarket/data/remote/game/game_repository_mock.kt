package br.com.gamemarket.data.remote.game

import br.com.gamemarket.data.model.ServiceResponse
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking

fun GameRepository.mockSuccessful() {
    runBlocking {
        whenever(this@mockSuccessful.getGames())
            .thenReturn(
                ServiceResponse.BODY(emptyList())
            )
    }
}

fun GameRepository.mockFailure() {
    runBlocking {
        whenever(this@mockFailure.getGames())
            .thenReturn(
                ServiceResponse.ERROR("Erro inesperado")
            )
    }
}