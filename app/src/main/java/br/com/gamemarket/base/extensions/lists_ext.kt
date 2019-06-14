package br.com.gamemarket.base.extensions

import br.com.gamemarket.data.model.ItemCart

fun List<String>.checkLengthListImages(path: String): List<String> {
    return if (this.isNotEmpty()) {
        this
    } else {
        val aux = mutableListOf<String>()
        aux.add(path)
        aux
    }

}

fun List<ItemCart>.getItemIfExists(id: Long): ItemCart? {
    return this.firstOrNull { it.id == id }
}