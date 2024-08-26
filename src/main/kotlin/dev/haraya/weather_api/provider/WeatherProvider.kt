package dev.haraya.weather_api.provider
import dev.haraya.weather_api.model.WeatherResponse


interface WeatherProvider {
    fun getWeather(city: String): WeatherResponse
}