package br.com.gamemarket.data.model

data class ItemCart(
    val id: Long,
    val name: String,
    val price: Double,
    val platform: String,
    val image: String,
    var quantity: Int
)