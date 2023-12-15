package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Temperatures(
    @SerializedName("id")
    @Expose
    val idAula: String,

    @SerializedName("fecha")
    @Expose
    val fecha: Timestamp,

    @SerializedName("valorIn")
    @Expose
    val temperaturaIn: Double,

    @SerializedName("valorOut")
    @Expose
    val temperaturaOut: Double
){
    override fun toString(): String {
        return "Temperatures(idAula='$idAula', fecha=$fecha, temperaturaIn=$temperaturaIn, temperaturaOut=$temperaturaOut)"
    }
}
