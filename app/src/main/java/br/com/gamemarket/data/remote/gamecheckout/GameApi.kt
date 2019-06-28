package br.com.gamemarket.data.remote.gamecheckout

import br.com.gamemarket.data.model.dto.GameDto
import br.com.gamemarket.data.model.dto.SimpleGameDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {
    @GET("/game")
    fun getGames(): Call<List<SimpleGameDto>>

    @GET("/game/{id}")
    fun getGame(@Path("id") id: Long): Call<GameDto>
}