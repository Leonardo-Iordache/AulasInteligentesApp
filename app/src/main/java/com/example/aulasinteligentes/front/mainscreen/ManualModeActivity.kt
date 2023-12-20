package com.example.aulasinteligentes.front.mainscreen

import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.ManualModeScreenBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ManualModeActivity : AppCompatActivity(){
    private lateinit var windowButton: Button
    private lateinit var blindButton: Button
    private lateinit var acButton: Button
    private lateinit var binding: ManualModeScreenBinding
    private var job: Job = Job()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.manual_mode_screen)

        binding = ManualModeScreenBinding.inflate(layoutInflater)

        binding.let{
            windowButton = it.abrirCerrarVentanaButton
            blindButton = it.abrirCerrarPersianaButton
            acButton = it.onOffACBUTTON
        }

        windowButton.setOnClickListener {
            Log.i("ManualModeButton", "Ventana")
            executeChange(1)
        }

        blindButton.setOnClickListener {
            Log.i("ManualMode", "Persiana")
            executeChange(2)
        }

        acButton.setOnClickListener {
            Log.i("ManualMode", "Aire Acondicionado")
            executeChange(3)
        }
        setContentView(binding.root)
    }

    private fun executeChange(executor: Int){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Ingrese un número")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val spinnerOperation = Spinner(this)
        val spinnerTipoAire = Spinner(this)

        val operaciones = arrayOf("abrir", "cerrar")
        val tiposAire = arrayOf("caliente", "frío")

        val adapterOperation = ArrayAdapter(this, android.R.layout.simple_spinner_item, operaciones)
        val adapterTipoAire = ArrayAdapter(this, android.R.layout.simple_spinner_item, tiposAire)

        spinnerOperation.adapter = adapterOperation
        spinnerTipoAire.adapter = adapterTipoAire

        val inputAulaId = EditText(this)
        inputAulaId.inputType = InputType.TYPE_CLASS_NUMBER
        inputAulaId.hint = "Introduzca Aula o Pasillo"

        layout.addView(inputAulaId)
        layout.addView(spinnerOperation)
        layout.addView(spinnerTipoAire)

        builder.setView(layout)


        builder.setPositiveButton("OK") { dialog, which ->
            val aulaId = inputAulaId.text.toString()
            val open = spinnerOperation.selectedItem.toString() == "abrir" || spinnerOperation.selectedItem.toString() == "encender"
            val tipoAire = spinnerTipoAire.selectedItem.toString() == "caliente"
            Log.i("ManualModeAAA", "Datos: $aulaId, $open")
            runBlocking {
                job = launch {
                    if (executor == 1){
                        ControllerSingleton.changeWindowState(aulaId, open)
                    }
                    else if(executor == 2){
                        ControllerSingleton.changeBlindState(aulaId, open)
                    }
                    else{
                        ControllerSingleton.changeACState(aulaId, open,tipoAire)
                    }
                }
            }
            job.invokeOnCompletion {
                Toast.makeText(this, "Cambiado el estado con éxito", Toast.LENGTH_SHORT).show()
            }

        }

        builder.setNegativeButton("Cancelar") { dialog, which ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }
}