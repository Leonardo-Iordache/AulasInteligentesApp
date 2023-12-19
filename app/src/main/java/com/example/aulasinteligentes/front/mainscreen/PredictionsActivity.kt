package com.example.aulasinteligentes.front.mainscreen

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.evrencoskun.tableview.TableView
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.sort.SortState
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.PredictionScreenBinding
import com.example.aulasinteligentes.entities.PredictionTemp
import com.example.aulasinteligentes.entities.TemperaturaInterior
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class PredictionsActivity : AppCompatActivity() {
    private lateinit var tableView: TableView
    private lateinit var fechaInput: EditText
    private lateinit var searchButton: Button
    private lateinit var tableAdapter: PredictionsAdapter

    private lateinit var binding: PredictionScreenBinding
    private var job: Job = Job()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prediction_screen)

        binding = PredictionScreenBinding.inflate(layoutInflater)

        binding.let {
            //tableView = it.tableView
            fechaInput = it.fechaInput
            searchButton = it.button
        }
        setContentView(binding.root)
        //initializeTableView()
        var res = ArrayList<PredictionTemp>()
        searchButton.setOnClickListener {
            runBlocking {
                job = launch {
                    //loadData();
                    //res = ControllerSingleton.getPredictionList(fechaInput.text.toString().trim())!!
                    res = ControllerSingleton.getPredictionList("2023-12-15 12:00:00")!!
                }
            }
            job.invokeOnCompletion {
                Log.e("Prediccion", "$res")
            }
        }


    }

    /*private fun initializeTableView() {
        tableAdapter = PredictionsAdapter(this)
        tableView.setAdapter(tableAdapter as AbstractTableAdapter<Any, Any, Any>)
    }


    private suspend fun loadData() {
        val tableData = ControllerSingleton.getPredictionList(fechaInput.text.toString().trim())

        tableAdapter.setAllItems(tableData)
    }*/


}