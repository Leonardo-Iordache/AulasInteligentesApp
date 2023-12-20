package com.example.aulasinteligentes.conection

import com.example.aulasinteligentes.entities.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ClientService {
    @POST("registrarNuevoUsuario")
    suspend fun createNewUser(@Body usuario: User): Int

    @POST("validarInicioSesion")
    suspend fun checkUser(@Body usuario: User): Call<Boolean>

    @GET("getTemperaturaInteriorAula")
    suspend fun getTemperaturaInterior(@Query("aulaId") aulaId: String,
                                @Query("fechaDesde") fechaDesde: String,
                                @Query("fechaHasta") fechaHasta: String): Response<ArrayList<TemperaturaInterior>>

    @GET("getTemperaturaExteriorAula")
    suspend fun getTemperaturaExterior(@Query("aulaId") aulaId: String,
                                       @Query("fechaDesde") fechaDesde: String,
                                       @Query("fechaHasta") fechaHasta: String): Response<ArrayList<TemperaturaExterior>>

    @GET("getRuidoPasillo")
    suspend fun getNoiseList(@Query("pasilloId") pasilloId: String,
                             @Query("fechaDesde") fechaDesde: String,
                             @Query("fechaHasta") fechaHasta: String): Response<ArrayList<Noise>>

    @GET("getHumedadAula")
    suspend fun getHumidityList(@Query("aulaId")aulaId: String,
                                @Query("fechaDesde")fechaDesde: String,
                                @Query("fechaHasta")fechaHasta: String): Response<ArrayList<Humidity>>

    @GET("getLuzAula")
    suspend fun getIluminationList(@Query("aulaId")aulaId: String,
                                   @Query("fechaDesde")fechaDesde: String,
                                   @Query("fechaHasta")fechaHasta: String): Response<ArrayList<Ilumination>>

    @GET("prediccion")
    suspend fun getPredictionsList(@Query("fecha") fecha: String): Response<ArrayList<PredictionTemp>>

    @GET("getPasillos")
    suspend fun getPasillos(): Response<ArrayList<Hall>>

    @GET("getAulas")
    suspend fun getAulas(): Response<ArrayList<Aula>>

    @GET("ventana")
    suspend fun changeWindowState(@Query("aulaId") aulaId: String)

    @GET("cortina")
    suspend fun changeBlindState(@Query("aulaId") aulaId: String)

    @GET("aire")
    suspend fun changeACState(@Query("aulaId") aulaId: String)
}