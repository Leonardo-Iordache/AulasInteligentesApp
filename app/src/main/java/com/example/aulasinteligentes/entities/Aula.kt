package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Aula(
    @SerializedName("id")
    @Expose
    val idAula: String,

    @SerializedName("nombre")
    @Expose
    val nombreAula: String,

    @SerializedName("aforo")
    @Expose
    val aforo: String,

    @SerializedName("id_pasillo")
    @Expose
    val idPasillo: String
)
