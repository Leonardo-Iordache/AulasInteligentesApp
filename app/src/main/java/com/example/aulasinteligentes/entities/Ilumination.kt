package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Ilumination(
    @SerializedName("id_aula")
    @Expose
    val idAula: String,

    @SerializedName("fecha")
    @Expose
    val date: Timestamp,

    @SerializedName("valor")
    @Expose
    val iluminationValue: Double
)
