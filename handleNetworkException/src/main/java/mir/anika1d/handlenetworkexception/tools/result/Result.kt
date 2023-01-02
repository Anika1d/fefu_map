package mir.anika1d.handlenetworkexception.tools.result

import mir.anika1d.handlenetworkexception.tools.ResponseError


sealed class Result<out T> {

    sealed class Success<T> : Result<T>() {

        abstract val value: T

        override fun toString() = "Success($value)"

        class Value<T>(override val value: T) : Success<T>()

        data class HttpResponse<T>(
            override val value: T,
            override val statusCode: Int,
            override val statusMessage: String? = null,
            override val url: String? = null,
        ) : Success<T>(), mir.anika1d.handlenetworkexception.tools.result.HttpResponse

        object Empty : Success<Nothing>() {

            override val value: Nothing get() = error("No value")

            override fun toString() = "Success"
        }
    }


    sealed class Failure<E : Throwable>(open val _error: E? = null) : Result<Nothing>() {

        override fun toString() = "Failure($_error)"
        abstract val value: ResponseError?

        class Error(
            override val _error: Throwable,
            override val value: ResponseError?
        ) : Failure<Throwable>(_error)

        class HttpError(
            override val _error: HttpException,
            override val value: ResponseError?
        ) : Failure<HttpException>(),
            HttpResponse {
            override val statusCode: Int get() = _error.statusCode
            override val statusMessage: String? get() = _error.statusMessage
            override val url: String? get() = _error.url
        }
    }
}

typealias EmptyResult = Result<Nothing>