package com.example.sensors

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import android.hardware.Sensor
import android.hardware.SensorManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var sensorAdapter: SensorAdapter
    private lateinit var sensorManager: SensorManager
    private lateinit var categorySpinner: Spinner
    private lateinit var sensorRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        categorySpinner = findViewById(R.id.categorySpinner)
        sensorRecyclerView = findViewById(R.id.sensorRecyclerView)

        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        val categories = arrayOf(
            "Датчики окружающей среды",
            "Датчики положения устройства",
            "Датчики состояния человека"
        )

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = adapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                updateSensorList(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateSensorList(categoryIndex: Int) {
        val sensors = when (categoryIndex) {
            0 -> sensorManager.getSensorList(Sensor.TYPE_ALL)
                .filter { it.type == Sensor.TYPE_LIGHT || it.type == Sensor.TYPE_TEMPERATURE }
            1 -> sensorManager.getSensorList(Sensor.TYPE_ALL)
                .filter { it.type == Sensor.TYPE_ACCELEROMETER || it.type == Sensor.TYPE_GYROSCOPE }
            else -> sensorManager.getSensorList(Sensor.TYPE_ALL)
                .filter { it.type == Sensor.TYPE_HEART_RATE || it.type == Sensor.TYPE_STEP_COUNTER }
        }.map { it.name }

        sensorAdapter = SensorAdapter(sensors)
        sensorRecyclerView.layoutManager = LinearLayoutManager(this)
        sensorRecyclerView.adapter = sensorAdapter
    }
}
