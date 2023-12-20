package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Humidity(
    @SerializedName("id_aula")
    @Expose
    val idAula: String,

    @SerializedName("fecha")
    @Expose
    val date: String,

    @SerializedName("valor")
    @Expose
    val humidityValue: Double
)
