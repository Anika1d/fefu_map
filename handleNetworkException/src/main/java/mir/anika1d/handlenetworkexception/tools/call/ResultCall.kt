package mir.anika1d.handlenetworkexception.tools.call

import mir.anika1d.handlenetworkexception.tools.ResponseError
import mir.anika1d.handlenetworkexception.tools.result.HttpException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import mir.anika1d.handlenetworkexception.tools.result.Result

@Suppress("UNCHECKED_CAST")
internal class ResultCall<T>(private val proxy: Call<T>) : CallDelegate<T, Result<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<Result<T>>) {
        proxy.enqueue(ResultCallback(this, callback))
    }

    override fun cloneImpl(): ResultCall<T> {
        return ResultCall(proxy.clone())
    }

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<Result<T>>
    ) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result: Result<T>
            if (response.isSuccessful) {
                result = Result.Success.HttpResponse(
                    value = response.body() as T,
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    url = call.request().url.toString(),
                )
            } else {
                val errorBody=response.errorBody()?.string()
                result = Result.Failure.HttpError(
                    HttpException(
                        statusCode = response.code(),
                        statusMessage = response.message(),
                        url = call.request().url.toString(),
                    ),
                    value = ResponseError.fromJson(errorBody)
                )
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is retrofit2.HttpException -> {
                    val errorBody=error.response()?.errorBody()?.string()

                    Result.Failure.HttpError(
                    HttpException(
                        statusCode = error.code(),
                        statusMessage = error.message(),
                        cause = error
                    ),
                    value = ResponseError.fromJson(errorBody)
                )}
                is IOException -> Result.Failure.Error(
                    error,
                    null
                )
                else -> Result.Failure.Error(error, null)
            }

            callback.onResponse(proxy, Response.success(result))
        }
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}