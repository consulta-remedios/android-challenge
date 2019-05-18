package br.com.gamemarket.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Game(
    val id: Long,
    val name: String,
    val description:String,
    val price: Double,
    val score: Int,
    val platform: String,
    val image: String,
    val images: List<String>
) : Parcelable