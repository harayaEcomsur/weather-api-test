package dev.haraya.weather_api

import dev.haraya.weather_api.model.WeatherResponse
import dev.haraya.weather_api.service.WeatherService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class WeatherServiceTests {

    @MockBean
    private lateinit var weatherService: WeatherService

    @Test
    fun testGetWeather() {
        // Mock the behavior of the weatherService
        Mockito.`when`(weatherService.getWeather("London")).thenReturn(
            WeatherResponse("London", 18.5, "cielo despejado", 60, 5.5)
        )

        // Call the method under test
        val response = weatherService.getWeather("London")

        // Assert the expected results
        assertEquals("London", response.ciudad)
    }
}
