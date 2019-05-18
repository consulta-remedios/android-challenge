package br.com.gamemarket.data.remote.gamecheckout

import br.com.gamemarket.data.model.ServiceResponse
import br.com.gamemarket.data.model.dto.SimpleGameDto
import br.com.ideliver.model.GameDto

interface GameRepository {
    suspend fun getGames(): ServiceResponse<List<SimpleGameDto>>
    suspend fun getGame(gameId: Long): ServiceResponse<GameDto>
}