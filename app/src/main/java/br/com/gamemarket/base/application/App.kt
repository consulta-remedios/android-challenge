package br.com.gamemarket.base.application

import android.app.Application
import br.com.gamemarket.base.di.appModule
import br.com.gamemarket.base.di.dispatcherModule
import br.com.gamemarket.base.di.featureModule
import org.koin.android.ext.android.startKoin

@Suppress("unused")
class App : Application() {

    private val modules = listOf(
        appModule,
        featureModule,
        dispatcherModule
    )

    override fun onCreate() {
        super.onCreate()

        startKoin(this, modules)
    }
}
