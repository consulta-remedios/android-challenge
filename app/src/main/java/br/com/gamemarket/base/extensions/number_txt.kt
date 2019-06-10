package br.com.gamemarket.base.extensions

fun Number.toCurrency() : String{
    val value = this.toString().replace('.', ',')
    return "R$ $value"
}