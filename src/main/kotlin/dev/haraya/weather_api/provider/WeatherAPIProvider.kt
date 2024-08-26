package dev.haraya.weather_api.provider

import dev.haraya.weather_api.model.WeatherResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject

@Service
class WeatherAPIProvider(
    @Value("\${weather.apiKey}") private val apiKey: String
) : WeatherProvider {

    private val restTemplate = RestTemplate()

    override fun getWeather(city: String): WeatherResponse {
        // URL de la API que incluye la ciudad y la API key
        val url = "http://api.weatherapi.com/v1/current.json?key=$apiKey&q=$city"

        // Realiza la solicitud GET y mapea la respuesta a un Map<String, Any>
        val jsonResponse = restTemplate.getForObject<Map<String, Any>>(url)

        // Extraer la información relevante del JSON
        val currentWeather = jsonResponse?.get("current") as Map<String, Any>
        val location = jsonResponse["location"] as Map<String, Any>

        return WeatherResponse(
            ciudad = location["name"] as String,
            temperatura = currentWeather["temp_c"] as Double,
            descripcionClima = (currentWeather["condition"] as Map<String, Any>)["text"] as String,
            humedad = currentWeather["humidity"] as Int,
            velocidadViento = currentWeather["wind_kph"] as Double
        )
    }
}