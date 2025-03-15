package com.example.a1lab

import kotlin.math.abs




class AirQualityRepository(private val api: AirQualityApi) {
    suspend fun getNearestSensor(apiKey: String, lat: Double, lon: Double): Int? {
        val sensorsResponse = api.getSensors(apiKey, lat + 1, lon - 1, lat - 1, lon + 1)

        val sensors = sensorsResponse.toSensorList()

        return sensors.minByOrNull {
            abs(it.latitude - lat) + abs(it.longitude - lon)
        }?.sensor_index
    }


    suspend fun getAirQuality(sensorIndex: Int, apiKey: String): AirQualityResponse {
        return api.getAirQuality(sensorIndex, apiKey)
    }
}

