package com.chinh.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.chinh.weather.data.model.WeatherInfo
import com.chinh.weather.repository.WeatherRepository
import com.chinh.weather.repository.model.ApiResult
import com.chinh.weather.ui.weather.WeatherListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(value = MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class WeatherListViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var viewModel: WeatherListViewModel
    val dispatcher = TestCoroutineDispatcher()

    @Mock
    lateinit var repo: WeatherRepository

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = WeatherListViewModel(repo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun call_api_emptyList() = runBlockingTest {
        val flow = MutableSharedFlow<ApiResult<List<WeatherInfo>>>()
        `when`(repo.getWeather("saigon")).thenReturn(flow)
        viewModel.loadData("saigon")
        MatcherAssert.assertThat(viewModel.loading.value, CoreMatchers.`is`(true))
        flow.emit(ApiResult.Success(emptyList()))
        MatcherAssert.assertThat(viewModel.data.value is ApiResult.Success, CoreMatchers.`is`(true))
        MatcherAssert.assertThat(viewModel.data.value?.data, CoreMatchers.`is`(emptyList()))
    }

    @Test
    fun call_api_exception() = runBlockingTest {
        `when`(repo.getWeather("saigon")).thenReturn(flow {
            throw Exception("Api Exception")
        })
        viewModel.loadData("saigon")
        MatcherAssert.assertThat(viewModel.data.value is ApiResult.Error, CoreMatchers.`is`(true))
    }
}