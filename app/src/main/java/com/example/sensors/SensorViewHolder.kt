package com.example.sensors

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SensorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val sensorNameTextView: TextView = itemView.findViewById(R.id.sensorNameTextView)
}
