package br.com.gamemarket.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemCart(
    val id: Long,
    val name: String,
    val price: Double,
    val platform: String,
    val image: String,
    var quantity: Int
): Parcelable