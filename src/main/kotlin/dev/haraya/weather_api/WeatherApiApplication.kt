package dev.haraya.weather_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class WeatherApiApplication

fun main(args: Array<String>) {
	runApplication<WeatherApiApplication>(*args)
}
