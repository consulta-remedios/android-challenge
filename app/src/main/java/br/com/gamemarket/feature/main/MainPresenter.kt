package br.com.gamemarket.feature.main

class MainPresenter(
    override var view: MainContract.View
) : MainContract.Presenter {
    override fun test() {
        view.test()
    }
}