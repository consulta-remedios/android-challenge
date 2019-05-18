package br.com.gamemarket.data.remote.gamecheckout

import br.com.gamemarket.base.extensions.awaitResult
import br.com.gamemarket.data.model.ServiceResponse
import br.com.gamemarket.data.model.dto.SimpleGameDto
import br.com.ideliver.model.GameDto
import kotlinx.coroutines.delay

class GameService(private val api: GameApi) : GameRepository {
    override suspend fun getGames(): ServiceResponse<List<SimpleGameDto>> {
        return api.getGames().awaitResult()
    }

    // mock service
    override suspend fun getGame(gameId: Long): ServiceResponse<GameDto> {
        delay(2200)

        return ServiceResponse.BODY(
            GameDto(
                gameId
            )
        )
    }
}