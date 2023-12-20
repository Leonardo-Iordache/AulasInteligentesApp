package com.example.aulasinteligentes.front.mainscreen

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.databinding.MainMenuBinding
class MainMenuActivity : AppCompatActivity() {
    private lateinit var consultarDatos: Button
    private lateinit var prediccionButton: Button
    private lateinit var cambiarModoButton: Button
    private lateinit var binding: MainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        binding = MainMenuBinding.inflate(layoutInflater)

        binding.let {
            consultarDatos = it.consultarDatosButton
            prediccionButton = it.prediccionButton
            cambiarModoButton = it.cambiarModoButton
        }

        consultarDatos.setOnClickListener {
            Log.i("MainMenuActivity", "CONSULTAR DATOS")
            showTemperaturesScreen()
        }

        prediccionButton.setOnClickListener {
            Log.i("MainMenuActivity", "CONSULTAR PREDICCIONES")
            showPredictionsScreen()
        }

        cambiarModoButton.setOnClickListener {
            Log.d("MainMenuActivity", "CAMBIAR MODO")
            switchExecutionMode()
        }


        setContentView(binding.root)
    }


    private fun showTemperaturesScreen(){
        val intent = Intent(this, ChartActivity::class.java)
        startActivity(intent)
    }

    private fun showPredictionsScreen(){
        val intent = Intent(this, PredictionsActivity::class.java)
        startActivity(intent)
    }

    private fun switchExecutionMode(){
        val intent = Intent(this, ManualModeActivity::class.java)
        startActivity(intent)
    }
}