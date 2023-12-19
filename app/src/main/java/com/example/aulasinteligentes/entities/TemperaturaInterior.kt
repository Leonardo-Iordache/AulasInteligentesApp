package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class TemperaturaInterior(
    @SerializedName("id_aula")
    @Expose
    val idAula: String,

    @SerializedName("fecha")
    @Expose
    val fecha: String,

    @SerializedName("valor")
    @Expose
    val temperaturaIn: Double,

){
    override fun toString(): String {
        return "Temperatures(idAula='$idAula', fecha=$fecha, temperaturaIn=$temperaturaIn)"
    }
}
