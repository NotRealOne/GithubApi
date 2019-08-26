package com.kdaydin.inginterview.network

import com.kdaydin.inginterview.data.Repository
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by kubilaay on 2019-08-26.
 */
interface ApiService {
    @GET("/users/{user}/repos")
    fun getReposByUsername(@Path("user") username: String): Call<List<Repository>>

}