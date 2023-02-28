package com.chinh.weather.ui.shop.phone.verify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chinh.weather.R
import com.chinh.weather.databinding.FragmentVerifyPhoneNumberBinding

class VerifyPhoneNumberFragment : Fragment() {

    companion object {
        fun newInstance() = VerifyPhoneNumberFragment()
    }

    private var binding: FragmentVerifyPhoneNumberBinding? = null
    private lateinit var viewModel: VerifyPhoneNumberViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_verify_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVerifyPhoneNumberBinding.bind(view)
        binding?.apply {
            firstPinView.addTextChangedListener {
                if (it?.length == 6) {
                    inputDone = true
                    invalidateAll()
                } else {
                    inputDone = false
                    invalidateAll()
                }
            }
            startCountTime()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    private fun startCountTime(){
        binding?.apply {
            countView.start(60000)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[VerifyPhoneNumberViewModel::class.java]
    }

}