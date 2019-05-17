package br.com.gamemarket.base

interface BaseView<out T : BasePresenter<*>> {
    val presenter: T
}