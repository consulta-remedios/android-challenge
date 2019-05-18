package br.com.gamemarket.base.extensions

import android.app.Activity
import android.content.Context
import android.widget.Toast

inline fun <reified T> Activity.extra(key: String, defaultValue: T? = null): Lazy<T> = lazy {
    val value = intent.extras?.get(key)

    when {
        value is T -> value
        defaultValue != null -> defaultValue
        else -> throw IllegalArgumentException(
            "Couldn't find extra with key \"$key\" from type " +
                    T::class.java.canonicalName
        )
    }
}

fun Context.showToast(message:String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}