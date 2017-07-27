package com.example.administrator.kotlin.domain.commands

import com.example.administrator.kotlin.domain.commands.Command
import com.example.administrator.kotlin.domain.dataSource.ForecastProvider
import com.example.administrator.kotlin.domain.model.Forecast



class RequestDayForecastCommand(
        val id: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<Forecast> {

    override fun execute() = forecastProvider.requestForecast(id)
}