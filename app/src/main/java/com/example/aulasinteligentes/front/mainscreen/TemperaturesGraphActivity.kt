package com.example.aulasinteligentes.front.mainscreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.TemperaturesGraphScreenBinding
import com.example.aulasinteligentes.entities.Temperatures
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class TemperaturesGraphActivity : AppCompatActivity() {
    private lateinit var lineChart: LineChart
    private lateinit var spinnerTemperatureType: Spinner
    private var temperaturesList: ArrayList<Temperatures>? = null // Tu lista de temperaturas
    private lateinit var binding: TemperaturesGraphScreenBinding
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperatures_graph_screen)

        binding = TemperaturesGraphScreenBinding.inflate(layoutInflater)

        binding.let {
            lineChart = it.chart
            spinnerTemperatureType = it.spinner
        }

        runBlocking {
            job = launch {
                //temperaturesList = getTemperatures()
                temperaturesList = arrayListOf(
                    Temperatures(
                        "1",
                        ControllerSingleton.parseDate("2023-12-12 14:00"),
                        25.0,
                        15.0
                    ),
                    Temperatures(
                        "1",
                        ControllerSingleton.parseDate("2023-12-12 15:00"),
                        24.0,
                        10.0
                    ),
                    Temperatures(
                        "1",
                        ControllerSingleton.parseDate("2023-12-12 16:00"),
                        22.0,
                        12.0
                    ),
                    Temperatures(
                        "1",
                        ControllerSingleton.parseDate("2023-12-12 17:00"),
                        24.0,
                        13.0
                    ),
                    Temperatures(
                        "1",
                        ControllerSingleton.parseDate("2023-12-12 18:00"),
                        25.0,
                        15.0
                    ),
                    Temperatures(
                        "1",
                        ControllerSingleton.parseDate("2023-12-12 19:00"),
                        25.0,
                        12.0
                    ),
                    Temperatures("2", ControllerSingleton.parseDate("2023-12-12 14:00"), 20.0, 1.0),
                    Temperatures(
                        "2",
                        ControllerSingleton.parseDate("2023-12-12 15:00"),
                        21.0,
                        17.0
                    ),
                    Temperatures(
                        "2",
                        ControllerSingleton.parseDate("2023-12-12 16:00"),
                        15.0,
                        14.0
                    ),
                    Temperatures(
                        "2",
                        ControllerSingleton.parseDate("2023-12-12 17:00"),
                        14.0,
                        12.0
                    ),
                    Temperatures("2", ControllerSingleton.parseDate("2023-12-12 18:00"), 21.0, 5.0),
                    Temperatures("2", ControllerSingleton.parseDate("2023-12-12 19:00"), 18.0, 0.0)
                )
            }
        }

        job.invokeOnCompletion {
            Log.i("TemperaturesChart", "Guardadas las temperaturas correctamente")
            val aulas = temperaturesList?.map { it.idAula }?.distinct() ?: emptyList()

            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, aulas)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTemperatureType.adapter = adapter

            spinnerTemperatureType.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        val selectedAula = parent?.getItemAtPosition(position).toString()
                        val filteredTemperatures =
                            temperaturesList?.filter { it.idAula == selectedAula }

                        filteredTemperatures?.let {
                            setData(it)
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Acciones cuando no se selecciona nada en el Spinner (si es necesario)
                    }
                }

            setupChart()
        }
        setContentView(binding.root)

    }

    private fun setupChart() {
        lineChart.apply {
            setNoDataText("No hay datos disponibles")
            description.isEnabled = false
            setDrawGridBackground(false)
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            legend.textColor = Color.BLACK

            val xAxis = xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return temperaturesList?.getOrNull(value.toInt())?.fecha?.toString() ?: ""
                }
            }

            axisLeft.textColor = Color.BLACK
            axisRight.isEnabled = false
        }
    }


    private fun setData(temperatures: List<Temperatures>) {
        val exteriorEntries = ArrayList<Entry>()
        val interiorEntries = ArrayList<Entry>()

        temperatures.forEachIndexed { index, temp ->
            exteriorEntries.add(Entry(index.toFloat(), temp.temperaturaIn.toFloat()))
            interiorEntries.add(Entry(index.toFloat(), temp.temperaturaOut.toFloat()))
        }

        val exteriorDataSet = LineDataSet(exteriorEntries, "Exterior")
        exteriorDataSet.color = Color.BLUE
        exteriorDataSet.valueTextColor = Color.BLUE

        val interiorDataSet = LineDataSet(interiorEntries, "Interior")
        interiorDataSet.color = Color.RED
        interiorDataSet.valueTextColor = Color.RED

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(exteriorDataSet)
        dataSets.add(interiorDataSet)

        val data = LineData(dataSets)
        lineChart.data = data
        lineChart.invalidate()
    }


    private suspend fun getTemperatures(): ArrayList<Temperatures>? {
        return ControllerSingleton.getTemperatures()
    }

}