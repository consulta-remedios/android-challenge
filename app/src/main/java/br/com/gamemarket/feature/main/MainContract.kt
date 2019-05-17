package br.com.gamemarket.feature.main

import br.com.gamemarket.base.BasePresenter
import br.com.gamemarket.base.BaseView

interface MainContract {
   interface View : BaseView<Presenter> {
      fun test()
   }

   interface Presenter : BasePresenter<View> {
      fun test()
   }
}