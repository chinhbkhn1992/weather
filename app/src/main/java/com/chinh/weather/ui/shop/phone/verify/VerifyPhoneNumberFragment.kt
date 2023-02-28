package com.chinh.weather.ui.shop.phone.verify

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.chinh.weather.R
import com.chinh.weather.databinding.FragmentVerifyPhoneNumberBinding

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding: FragmentVerifyPhoneNumberBinding = FragmentVerifyPhoneNumberBinding.bind(view)
        binding.firstPinView.addTextChangedListener {
            if (it?.length == 6){
                binding.inputDone = true
                binding.invalidateAll()
            }else{
                binding.inputDone = false
                binding.invalidateAll()
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[VerifyPhoneNumberViewModel::class.java]
        // TODO: Use the ViewModel
    }

}