package com.piusvelte.nwsweather.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.piusvelte.nwsweather.data.gridpoints.model.ForecastPeriod
import com.piusvelte.nwsweather.databinding.ForecastPeriodBinding

internal class ForecastAdapter : ListAdapter<ForecastPeriod, ForecastViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ForecastPeriodBinding.inflate(inflater, parent, false)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        val item = getItem(position)

        with(holder.binding) {
            forecastName.text = item.name
            forecastIcon.loadIcon(item.icon)
            forecastTemperature.text = item.temperature.toString()
            forecastTemperatureUnit.text = item.temperatureUnit.name.first().toString()
            forecastShort.text = item.shortForecast
        }
    }

    private fun ImageView.loadIcon(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<ForecastPeriod>() {
            override fun areItemsTheSame(
                oldItem: ForecastPeriod,
                newItem: ForecastPeriod,
            ): Boolean {
                return oldItem.number == newItem.number
            }

            override fun areContentsTheSame(
                oldItem: ForecastPeriod,
                newItem: ForecastPeriod,
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
