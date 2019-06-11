package br.com.gamemarket.data.remote.gamecheckout

import br.com.gamemarket.base.extensions.awaitResult
import br.com.gamemarket.data.model.ServiceResponse
import br.com.gamemarket.data.model.GameDto

class GameService(private val api: GameApi) : GameRepository {
    override suspend fun getGames(): ServiceResponse<List<GameDto>> {
        return api.getGames().awaitResult()
    }

    // mock service
    override suspend fun getGame(gameId: Long): ServiceResponse<GameDto> {

        return ServiceResponse.BODY(
            GameDto(
                gameId
            )
        )
    }
}