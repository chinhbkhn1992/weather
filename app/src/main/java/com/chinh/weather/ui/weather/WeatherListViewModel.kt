package com.chinh.weather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chinh.weather.data.model.WeatherInfo
import com.chinh.weather.repository.WeatherRepository
import com.chinh.weather.repository.model.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(val repo: WeatherRepository) : ViewModel() {
    var view: WeatherListView? = null
    val data = MutableLiveData<ApiResult<List<WeatherInfo>>>()
    val loading = MutableLiveData<Boolean>()
    fun loadData(query: String) {
        viewModelScope.launch {
            repo.getWeather(query)
                .catch { exception ->
                    data.value = ApiResult.Error(exception.message, exception)
                }
                .onStart {
                    loading.value = true
                }
                .onCompletion {
                    loading.value = false
                }
                .collect { apiResult ->
                    data.value = apiResult
                }
        }
    }
}