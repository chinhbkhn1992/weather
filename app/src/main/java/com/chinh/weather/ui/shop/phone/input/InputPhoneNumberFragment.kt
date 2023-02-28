package com.chinh.weather.ui.shop.phone.input

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
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
                if ((it?.length ?: 0) > 0){
                    binding.inputDone = true
                    binding.invalidateAll()
                }else{
                    binding.inputDone = false
                    binding.invalidateAll()
                }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[InputPhoneNumberViewModel::class.java]
        // TODO: Use the ViewModel
    }

}