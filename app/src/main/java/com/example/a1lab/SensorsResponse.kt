package com.example.a1lab

import com.google.gson.annotations.SerializedName

data class SensorsResponse(
    @SerializedName("fields")
    val fields: List<String>,  // Названия полей ["sensor_index", "latitude", "longitude"]

    @SerializedName("data")
    val data: List<List<Any>>   // Массив данных (список списков)
)

fun SensorsResponse.toSensorList(): List<SensorData> {
    val indexSensor = fields.indexOf("sensor_index")
    val indexLat = fields.indexOf("latitude")
    val indexLon = fields.indexOf("longitude")

    return data.mapNotNull { entry ->
        try {
            val sensorIndex = (entry.getOrNull(indexSensor) as? Number)?.toInt()
            val latitude = (entry.getOrNull(indexLat) as? Number)?.toDouble()
            val longitude = (entry.getOrNull(indexLon) as? Number)?.toDouble()

            if (sensorIndex != null && latitude != null && longitude != null) {
                SensorData(sensorIndex, latitude, longitude)
            } else {
                null // Пропускаем невалидные данные
            }
        } catch (e: Exception) {
            null // Пропускаем ошибочные записи
        }
    }
}




data class SensorData(
    val sensor_index: Int,
    val latitude: Double,
    val longitude: Double
)

