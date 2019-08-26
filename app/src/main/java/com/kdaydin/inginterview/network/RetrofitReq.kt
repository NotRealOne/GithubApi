package com.kdaydin.inginterview.network

import android.util.Log
import com.google.gson.JsonSyntaxException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by kubilaay on 2019-08-26.
 */
class RetrofitReq() {
    private val _baseURL = "https://api.github.com/"
    var retrofit: Retrofit? = null

    init {
        retrofit = getRetrofitInstance()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        httpClient.writeTimeout(60, TimeUnit.SECONDS)
        httpClient.retryOnConnectionFailure(false)

        val loggingInterceptor = HttpLoggingInterceptor { message ->
            try {
                if (message.isNotEmpty()) {
                    Log.i("HTTP_CONNECTION", message + "\n")
                }
            } catch (e: JsonSyntaxException) {
                Log.i("HTTP_CONNECTION_ERROR", message)
            }
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(loggingInterceptor)

        return httpClient.build()
    }

    private fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(_baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(createOkHttpClient())
                .build()
        }
        return retrofit
    }

    fun getGithubApi(): ApiService? {
        return getRetrofitInstance()?.create(ApiService::class.java)
    }


}