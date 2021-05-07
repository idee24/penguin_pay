package com.example.penguinpay.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.penguinpay.Constants
import com.example.penguinpay.MainActivity
import com.example.penguinpay.R
import com.example.penguinpay.databinding.FragmentTransactionBinding
import com.example.penguinpay.utils.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *@Created by Yerimah on 5/07/2021.
 */
class TransactionFragment : Fragment(R.layout.fragment_transaction) {

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var countrySheet: BottomSheetDialogFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        countrySheet = CountrySelectSheet(mainActivity, ::updateCountry)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        revalidateListener(binding.firstNameTextField, binding.firstNameLayout)
        revalidateListener(binding.lastNameTextField, binding.lastNameLayout)
        revalidateListener(binding.phoneTextField, binding.phoneLayout)
        initAmountListener(binding.amountTextField, binding.amountLayout)
        binding.countryLayout.setOnClickListener {
            countrySheet.show(childFragmentManager, Constants.COUNTRY_SHEET_KEY)
        }
        binding.sendButton.setOnClickListener {
            if (isValidPayload()) {
              getRates()
            }
        }
    }

    private fun updateCountry(countryCode: String) {
        countrySheet.dismiss()
        val selectedCountry = getCountryByCode(countryCode)
        binding.flagImageView.setImageResource(selectedCountry.image)
        binding.countryNameTextView.text = selectedCountry.countryCode
    }

    private fun getRates() {

    }


    private fun isValidPayload(): Boolean {
        val firstName = isNotEmpty(mainActivity, binding.firstNameTextField, binding.firstNameLayout)
        val lastName = isNotEmpty(mainActivity, binding.lastNameTextField, binding.lastNameLayout)
        val phone = isValidPhoneNumber(mainActivity, binding.phoneTextField, binding.phoneLayout)
        val amount = isValidAmount(mainActivity, binding.amountTextField, binding.amountLayout)
        return firstName && lastName && phone && amount
    }
}