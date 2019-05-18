package br.com.gamemarket.base.di

import br.com.gamemarket.feature.game.GameContract
import br.com.gamemarket.feature.game.GamePresenter
import br.com.gamemarket.feature.main.MainContract
import br.com.gamemarket.feature.main.MainPresenter
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Main
import org.koin.dsl.module.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    factory { (view: MainContract.View) ->
        MainPresenter(
            view = view,
            gameRepository = get(),
            localRepository = get(),
            dispacherContext = get()
        )
    } bind MainContract.Presenter::class

    factory { (view: GameContract.View) ->
        GamePresenter(
            view = view,
            gameRepository = get(),
            dispacherContext = get()
        )
    } bind GameContract.Presenter::class
}

val featureModule = module {
    single {
        GsonConverterFactory.create(GsonBuilder().create())
    } bind Converter.Factory::class
}

val dispatcherModule = module {
    factory { Main as CoroutineDispatcher }
}