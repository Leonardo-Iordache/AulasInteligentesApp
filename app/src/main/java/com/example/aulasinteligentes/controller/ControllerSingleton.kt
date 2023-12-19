package com.example.aulasinteligentes.controller

import android.util.Log
import com.example.aulasinteligentes.conection.ClientServiceImpl
import com.example.aulasinteligentes.entities.Aula
import com.example.aulasinteligentes.entities.Hall
import com.example.aulasinteligentes.entities.Humidity
import com.example.aulasinteligentes.entities.Ilumination
import com.example.aulasinteligentes.entities.Noise
import com.example.aulasinteligentes.entities.PredictionTemp
import com.example.aulasinteligentes.entities.TemperaturaExterior
import com.example.aulasinteligentes.entities.TemperaturaInterior
import com.example.aulasinteligentes.entities.User
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

object ControllerSingleton {
    private val restApiService = ClientServiceImpl()

    suspend fun validarUsuario(user: User): Boolean {
        return restApiService.validateUser(user)
    }

    suspend fun createUser(user: User): Int{
        return restApiService.createUser(user)
    }

    suspend fun getTemperaturaInterior(idAula: String): ArrayList<TemperaturaInterior>?{
        return restApiService.getTemperaturaInterior(idAula)
    }

    suspend fun getTemperaturaExterior(idAula: String): ArrayList<TemperaturaExterior>?{
        return restApiService.getTemperaturaExterior(idAula)
    }

    suspend fun getIluminationList(idAula:String): ArrayList<Ilumination>?{
        return restApiService.getIluminationList(idAula)
    }

    suspend fun getNoiseList(idPasillo: String): ArrayList<Noise>?{
        return restApiService.getNoiseList(idPasillo)
    }

    suspend fun getHumidityList(idAula: String): ArrayList<Humidity>?{
        return restApiService.getHumidityList(idAula)
    }

    suspend fun getPredictionList(fecha: String): ArrayList<PredictionTemp>?{
        return restApiService.getPredictionList(fecha)
    }

    fun parseDate(dateString: String): Timestamp {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        var timeStamp = Timestamp(System.currentTimeMillis())

        try {
            val date = dateFormat.parse(dateString)
            if (date != null) {
                timeStamp = Timestamp(date.time)
            }
        } catch (e: Exception) {
            Log.e("DateParser", "Error parsing date: $e")
        }

        return timeStamp
    }

}