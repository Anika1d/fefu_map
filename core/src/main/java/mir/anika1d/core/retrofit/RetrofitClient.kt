package mir.anika1d.core.retrofit


import mir.anika1d.core.retrofit.urls.Urls
import mir.anika1d.handlenetworkexception.tools.adapter.ResultAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun provideOkHttpClientCore(
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(100L, TimeUnit.SECONDS)
        .readTimeout(100L, TimeUnit.SECONDS)
        .writeTimeout(100L, TimeUnit.SECONDS)
        .callTimeout(100L, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }).build()
}

fun provideRetrofitCore(
    okHttpClient: OkHttpClient,
): Retrofit = Retrofit
    .Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(ResultAdapterFactory())
    .baseUrl(Urls.FEFU_MAP)
    .client(okHttpClient)
    .build()

fun provideRetrofitServicesCore(
    retrofit: Retrofit
): RetrofitServices =
    retrofit.create(RetrofitServices::class.java)

