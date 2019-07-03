package br.com.gamemarket.data.model.dto

data class GameDto(
    val id: Long,
    val name: String,
    val description:String,
    val price: Double,
    val score: Int,
    val platform: String,
    val image: String,
    val images: List<String>
)