package com.example.aulasinteligentes.conection

import android.util.Log
import com.example.aulasinteligentes.entities.Temperatures
import com.example.aulasinteligentes.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


    suspend fun getTemperatures(): ArrayList<Temperatures>?{
        val call = retrofit.getTemperatures()

        if (call.isSuccessful){
            val temperatures = call.body() ?: return null
            return temperatures
        }
        else {
            Log.e("ClientServiceImpl", "Error al coger las temperaturas")
        }
        return null
    }
}