package com.example.aulasinteligentes.conection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object ServiceBuilder {
    private const val serverURL = "http://192.168.1.138:8080"
    private val client = OkHttpClient.Builder().build()
    private val gson: Gson = GsonBuilder().create()

    private val retrofit = Retrofit.Builder()
        .baseUrl(serverURL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(client)
        .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }

    fun getURL(): String{
        return this.serverURL
    }
}