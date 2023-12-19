package com.example.aulasinteligentes.conection

import android.util.Log
import com.example.aulasinteligentes.entities.Aula
import com.example.aulasinteligentes.entities.Hall
import com.example.aulasinteligentes.entities.Humidity
import com.example.aulasinteligentes.entities.Ilumination
import com.example.aulasinteligentes.entities.Noise
import com.example.aulasinteligentes.entities.PredictionTemp
import com.example.aulasinteligentes.entities.TemperaturaExterior
import com.example.aulasinteligentes.entities.TemperaturaInterior
import com.example.aulasinteligentes.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ClientServiceImpl{
    private val serverUrl = ServiceBuilder.getURL()
    private val retrofit = ServiceBuilder.buildService(ClientService::class.java)


    suspend fun getNewUser(): Int{
        var response = 1

        try{
            Log.d("AAAAA", "HE entrado")

            val call = retrofit.getNewUser()
            val result = call.body()
            Log.d("AAAAA", "Correcto: $result")
            if (call.isSuccessful){
                response = result ?: 12
            }
            else {
                Log.d("ClientServiceLLL", "La solicitud no fue exitosa: ${call.code()}")
                val errorBody = call.errorBody()?.string()
                Log.d("ClientServiceLLL", "Mensaje de error: $errorBody")
            }
        } catch (e: Exception){
            Log.e("ClientServiceLLL", "Error en la solicitud: ${e.message}", e)

        }
        Log.i("ClientServiceLLL", "Resultado: $response")
        return response
    }


    suspend fun createUser(user: User): Int {
        try {
            val result = retrofit.createNewUser(user)
            Log.i("ClientServiceLLL", "Usuario creado correctamente: $result")
            return result
        } catch (e: Exception) {
            Log.e("ClientServiceLLL", "Error en la solicitud: ${e.message}", e)
        }
        return 0
    }


    suspend fun validateUser(user: User): Boolean{
        var respuesta = false
        val call = retrofit.checkUser(user)
        call.enqueue(object : Callback<Boolean>{

            override fun onResponse(call: Call<Boolean>, response: Response<Boolean>) {
                if (response.isSuccessful){
                    respuesta = response.body() ?: false
                }
                else{
                    Log.e("ClientServiceImpl", "Error al validar el usuario")
                }
            }

            override fun onFailure(call: Call<Boolean>, t: Throwable) {
                Log.e("ClientServiceImpl", "Error de conexion")
            }
        })
        return respuesta
    }


    suspend fun getTemperaturaInterior(idAula: String): ArrayList<TemperaturaInterior>? {
        try {
            val fechaDesde = restarUnMesAFechaActual()
            val fechaHasta = obtenerFechaActual()

            Log.i("ClientServiceImpl", "FECHA DESDE: $fechaDesde")
            Log.i("ClientServiceImpl", "FECHA HASTA: $fechaHasta")

            val call = retrofit.getTemperaturaInterior(idAula, fechaDesde, fechaHasta)

            if (call.isSuccessful) {
                return call.body()
            } else {
                val errorBody = call.errorBody()?.string() ?: "Error Desconocido"
                Log.e("ClientServiceImpl", "Error al coger las temperaturas - Código: ${call.code()}, Mensaje: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("ClientServiceImpl", "Excepción al obtener las temperaturas: ${e.message}", e)
        }
        return null
    }



    suspend fun getTemperaturaExterior(idAula: String): ArrayList<TemperaturaExterior>? {
        try {
            val fechaDesde = restarUnMesAFechaActual()
            val fechaHasta = obtenerFechaActual()

            Log.i("ClientServiceImpl", "FECHA DESDE: $fechaDesde")
            Log.i("ClientServiceImpl", "FECHA HASTA: $fechaHasta")

            val call = retrofit.getTemperaturaExterior(idAula, fechaDesde, fechaHasta)

            if (call.isSuccessful) {
                return call.body()
            } else {
                val errorBody = call.errorBody()?.string() ?: "Error Desconocido"
                Log.e("ClientServiceImpl", "Error al coger las temperaturas - Código: ${call.code()}, Mensaje: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("ClientServiceImpl", "Excepción al obtener las temperaturas: ${e.message}", e)
        }
        return null
    }



    suspend fun getHumidityList(idAula: String): ArrayList<Humidity>?{
        try {
            val fechaDesde = restarUnMesAFechaActual()
            val fechaHasta = obtenerFechaActual()

            Log.i("ClientServiceImpl", "FECHA DESDE: $fechaDesde")
            Log.i("ClientServiceImpl", "FECHA HASTA: $fechaHasta")

            val call = retrofit.getHumidityList(idAula, fechaDesde, fechaHasta)

            if (call.isSuccessful) {
                return call.body()
            } else {
                val errorBody = call.errorBody()?.string() ?: "Error Desconocido"
                Log.e("ClientServiceImpl", "Error al coger las humedades - Código: ${call.code()}, Mensaje: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("ClientServiceImpl", "Excepción al obtener las humedades: ${e.message}", e)
        }
        return null
    }

    suspend fun getNoiseList(idPasillo: String): ArrayList<Noise>?{
        try {
            val fechaDesde = restarUnMesAFechaActual()
            val fechaHasta = obtenerFechaActual()

            Log.i("ClientServiceImpl", "FECHA DESDE: $fechaDesde")
            Log.i("ClientServiceImpl", "FECHA HASTA: $fechaHasta")

            val call = retrofit.getNoiseList(idPasillo, fechaDesde, fechaHasta)

            if (call.isSuccessful) {
                return call.body()
            } else {
                val errorBody = call.errorBody()?.string() ?: "Error Desconocido"
                Log.e("ClientServiceImpl", "Error al coger el ruido - Código: ${call.code()}, Mensaje: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("ClientServiceImpl", "Excepción al obtener el ruido: ${e.message}", e)
        }
        return null
    }

    suspend fun getIluminationList(idAula: String): ArrayList<Ilumination>?{
        try {
            val fechaDesde = restarUnMesAFechaActual()
            val fechaHasta = obtenerFechaActual()

            Log.i("ClientServiceImpl", "FECHA DESDE: $fechaDesde")
            Log.i("ClientServiceImpl", "FECHA HASTA: $fechaHasta")

            val call = retrofit.getIluminationList(idAula, fechaDesde, fechaHasta)

            if (call.isSuccessful) {
                return call.body()
            } else {
                val errorBody = call.errorBody()?.string() ?: "Error Desconocido"
                Log.e("ClientServiceImpl", "Error al coger la iluminacion - Código: ${call.code()}, Mensaje: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("ClientServiceImpl", "Excepción al obtener la iluminacion: ${e.message}", e)
        }
        return null
    }

    suspend fun getPredictionList(fecha: String): ArrayList<PredictionTemp>?{
        try {
            val call = retrofit.getPredictionsList(fecha)

            if (call.isSuccessful) {
                return call.body()
            } else {
                val errorBody = call.errorBody()?.string() ?: "Error Desconocido"
                Log.e("ClientServiceImpl", "Error al coger la prediccion - Código: ${call.code()}, Mensaje: $errorBody")
            }
        } catch (e: Exception) {
            Log.e("ClientServiceImpl", "Excepción al obtener la prediccion: ${e.message}", e)
        }
        return null
    }


    suspend fun getPasillos(): ArrayList<Hall>?{
        val call = retrofit.getPasillos()

        if (call.isSuccessful){
            return call.body()
        }
        else{
            Log.e("ClientServiceImpl", "Error al coger los pasillos")
        }
        return null
    }

    suspend fun getAulas(): ArrayList<Aula>?{
        val call = retrofit.getAulas()

        if (call.isSuccessful){
            return call.body()
        }
        else{
            Log.e("ClientServiceImpl", "Error al coger las aulas")
        }
        return null
    }


    private fun obtenerFechaActual(): String {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendario = Calendar.getInstance()
        return formato.format(calendario.time)
    }

    private fun restarUnMesAFechaActual(): String {
        val formato = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendario = Calendar.getInstance()

        calendario.add(Calendar.DAY_OF_MONTH, -1)

        return formato.format(calendario.time)
    }
}