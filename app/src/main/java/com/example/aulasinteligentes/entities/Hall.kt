package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hall(
    @SerializedName("id")
    @Expose
    val idPasillo: String,

    @SerializedName("num_aulas")
    @Expose
    val nAulas: Int,

    @SerializedName("id_zona")
    @Expose
    val idZona: String

)
