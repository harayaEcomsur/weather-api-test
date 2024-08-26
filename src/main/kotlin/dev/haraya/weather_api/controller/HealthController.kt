package dev.haraya.weather_api.controller

import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import dev.haraya.weather_api.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController(private val weatherService: WeatherService) {

    @GetMapping("/health")
    fun healthCheck(): ResponseEntity<String> {
        return try {
            weatherService.getWeather("London") // Puedes utilizar una ciudad fija para la verificación.
            ResponseEntity.ok("API externa está funcionando correctamente.")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("API externa no está disponible.")
        }
    }
}
