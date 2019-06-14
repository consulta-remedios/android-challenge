package br.com.gamemarket.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CreditCard (
    val id: Long,
    val name: String,
    val number:String,
    val flag: Double,
    val expirationDate: Int,
    val code: String
): Parcelable