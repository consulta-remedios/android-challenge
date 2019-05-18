package br.com.gamemarket.data.model.dto

data class SimpleGameDto(
    val id: Long,
    val name: String,
    val price: Double,
    val platform: String,
    val image: String
)