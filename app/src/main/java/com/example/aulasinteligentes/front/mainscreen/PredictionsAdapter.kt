package com.example.aulasinteligentes.front.mainscreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.entities.PredictionTemp

class PredictionsAdapter(private val predictionsList: ArrayList<PredictionTemp>) :
    RecyclerView.Adapter<PredictionsAdapter.PredictionsActivityViewHolder>() {

    class PredictionsActivityViewHolder(itemView: View) : ViewHolder(itemView) {
        val idAula = itemView.findViewById<TextView>(R.id.idAulaText)
        val fechaPrediccion = itemView.findViewById<TextView>(R.id.fechaText)
        val valorPrediccion = itemView.findViewById<TextView>(R.id.valorText)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PredictionsAdapter.PredictionsActivityViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val predictionView = inflater.inflate(R.layout.prediccion_item, parent, false)
        return PredictionsActivityViewHolder(predictionView)
    }

    override fun onBindViewHolder(holder: PredictionsActivityViewHolder, position: Int) {
        val prediction = predictionsList[position]
        val textViewIdAula = holder.idAula
        val textViewFechaPrediccion = holder.fechaPrediccion
        val textViewValorPrediccion = holder.valorPrediccion

        textViewIdAula.text = "Aula: " + prediction.idAula
        textViewFechaPrediccion.text = "Fecha: " + prediction.fechaPrediccion
        textViewValorPrediccion.text = "Temperatura: " + prediction.valorPrediccion.toString()
    }

    override fun getItemCount(): Int {
        return predictionsList.size
    }

}