package com.example.administrator.kotlin.domain.dataSource

import com.example.administrator.kotlin.domain.model.Forecast
import com.example.administrator.kotlin.domain.model.ForecastList


interface ForecastDataSource {

    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?

    fun requestDayForecast(id: Long): Forecast?

}