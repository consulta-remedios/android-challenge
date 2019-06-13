package br.com.gamemarket.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Address (
    val id: Long,
    val name: String,
    val street: String,
    val number: Double,
    val city: Int,
    val state: String,
    val country: String
): Parcelable