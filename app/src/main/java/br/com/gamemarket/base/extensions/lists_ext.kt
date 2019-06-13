package br.com.gamemarket.base.extensions

fun List<String>.checkLengthListImages(path: String): List<String> {
    return if (this.isNotEmpty()) {
        this
    } else {
        val aux = mutableListOf<String>()
        aux.add(path)
        aux
    }

}
 