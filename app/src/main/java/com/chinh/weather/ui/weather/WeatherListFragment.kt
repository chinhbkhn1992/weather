package com.chinh.weather.ui.weather

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import com.chinh.weather.R
import com.chinh.weather.data.model.WeatherInfo
import com.chinh.weather.databinding.WeatherListFragmentBinding
import com.chinh.weather.repository.model.ApiResult
import com.chinh.weather.ui.adapter.WeatherListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherListFragment : Fragment(), WeatherListView {
    private lateinit var binding: WeatherListFragmentBinding
    private val adapter by lazy {
        WeatherListAdapter(this::onClickItemNews)
    }

    companion object {
        fun newInstance() = WeatherListFragment()
    }

    private val viewModel: WeatherListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WeatherListFragmentBinding.inflate(inflater, container, false)
        viewModel.view = this
        binding.listNews.apply {
            this.adapter = this@WeatherListFragment.adapter
            itemAnimator = DefaultItemAnimator()

            val horizontalDecoration = DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
            val horizontalDivider =
                ContextCompat.getDrawable(activity!!, R.drawable.horizontal_divider)
            horizontalDecoration.setDrawable(horizontalDivider!!)
            addItemDecoration(horizontalDecoration)
        }
        binding.swRefresh.setOnRefreshListener {
            loadData()
        }
        binding.btSearch.setOnClickListener {
            loadData()
            binding.searchView.clearFocus()
            hideKeyboard(requireActivity())
        }
        return binding.root
    }

    private fun loadData() {
        val query = binding.searchView.text.toString()
        viewModel.loadData(query)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) {
            when (it) {
                is ApiResult.Success -> {
                    handleResult(it.data)
                }
                is ApiResult.Error -> {
                    handleError(it.exception)
                }
            }
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            when (it) {
                true -> showLoading(true)
                else -> showLoading(false)
            }
        }
        loadData()
    }

    override fun showLoading(loading: Boolean) {
        binding.swRefresh.isRefreshing = loading
    }

    override fun handleResult(data: List<WeatherInfo>?) {
        data?.let {
            adapter.setItems(it)
        }
        binding.isEmpty = data.isNullOrEmpty()
    }

    override fun handleError(exception: String?) {
        binding.isEmpty = true
    }


    private fun onClickItemNews(item: WeatherInfo) {
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}