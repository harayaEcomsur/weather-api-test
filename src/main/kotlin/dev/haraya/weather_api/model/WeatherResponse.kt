package dev.haraya.weather_api.model

data class WeatherResponse(
    val ciudad: String,
    val temperatura: Double,
    val descripcionClima: String,
    val humedad: Int,
    val velocidadViento: Double
)