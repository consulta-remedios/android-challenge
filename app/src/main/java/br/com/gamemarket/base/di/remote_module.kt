package br.com.gamemarket.base.di

import br.com.gamemarket.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val HEADER_INTERCEPTOR = "HEADER_INTERCEPTOR"

const val BASE_SERVER = "BASE_SERVER"

val retrofitClientModule = module {

    single(BASE_SERVER) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(/* OkHttpClient */ get())
            .addConverterFactory(get())
            .build()
    } bind Retrofit::class

    single {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(get(HEADER_INTERCEPTOR))
            .build()
    } bind OkHttpClient::class

    single(HEADER_INTERCEPTOR) {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Token", BuildConfig.SERVICE_ATHORIZATION_KEY)
                    .addHeader("SO", "android")
                    .addHeader("Version", BuildConfig.VERSION_CODE.toString())
                    .build()
            )
        }
    }
}