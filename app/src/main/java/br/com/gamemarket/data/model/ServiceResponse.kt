package br.com.gamemarket.data.model

sealed class ServiceResponse<out T> {
    object OK : ServiceResponse<Nothing>()
    class BODY<out T>(val value: T) : ServiceResponse<T>()
    class ERROR(val message: String = "") : ServiceResponse<Nothing>()
}

fun <T : Any, R : Any> ServiceResponse<T>.convert(function: (T) -> R) = when (this) {
    is ServiceResponse.BODY -> ServiceResponse.BODY(
        function(this.value)
    )
    is ServiceResponse.OK -> this
    is ServiceResponse.ERROR -> this
}

fun <T> ServiceResponse<T>.whenever(
    isBody: (T) -> Unit = {},
    isError: (String) -> Unit = {},
    isOk: () -> Unit = {}
) {
    when (this) {
        is ServiceResponse.BODY<T> -> isBody(this.value)
        is ServiceResponse.ERROR -> isError(this.message)
        is ServiceResponse.OK -> isOk
    }
}