package com.example.aulasinteligentes.front.mainscreen

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.databinding.MainMenuBinding
class MainMenuActivity : AppCompatActivity() {
    private lateinit var consultarDatos: Button
    private lateinit var prediccionButton: Button
    private lateinit var binding: MainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu)

        binding = MainMenuBinding.inflate(layoutInflater)

        binding.let {
            consultarDatos = it.consultarDatosButton
            prediccionButton = it.prediccionButton
        }

        consultarDatos.setOnClickListener {
            Log.d("MainMenuActivity", "CONSULTAR DATOS")
        }

        prediccionButton.setOnClickListener {
            Log.d("MainMenuActivity", "CONSULTAR PREDICCIONES")
        }


        setContentView(binding.root)
    }

}