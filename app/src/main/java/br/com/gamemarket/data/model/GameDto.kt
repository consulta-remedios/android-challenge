package br.com.gamemarket.data.model

data class GameDto(
    val id: Long,
    val name: String = "",
    val description: String = "",
    val price: Double = 0.0,
    val platform: String = "",
    val image: String = ""
)