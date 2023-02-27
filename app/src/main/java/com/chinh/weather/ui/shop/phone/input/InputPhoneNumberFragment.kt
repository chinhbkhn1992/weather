package com.chinh.weather.ui.shop.phone.input

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chinh.weather.R

class InputPhoneNumberFragment : Fragment() {

    companion object {
        fun newInstance() = InputPhoneNumberFragment()
    }

    private lateinit var viewModel: InputPhoneNumberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_phone_number, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(InputPhoneNumberViewModel::class.java)
        // TODO: Use the ViewModel
    }

}