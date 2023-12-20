package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PredictionTemp(
    @SerializedName("idAula")
    @Expose
    val idAula: String,

    @SerializedName("fecha")
    @Expose
    val fechaPrediccion: String,

    @SerializedName("valor")
    @Expose
    val valorPrediccion: Double
)
