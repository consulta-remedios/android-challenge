package br.com.gamemarket.base.di

import br.com.gamemarket.data.local.*
import br.com.gamemarket.data.remote.gamecheckout.GameApi
import br.com.gamemarket.data.remote.gamecheckout.GameRepository
import br.com.gamemarket.data.remote.gamecheckout.GameService
import org.koin.dsl.module.module
import retrofit2.Retrofit

val repositoryModule = module {
    factory { get<Retrofit>(BASE_SERVER).create(GameApi::class.java) }

    factory { GameService(api = get()) } bind GameRepository::class

    factory { CartStaticRepository() } bind CartRepository::class

    factory { AddressStaticRepository() } bind AddressRepository::class

    factory { CreditCardStaticRepository() } bind CreditCardRepository::class
}