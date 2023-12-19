package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PredictionTemp(
    @SerializedName("idAula")
    @Expose
    private val idAula: String,

    @SerializedName("valor")
    @Expose
    private val valorPrediccion: Double
)
