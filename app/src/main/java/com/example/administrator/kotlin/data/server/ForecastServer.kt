package com.example.administrator.kotlin.data.server

import com.antonioleiva.weatherapp.data.server.ForecastByZipCodeRequest
import com.example.administrator.kotlin.data.db.ForecastDb
import com.example.administrator.kotlin.domain.dataSource.ForecastDataSource
import com.example.administrator.kotlin.domain.model.ForecastList


class ForecastServer(val dataMapper: ServerDataMapper = ServerDataMapper(),
                     val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}