package com.example.administrator.kotlin.domain.model

/**
 * Created by Administrator on 2017/7/25 0025.
 */
data class Forecast(val id: Long, val date: Long, val description: String, val high: Int,
                    val low: Int,
                    val iconUrl: String)
data class ForecastList(val id: Long, val city: String, val country: String,
                        val dailyForecast: List<Forecast>) {

    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int) = dailyForecast[position]
}