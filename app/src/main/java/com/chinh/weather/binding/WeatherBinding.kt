package com.chinh.weather.binding

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.chinh.weather.R
import com.chinh.weather.data.model.WeatherInfo
import java.text.SimpleDateFormat
import java.util.*


@BindingAdapter(value = ["weather_dateTime", "eather_dateTimeFormat"], requireAll = false)
fun TextView.setDateTimeTimeStamp(dateTime: Long?, dateTimeFormat: String? = "EEE, dd MMM yyyy") {
    var textDateTime = ""
    if (dateTime == null) {
        textDateTime = ""
        return
    }
    val format = dateTimeFormat ?: "EEE, dd MMM yyyy"
    textDateTime = try {
        val format = SimpleDateFormat(format)
        val time = Date(dateTime.times(1000))
        format.format(time)
    } catch (e: Exception) {
        ""
    }
    text = resources.getString(R.string.date_weather, textDateTime)
}

@BindingAdapter(value = ["weather_avg_temp"])
fun TextView.setWeatherAvgTemp(dateTime: Double?) {
    var textAvgTemp = ""
    dateTime?.let {
        textAvgTemp = kenvinToCelsius(it)
    }
    text = resources.getString(R.string.avg_temp_weather, textAvgTemp)
}

@BindingAdapter(value = ["weather_pressure"])
fun TextView.setWeatherPressure(pressure: Int?) {
    text = resources.getString(R.string.pressure, pressure ?: "")
}

@BindingAdapter(value = ["weather_humidity"])
fun TextView.setWeatherHumidity(humidity: Int?) {
    text = resources.getString(R.string.humidity, humidity ?: "")
}

@BindingAdapter(value = ["weather_description"])
fun TextView.setWeatherHumidity(item: WeatherInfo?) {
    item?.weather?.get(0)?.description.let {
        text = resources.getString(R.string.description, it ?: "")
    }
}

private fun kenvinToCelsius(it: Double) = String.format("%.1f", it.minus(273.15))
