package com.chinh.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.chinh.weather.R
import com.chinh.weather.databinding.ActivityWeatherBinding
import com.scottyab.rootbeer.RootBeer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityWeatherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (RootBeer(this).isRooted) {
            throw Exception("Rooted not accept")
        }
        val binding =
            DataBindingUtil.setContentView<ActivityWeatherBinding>(this, R.layout.activity_weather)
    }
}