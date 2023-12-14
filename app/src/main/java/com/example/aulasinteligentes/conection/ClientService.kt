package com.example.aulasinteligentes.conection

import com.example.aulasinteligentes.entities.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ClientService {
    @POST("RUTA")
    suspend fun createNewUser(@Body usuario: User): Call<Int>

    @POST("RUTA")
    suspend fun checkUser(@Body usuario: User): Call<Boolean>

    @GET("RUTA")
    suspend fun getTemperatures(): Response<ArrayList<Temperatures>>

}