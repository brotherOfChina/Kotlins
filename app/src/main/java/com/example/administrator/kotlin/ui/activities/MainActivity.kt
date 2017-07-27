package com.example.administrator.kotlin.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import com.example.administrator.kotlin.R
import com.example.administrator.kotlin.domain.commands.RequestForecastCommand
import com.example.administrator.kotlin.domain.model.ForecastList
import com.example.administrator.kotlin.extensions.DelegatesExt
import com.example.administrator.kotlin.ui.adapters.ForecastListAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

/**
 * alt+ctrl+shift+k
 */
class MainActivity : AppCompatActivity(), ToorbarManager {
    override val toolbar: Toolbar
        get() = find(R.id.toolbar)
    val zipCode:Long by DelegatesExt.preference(this,SettingActivity.ZIP_CODE,SettingActivity.DEFAULT_ZIP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()


        forecastList.layoutManager = LinearLayoutManager(this)
        attachScroll(forecastList)
//        doAsync {
//            val result = RequestForecastCommand(94043).execute()
//            uiThread {
//                forecastList.adapter = ForecastListAdapter(result) {
////                    startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to result.city)
//                }
////              toolbarTitle="${result.city}(${result.country})"
//            }
//        }
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() = async(UI) {
        val result = bg { RequestForecastCommand(zipCode).execute() }
        updateUI(result.await())
    }
    private fun updateUI(weekForecast: ForecastList) {
        val adapter = ForecastListAdapter(weekForecast) {
            startActivity<DetailActivity>(DetailActivity.ID to it.id, DetailActivity.CITY_NAME to weekForecast.city)
        }
        forecastList.adapter = adapter
        toolbarTitle = "${weekForecast.city} (${weekForecast.country})"
    }


}
