package com.consultaremedios.base.extensions

import android.content.Intent
import android.os.Parcelable

fun <T : Parcelable> Intent.putExtra(name: String, value: List<T>) {
    this.putParcelableArrayListExtra(name, ArrayList(value))
}