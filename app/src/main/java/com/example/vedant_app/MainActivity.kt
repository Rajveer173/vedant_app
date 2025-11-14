package com.example.vedant_app

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    
    private var currentCityIndex = 0
    
    // Static weather data for Indian cities
    private val cities = listOf(
        WeatherData("New Delhi, India", "29°C", "☀️", "Sunny", "62%", "12 km/h", "24°C / 31°C"),
        WeatherData("Mumbai, India", "32°C", "⛅", "Partly Cloudy", "78%", "8 km/h", "28°C / 34°C"),
        WeatherData("Bangalore, India", "26°C", "🌧️", "Light Rain", "85%", "15 km/h", "22°C / 28°C"),
        WeatherData("Chennai, India", "31°C", "☀️", "Hot", "72%", "6 km/h", "27°C / 33°C"),
        WeatherData("Kolkata, India", "28°C", "🌫️", "Hazy", "68%", "10 km/h", "25°C / 30°C"),
        WeatherData("Jaipur, India", "35°C", "☀️", "Very Hot", "45%", "18 km/h", "29°C / 37°C")
    )
    
    private lateinit var cityText: TextView
    private lateinit var tempText: TextView
    private lateinit var weatherEmoji: TextView
    private lateinit var conditionText: TextView
    private lateinit var humidity: TextView
    private lateinit var wind: TextView
    private lateinit var minmax: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        
        // Initialize views
        cityText = findViewById(R.id.cityText)
        tempText = findViewById(R.id.tempText)
        weatherEmoji = findViewById(R.id.weatherEmoji)
        conditionText = findViewById(R.id.conditionText)
        humidity = findViewById(R.id.humidity)
        wind = findViewById(R.id.wind)
        minmax = findViewById(R.id.minmax)
        
        // Set click listener on main container to cycle cities
        findViewById<androidx.constraintlayout.widget.ConstraintLayout>(R.id.main).setOnClickListener {
            cycleCities()
        }
        
        // Display initial city
        updateWeatherDisplay()
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    
    private fun cycleCities() {
        currentCityIndex = (currentCityIndex + 1) % cities.size
        updateWeatherDisplay()
    }
    
    private fun updateWeatherDisplay() {
        val currentWeather = cities[currentCityIndex]
        
        cityText.text = currentWeather.city
        tempText.text = currentWeather.temperature
        weatherEmoji.text = currentWeather.emoji
        conditionText.text = currentWeather.condition
        humidity.text = "Humidity\n${currentWeather.humidity}"
        wind.text = "Wind\n${currentWeather.wind}"
        minmax.text = "Min/Max\n${currentWeather.minMax}"
    }
    
    data class WeatherData(
        val city: String,
        val temperature: String,
        val emoji: String,
        val condition: String,
        val humidity: String,
        val wind: String,
        val minMax: String
    )
}