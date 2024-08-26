package dev.haraya.weather_api.provider

import dev.haraya.weather_api.model.WeatherResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class OpenWeatherMapProvider(
    @Value("\${weather.apiKey}") private val apiKey: String
) : WeatherProvider {

    private val restTemplate = RestTemplate()

    override fun getWeather(city: String): WeatherResponse {
        val url = "http://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apiKey&units=metric&lang=es"
        val jsonResponse = restTemplate.getForObject<Map<String, Any>>(url)

        return WeatherResponse(
            ciudad = jsonResponse?.get("name") as String,
            temperatura = (jsonResponse["main"] as Map<String, Any>)["temp"] as Double,
            descripcionClima = ((jsonResponse["weather"] as List<Map<String, Any>>)[0])["description"] as String,
            humedad = (jsonResponse["main"] as Map<String, Any>)["humidity"] as Int,
            velocidadViento = (jsonResponse["wind"] as Map<String, Any>)["speed"] as Double
        )
    }
}