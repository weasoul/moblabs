package com.example.a1lab

import com.google.gson.annotations.SerializedName

data class AirQualityResponse(
    val sensor: AirQualityData
)

data class AirQualityData(
    @SerializedName("pm2.5_alt") val pm2_5: Double,
    val latitude: Double,
    val longitude: Double
)

