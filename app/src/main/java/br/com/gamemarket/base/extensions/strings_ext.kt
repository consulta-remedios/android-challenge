package br.com.gamemarket.base.extensions

fun String.hideCardNumber(): String{
    val first =  this.substring(0, 3)
    val last = this.substring(12, 15)

    return "$first **** **** $last"
}