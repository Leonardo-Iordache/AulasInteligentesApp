package com.example.aulasinteligentes.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class Noise(
    @SerializedName("id_pasillo")
    @Expose
    val idPasillo: String,

    @SerializedName("fecha")
    @Expose
    val fecha: Timestamp,

    @SerializedName("valor")
    val nivelRuido: Double
){

    override fun toString(): String {
        return "Noise(idPasillo='$idPasillo', fecha=$fecha, nivelRuido=$nivelRuido)"
    }
}
