package com.example.aulasinteligentes.front.mainscreen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.aulasinteligentes.R
import com.example.aulasinteligentes.controller.ControllerSingleton
import com.example.aulasinteligentes.databinding.TemperaturesGraphScreenBinding
import com.example.aulasinteligentes.entities.Humidity
import com.example.aulasinteligentes.entities.Ilumination
import com.example.aulasinteligentes.entities.Noise
import com.example.aulasinteligentes.entities.TemperaturaExterior
import com.example.aulasinteligentes.entities.TemperaturaInterior
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


class ChartActivity : AppCompatActivity() {
    private lateinit var lineChart: LineChart
    private lateinit var spinnerSelectData: Spinner
    private lateinit var idText: EditText
    private lateinit var searchButton: Button

    private var noiseList: ArrayList<Noise>? = null
    private var temperaturaInteriorList: ArrayList<TemperaturaInterior>? = null
    private var temperaturaExteriorList: ArrayList<TemperaturaExterior>? = null
    private var iluminationList: ArrayList<Ilumination>? = null
    private var humidityList: ArrayList<Humidity>? = null

    private lateinit var binding: TemperaturesGraphScreenBinding
    private var job: Job = Job()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.temperatures_graph_screen)

        binding = TemperaturesGraphScreenBinding.inflate(layoutInflater)

        binding.let {
            lineChart = it.chart
            spinnerSelectData = it.tipoDatoSpinner
            idText = it.seleccion
            searchButton = it.searchButton
        }
        setContentView(binding.root)


        Log.i("TemperaturesChart", "Guardadas las temperaturas correctamente")
        val datos = arrayListOf("Temperatura", "Humedad", "Ruido", "Iluminación")

        val adapterData = ArrayAdapter(this, android.R.layout.simple_spinner_item, datos)
        adapterData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerSelectData.adapter = adapterData

        searchButton.setOnClickListener {
            val data = spinnerSelectData.selectedItem.toString()
            if (data == "Temperatura"){
                runBlocking {
                    job = launch {
                        Log.i("TemperaturesChart", "Temperatura Request: ${idText.text.toString().trim()}")
                        temperaturaInteriorList = ControllerSingleton.getTemperaturaInterior(idText.text.toString().trim())
                        temperaturaExteriorList = ControllerSingleton.getTemperaturaExterior(idText.text.toString().trim())
                    }
                }
                job.invokeOnCompletion {
                    Log.i("TemperaturesChart", "Temperatura Request: $temperaturaInteriorList")
                    temperaturaInteriorList?.let { it1 -> temperaturaExteriorList?.let { it2 ->
                        setTemperatureData(it1,
                            it2
                        )
                    } }
                }
            }
            else if (data == "Humedad"){
                runBlocking {
                    job = launch {
                        humidityList = ControllerSingleton.getHumidityList(idText.text.toString().trim())
                    }
                }
                job.invokeOnCompletion {
                    humidityList?.let { it1 -> setHumidityData(it1) }
                }
            }
            else if (data == "Ruido"){
                runBlocking {
                    job = launch {
                        noiseList = ControllerSingleton.getNoiseList(idText.text.toString().trim())
                    }
                }
                job.invokeOnCompletion {
                    Log.i("ChartActtt", "Conseguido $noiseList")
                    noiseList?.let { it1 -> setNoiseData(it1) }
                }
            }
            else if (data == "Iluminación"){
                runBlocking {
                    job = launch {
                        iluminationList = ControllerSingleton.getIluminationList(idText.text.toString().trim())
                    }
                }
                job.invokeOnCompletion {
                    Log.i("ChartActtt", "Conseguido $iluminationList")
                    iluminationList?.let { it1 -> setIluminationData(it1) }
                }
            }
        }

        setupChart()
    }

    private fun setupChart() {
        lineChart.apply {
            setNoDataText("No hay datos disponibles")
            description.isEnabled = false
            setDrawGridBackground(false)
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            legend.textColor = Color.WHITE

            val xAxis = xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return temperaturaInteriorList?.getOrNull(value.toInt())?.fecha ?: ""
                }
            }

            axisLeft.textColor = Color.WHITE
            axisRight.isEnabled = false
        }
    }


    //MOSTRAR TEMPERATURAS
    private fun setTemperatureData(temperaturaIn: List<TemperaturaInterior>, temperaturaOut: List<TemperaturaExterior>) {
        val exteriorEntries = ArrayList<Entry>()
        val interiorEntries = ArrayList<Entry>()

        temperaturaIn.forEachIndexed { index, temp ->
            interiorEntries.add(Entry(index.toFloat(), temp.temperaturaIn.toFloat()))
        }

        temperaturaOut.forEachIndexed { index, temp ->
            exteriorEntries.add(Entry(index.toFloat(), temp.temperaturaIn.toFloat()))
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



    //MOSTRAR HUMEDAD
    private fun setHumidityData(humidityList: List<Humidity>) {
        val humidityEntry = ArrayList<Entry>()

        humidityList.forEachIndexed { index, temp ->
            humidityEntry.add(Entry(index.toFloat(), temp.humidityValue.toFloat()))
        }

        val humidityDataSet = LineDataSet(humidityEntry, "Humedad")
        humidityDataSet.color = Color.BLUE
        humidityDataSet.valueTextColor = Color.BLUE

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(humidityDataSet)

        val data = LineData(dataSets)
        lineChart.data = data
        lineChart.invalidate()
    }


    //MOSTRAR RUIDO
    private fun setNoiseData(noiseList: List<Noise>) {
        val noiseEntry = ArrayList<Entry>()

        noiseList.forEachIndexed { index, temp ->
            noiseEntry.add(Entry(index.toFloat(), temp.nivelRuido.toFloat()))
        }

        val noiseDataSet = LineDataSet(noiseEntry, "Nivel de Ruido")
        noiseDataSet.color = Color.BLUE
        noiseDataSet.valueTextColor = Color.BLUE

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(noiseDataSet)

        val data = LineData(dataSets)
        lineChart.data = data
        lineChart.invalidate()
    }


    //MOSTRAR ILUMINACION
    private fun setIluminationData(iluminationList: List<Ilumination>) {
        val iluminationEntry = ArrayList<Entry>()

        iluminationList.forEachIndexed { index, temp ->
            iluminationEntry.add(Entry(index.toFloat(), temp.iluminationValue.toFloat()))
        }

        val iluminationDataSet = LineDataSet(iluminationEntry, "Iluminación")
        iluminationDataSet.color = Color.YELLOW
        iluminationDataSet.valueTextColor = Color.YELLOW

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(iluminationDataSet)

        val data = LineData(dataSets)
        lineChart.data = data
        lineChart.invalidate()
    }

}