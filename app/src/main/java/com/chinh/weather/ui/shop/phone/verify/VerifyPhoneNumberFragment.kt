package com.chinh.weather.ui.shop.phone.verify

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chinh.weather.R

class VerifyPhoneNumberFragment : Fragment() {

    companion object {
        fun newInstance() = VerifyPhoneNumberFragment()
    }

    private lateinit var viewModel: VerifyPhoneNumberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verify_phone_number, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[VerifyPhoneNumberViewModel::class.java]
        // TODO: Use the ViewModel
    }

}