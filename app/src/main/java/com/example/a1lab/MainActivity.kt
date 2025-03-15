package com.example.a1lab

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.a1lab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AirQualityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = AirQualityRepository(AirQualityApi.RetrofitInstance.api)
        val factory = AirQualityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(AirQualityViewModel::class.java)

        val latitudeInput = findViewById<EditText>(R.id.latitudeInput)
        val longitudeInput = findViewById<EditText>(R.id.longitudeInput)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        searchButton.setOnClickListener {
            val lat = latitudeInput.text.toString().toDoubleOrNull()
            val lon = longitudeInput.text.toString().toDoubleOrNull()

            if (lat != null && lon != null) {
                viewModel.fetchNearestSensor(lat, lon)
            } else {
                resultText.text = "Ошибка: Введите корректные координаты"
            }
        }

        viewModel.airQuality.observe(this, Observer { result ->
            resultText.text = result
        })
        val customClock = findViewById<ClockView>(R.id.customClock)
        val hourInput = findViewById<EditText>(R.id.hourInput)
        val minuteInput = findViewById<EditText>(R.id.minuteInput)
        val setTimeButton = findViewById<Button>(R.id.setTimeButton)

        setTimeButton.setOnClickListener {
            val hour = hourInput.text.toString().toIntOrNull() ?: 0
            val minute = minuteInput.text.toString().toIntOrNull() ?: 0
            customClock.setTime(hour, minute)
        }
    }

}



