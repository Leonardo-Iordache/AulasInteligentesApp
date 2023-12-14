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
        Log.d("ClientServiceLLL", "Intentando crear la conexión con $serverUrl")

        val call = retrofit.getNewUser()

        if (call.isSuccessful){
            val result = call.body()
            response = result ?: 12
        }
        else{
            Log.d("ClientServiceLLL", "No se ha logrado coger el coso")
        }
        return response
    }


    suspend fun createNewUser(user: User): Int{
        val call = retrofit.createNewUser(user)
        var respuesta = 1
        Log.d("ClientServiceImpl", "Lo consguimos")
        call.enqueue(object : Callback<Int>{
            override fun onResponse(call: Call<Int>, response: Response<Int>) {
                if (response.isSuccessful){
                    respuesta = response.body() ?: 2
                }
                else{
                    Log.e("ClientServiceImpl", "Error al crear usuario")
                }
            }

            override fun onFailure(call: Call<Int>, t: Throwable) {
                Log.e("ClientServiceImpl", "Error de conexion con el servidor")
            }
        })
        return respuesta
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