package com.example.aulasinteligentes.controller

import android.content.Intent
import android.content.Context
import com.example.aulasinteligentes.conection.ClientServiceImpl
import com.example.aulasinteligentes.entities.Temperatures
import com.example.aulasinteligentes.entities.User

object ControllerSingleton {
    private val restApiService: ClientServiceImpl = ClientServiceImpl()

    suspend fun validarUsuario(user: User): Boolean {
        return restApiService.validateUser(user)
    }

    suspend fun createUser(user: User): Int{
        return restApiService.createNewUser(user)
    }

    suspend fun getTemperatures(): ArrayList<Temperatures>?{
        return restApiService.getTemperatures()
    }

    suspend fun createNewUserGet(): Int{
        return restApiService.getNewUser()
    }
}