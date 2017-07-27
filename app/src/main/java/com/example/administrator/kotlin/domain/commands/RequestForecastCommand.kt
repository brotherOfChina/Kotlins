package com.example.administrator.kotlin.domain.commands

import com.example.administrator.kotlin.domain.dataSource.ForecastProvider
import com.example.administrator.kotlin.domain.model.ForecastList


class RequestForecastCommand(
        val zipCode: Long,
        val forecastProvider: ForecastProvider = ForecastProvider()) :
        Command<ForecastList> {

    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)
}