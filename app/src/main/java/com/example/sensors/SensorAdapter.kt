package com.example.sensors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SensorAdapter(private val sensors: List<String>) :
    RecyclerView.Adapter<SensorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SensorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.sensor_item_layout, parent, false)
        return SensorViewHolder(view)
    }

    override fun onBindViewHolder(holder: SensorViewHolder, position: Int) {
        holder.sensorNameTextView.text = sensors[position]
    }

    override fun getItemCount(): Int {
        return sensors.size
    }
}
