package com.example.saveingnote.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"



object MarsApi {
    private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create()).baseUrl(BASE_URL).build()
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}

interface MarsApiService{
    @GET("photos")
    suspend fun getPhotos(): String
}