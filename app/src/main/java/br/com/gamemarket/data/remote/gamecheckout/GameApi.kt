package br.com.gamemarket.data.remote.gamecheckout

import br.com.gamemarket.data.model.GameDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {
    @GET("/game")
    fun getGames(): Call<List<GameDto>>

    @GET("/game/{id}")
    fun getGame(@Path("id") id: Long): Call<GameDto>
}