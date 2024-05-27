package data.services

import android.content.Context
import data.constants.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitConnection {
    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        addInterceptor(ApiKeyInterceptor())

    }.build()

    public val builder = Retrofit.Builder()
        .baseUrl(AppConstants.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    var serviceIncidencies: IncidenciesEndpoints = builder.create()
    var serviceUsuarisEndpoints: UsuarisEndpoints = builder.create()
    var serviceZonesEndpoints: ZonesEndpoints = builder.create()
    var serviceUsuariResolEndpoints: UsuarisResolsEndpoints = builder.create()

    private class ApiKeyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            val originalRequest = chain.request()
            val requestWithApiKey = originalRequest.newBuilder()
                .header("X-Api-Key", AppConstants.apiKeyAdmin)
                .build()
            return chain.proceed(requestWithApiKey)
        }
    }


}