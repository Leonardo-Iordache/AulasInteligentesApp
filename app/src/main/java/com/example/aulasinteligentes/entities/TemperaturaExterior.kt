package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TemperaturaExterior(
    @SerializedName("id_aula")
    @Expose
    val idAula: String,

    @SerializedName("fecha")
    @Expose
    val fecha: String,

    @SerializedName("valor")
    @Expose
    val temperaturaIn: Double,
)
