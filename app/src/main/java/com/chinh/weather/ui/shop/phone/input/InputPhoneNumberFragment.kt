package com.chinh.weather.ui.shop.phone.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.chinh.weather.R
import com.chinh.weather.databinding.FragmentInputPhoneNumberBinding

class InputPhoneNumberFragment : Fragment() {

    companion object {
        fun newInstance() = InputPhoneNumberFragment()
    }

    private lateinit var viewModel: InputPhoneNumberViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_input_phone_number, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentInputPhoneNumberBinding = FragmentInputPhoneNumberBinding.bind(view)
        binding.apply {
            edPhoneNumber.addTextChangedListener {
                if ((it?.length ?: 0) > 0) {
                    binding.inputDone = true
                    binding.invalidateAll()
                } else {
                    binding.inputDone = false
                    binding.invalidateAll()
                }
            }

            btSendCode.setOnClickListener {
                val textPhone = edPhoneNumber.text?.toString()
                val textPhoneLength = textPhone?.length ?: 0
                if (textPhoneLength != 9) {
                    binding.inputError = true
                    binding.invalidateAll()
                }
                viewModel.doSendCode(textPhone)
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[InputPhoneNumberViewModel::class.java]
        // TODO: Use the ViewModel
    }

}