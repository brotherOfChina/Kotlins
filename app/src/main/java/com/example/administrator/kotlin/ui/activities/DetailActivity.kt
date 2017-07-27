package com.example.administrator.kotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.widget.TextView
import com.example.administrator.kotlin.domain.commands.RequestDayForecastCommand
import com.example.administrator.kotlin.R
import com.example.administrator.kotlin.domain.model.Forecast
import com.example.administrator.kotlin.extensions.color
import com.example.administrator.kotlin.extensions.textColor
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.ctx
import org.jetbrains.anko.find
import java.text.DateFormat
import java.util.*

class DetailActivity : AppCompatActivity() ,ToorbarManager{
    override val toolbar: Toolbar
        get() = find(R.id.toolbar) //To change initializer of created properties use File | Settings | File Templates.

    companion object {
        val ID="DetailActivity:id"
        val CITY_NAME="DetailActivity:cityName"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolBar()
        toolbarTitle=intent.getStringExtra(CITY_NAME)
        enableHomeAsUp { onBackPressed() }
        async(UI) {
            val result = bg { RequestDayForecastCommand(intent.getLongExtra(ID, -1)).execute() }
            bindForecast(result.await())
        }
    }

    private fun bindForecast(result: Forecast)= with(result) {
        Log.i("DetailActivity", ": bindForecast"+result);
        Picasso.with(ctx).load(iconUrl).into(icon)
        supportActionBar?.subtitle=date.toDateString(DateFormat.FULL)
        weatherDescription.text=description
        bindWeather(high to maxTemperature , low to minTemperature)
    }
    fun Long.toDateString(dateFormat:Int=DateFormat.MEDIUM):String{
        val df=DateFormat.getDateInstance(dateFormat, Locale.getDefault())
        return df.format(this)
    }
    private fun bindWeather(vararg views:Pair<Int,TextView>)=views.forEach {
        it.second.text="${it.first}"
        it.second.textColor=color(when(it.first){
            in -50..0 -> android.R.color.holo_red_dark
            in 0..12->android.R.color.holo_orange_dark
            else ->android.R.color.holo_green_dark
        })
    }
}
