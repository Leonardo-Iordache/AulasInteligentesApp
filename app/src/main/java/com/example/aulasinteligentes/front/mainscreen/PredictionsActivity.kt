package com.example.aulasinteligentes.front.mainscreen

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.PredictionScreenBinding
import com.example.aulasinteligentes.entities.PredictionTemp
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class PredictionsActivity : AppCompatActivity() {
    private lateinit var fechaInput: EditText
    private lateinit var searchButton: Button
    lateinit var predictions: ArrayList<PredictionTemp>
    private lateinit var recyclerViewPrediction: RecyclerView
    private lateinit var binding: PredictionScreenBinding
    private var job: Job = Job()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prediction_screen)

        binding = PredictionScreenBinding.inflate(layoutInflater)

        binding.let {
            fechaInput = it.fechaInput
            searchButton = it.button
            recyclerViewPrediction = it.recyclerViewPredicciones
        }
        setContentView(binding.root)
        searchButton.setOnClickListener {
            runBlocking {
                job = launch {
                    predictions = ControllerSingleton.getPredictionList(fechaInput.text.toString().trim())!!
                    //predictions = ControllerSingleton.getPredictionList("2023-12-15 12:00:00")!!
                }
            }
            job.invokeOnCompletion {
                Log.i("Prediccion", "$predictions")
                val adapter = PredictionsAdapter(predictions)
                recyclerViewPrediction.adapter = adapter
                recyclerViewPrediction.layoutManager = LinearLayoutManager(this)
            }
        }


    }

}