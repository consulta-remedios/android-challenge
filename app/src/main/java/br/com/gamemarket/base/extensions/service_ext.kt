package br.com.gamemarket.base.extensions

import br.com.gamemarket.data.model.ServiceResponse
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume

suspend fun <T : Any> Call<T>.awaitResult(): ServiceResponse<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resume(
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            ServiceResponse.ERROR("Response body is null")
                        } else {
                            ServiceResponse.BODY(body)
                        }
                    } else {
                        ServiceResponse.ERROR(response.message())
                    }
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (continuation.isCancelled) return
                continuation.resume(ServiceResponse.ERROR(t.message ?: "Erro inesperado"))
            }
        })

        registerOnCompletion(continuation)
    }
}

private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        try {
            cancel()
        } catch (ex: Throwable) {
        }
    }
}