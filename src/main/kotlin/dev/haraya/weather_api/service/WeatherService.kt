package dev.haraya.weather_api.service

import dev.haraya.weather_api.provider.OpenWeatherMapProvider
import dev.haraya.weather_api.provider.WeatherAPIProvider
import dev.haraya.weather_api.provider.WeatherProvider
import dev.haraya.weather_api.model.WeatherResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.cache.annotation.Cacheable

@Service
class WeatherService(
    @Value("\${weather.provider}") private val providerName: String,
    @Value("\${weather.apiKey}") private val apiKey: String
) {

    fun getProvider(): WeatherProvider {
        return when (providerName.lowercase()) {
            "openweathermap" -> OpenWeatherMapProvider(apiKey)
            "weatherapi" -> WeatherAPIProvider(apiKey)
            else -> throw IllegalArgumentException("Proveedor no soportado: $providerName")
        }
    }

    @Cacheable(value = ["weatherCache"], key = "#city", unless = "#result == null")
    fun getWeather(city: String): WeatherResponse {
        val provider = getProvider()
        return provider.getWeather(city)
    }
}
