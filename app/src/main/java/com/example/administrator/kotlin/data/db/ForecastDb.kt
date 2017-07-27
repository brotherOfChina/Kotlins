package com.example.administrator.kotlin.data.db

import android.util.Log
import com.antonioleiva.weatherapp.data.db.DbDataMapper
import com.antonioleiva.weatherapp.extensions.toVarargArray
import com.example.administrator.kotlin.domain.dataSource.ForecastDataSource
import com.example.administrator.kotlin.domain.model.ForecastList
import com.example.administrator.kotlin.extensions.byId
import com.example.administrator.kotlin.extensions.clear
import com.example.administrator.kotlin.extensions.parseList
import com.example.administrator.kotlin.extensions.parseOpt
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class ForecastDb(val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 val dataMapper: DbDataMapper = DbDataMapper()) : ForecastDataSource {

    override fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {

        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        Log.i("ForecastDb", ": dailyRequest:"+zipCode.toString()+date.toString());

        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }
        Log.i("ForecastDb", ": dailyForecast:"+dailyForecast);
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt { CityForecast(HashMap(it), dailyForecast) }
        Log.i("ForecastDb", ": city:"+city);
        city?.let { dataMapper.convertToDomain(it) }
    }

    override fun requestDayForecast(id: Long) = forecastDbHelper.use {

        val forecast = select(DayForecastTable.NAME).byId(id).
                parseOpt { DayForecast(HashMap(it)) }
        Log.i("zpj", ":forecast "+forecast+id);
        forecast?.let { dataMapper.convertDayToDomain(it) }
    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            Log.i("ForecastDb", ": saveForecast"+dataMapper.convertFromDomain(forecast).dailyForecast);

            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}
