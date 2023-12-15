package com.example.aulasinteligentes.conection

import com.example.aulasinteligentes.entities.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ClientService {
    @POST("AulasInteligentes/registrarNuevoUsuario")
    suspend fun createNewUser(@Body usuario: User): Int

    @GET("AulasInteligentes/registrarNuevoUsuario")
    suspend fun getNewUser(): Response<Int>

    @POST("validarInicioSesion")
    suspend fun checkUser(@Body usuario: User): Call<Boolean>

    @GET("")
    suspend fun getTemperatures(): Response<ArrayList<Temperatures>>

    @GET("RUTA")
    suspend fun getPrediction(): Response<ArrayList<Temperatures>>
}