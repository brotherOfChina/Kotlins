package com.example.administrator.kotlin.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.administrator.kotlin.extensions.toDateString
import com.example.administrator.kotlin.R
import com.example.administrator.kotlin.domain.model.Forecast
import com.example.administrator.kotlin.domain.model.ForecastList
import com.example.administrator.kotlin.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*

/**
 * Created by Administrator on 2017/7/21 0021.
 * 列表的adapter
 */
class ForecastListAdapter(val weekForecast: ForecastList, val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount() = weekForecast.size

    class ViewHolder(view: View, val itemClick: (Forecast) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                try {
                    Picasso.with(itemView.ctx).load(iconUrl).into(itemView.iconView)
                    itemView.dateView.text = date.toDateString()
                    itemView.descriptionView.text = description
                    itemView.maxTemperatureView.text = "${high}º"
                    itemView.minTemperatureView.text = "${low}º"
                    itemView.setOnClickListener { itemClick(this) }
                }catch (e:Exception){
                    e.printStackTrace()
                }

            }
        }
    }
}