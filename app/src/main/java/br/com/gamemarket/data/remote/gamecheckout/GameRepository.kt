package br.com.gamemarket.data.remote.gamecheckout

import br.com.gamemarket.data.model.ServiceResponse
import br.com.gamemarket.data.model.GameDto

interface GameRepository {
    suspend fun getGames(): ServiceResponse<List<GameDto>>
    suspend fun getGame(gameId: Long): ServiceResponse<GameDto>
}