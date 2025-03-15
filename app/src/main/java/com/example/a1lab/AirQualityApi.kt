package com.example.a1lab

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query



interface AirQualityApi {
    @GET("sensors?fields=sensor_index,latitude,longitude")
    suspend fun getSensors(
        @Query("api_key") apiKey: String,
        @Query("nwlat") nwlat: Double,
        @Query("nwlng") nwlng: Double,
        @Query("selat") selat: Double,
        @Query("selng") selng: Double
    ): SensorsResponse


    @GET("sensors/{sensor_index}?fields=pm2.5_alt,latitude,longitude")
    suspend fun getAirQuality(
        @Path("sensor_index") sensorIndex: Int,
        @Query("api_key") apiKey: String
    ): AirQualityResponse

    companion object RetrofitInstance {
        private const val BASE_URL = "https://api.purpleair.com/v1/"

        fun create(): AirQualityApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AirQualityApi::class.java)
        }
        val api: AirQualityApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AirQualityApi::class.java)
        }
    }
}

