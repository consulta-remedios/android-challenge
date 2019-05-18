package br.com.gamemarket.base.di

import br.com.gamemarket.data.local.CartRepository
import br.com.gamemarket.data.local.CartStaticRepository
import br.com.gamemarket.data.remote.gamecheckout.GameApi
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import br.com.gamemarket.data.remote.gamecheckout.GameService
import org.koin.dsl.module.module
import retrofit2.Retrofit

val repositoryModule = module {
    factory { get<Retrofit>(BASE_SERVER).create(GameApi::class.java) }

    factory { GameService(api = get()) } bind GameRepository::class

    factory { CartStaticRepository() } bind CartRepository::class
}