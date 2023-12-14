package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("usuario")
    @Expose
    val userName: String,

    @SerializedName("pass")
    @Expose
    val userPassword: String,

    @SerializedName("email")
    @Expose
    val userEmail: String
){
    override fun toString(): String {
        return "User(userName='$userName', userPassword='$userPassword', userEmail='$userEmail')"
    }
}
