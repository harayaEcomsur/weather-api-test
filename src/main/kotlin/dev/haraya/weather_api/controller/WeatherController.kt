package dev.haraya.weather_api.controller

import dev.haraya.weather_api.model.WeatherResponse
import dev.haraya.weather_api.service.WeatherService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController(private val weatherService: WeatherService) {

    @GetMapping("/weather")
    fun getWeather(@RequestParam city: String): ResponseEntity<WeatherResponse> {
        val weather = weatherService.getWeather(city)
        return ResponseEntity.ok(weather)
    }
}