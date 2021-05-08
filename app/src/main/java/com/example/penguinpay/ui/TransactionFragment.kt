package com.example.penguinpay.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.penguinpay.Constants
import com.example.penguinpay.MainActivity
import com.example.penguinpay.R
import com.example.penguinpay.databinding.FragmentTransactionBinding
import com.example.penguinpay.networking.*
import com.example.penguinpay.utils.*
import com.example.penguinpay.viewmodels.MainViewModel
import com.example.penguinpay.viewmodels.MainViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


/**
 *@Created by Yerimah on 5/07/2021.
 */
class TransactionFragment : Fragment(R.layout.fragment_transaction) {

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var viewModel: MainViewModel
    private lateinit var countrySheet: BottomSheetDialogFragment
    var selectedCountryCode = "NGN"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        initViewModel()
    }

    private fun initViewModel() {
        val factory = MainViewModelFactory(ApiClient.apiClient().create(ApiService::class.java))
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTransactionBinding.bind(view)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        countrySheet = CountrySelectSheet(mainActivity, viewModel.getCountries(), ::updateCountry)
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
        val selectedCountry = viewModel.getCountryByCode(countryCode)
        binding.flagImageView.setImageResource(selectedCountry.image)
        binding.countryNameTextView.text = selectedCountry.countryCode
        selectedCountryCode = countryCode
    }

    private fun getRates() {
        val decimalAmount = Integer.parseInt(binding.amountTextField.text.toString().trim(), 2).toDouble()

        viewModel.getLatestRates().observe(viewLifecycleOwner, Observer {
            it.let { resource ->
                when(resource.status) {
                    Status.SUCCESS -> {
                        showLoader(false, binding.appLoader, mainActivity)

                        if (resource.data?.rates != null) {
                            val amount = decimalAmount.times(getExchangeRate(resource.data.rates!!) ?: 0.0)
                            val binaryAmount = amount.toUInt().toString(radix = 2)
                            val displayAmount = "$selectedCountryCode$binaryAmount"
                            val message = "${binding.firstNameTextField.text.trim()} ${binding.lastNameTextField.text.trim()} " +
                                    "will receive an amount of \n$displayAmount.\n\n Will you like to proceed?"
                            FeedbackDialog(mainActivity, message, getString(R.string.proceed), ::successCallBack).show()
                        }
                        else {
                            FeedbackDialog(mainActivity, getString(R.string.exchange_error_message),
                                    getString(R.string.dismiss), ::errorCallBack).show()
                        }
                    }
                    Status.ERROR -> {
                        showLoader(false, binding.appLoader, mainActivity)
                        FeedbackDialog(mainActivity, getString(R.string.exchange_error_message),
                                getString(R.string.dismiss), ::errorCallBack).show()
                    }
                    Status.LOADING -> {
                        showLoader(true, binding.appLoader, mainActivity)
                    }
                }
            }
        })
    }

    private fun successCallBack() {
        findNavController().navigate(R.id.action_transactionFragment_to_successFragment)
    }

    private fun getExchangeRate(rates: Rates): Double? {
        return when (selectedCountryCode) {
            Constants.CURRENCY_CODE_NIGERIA -> rates.NGN
            Constants.CURRENCY_CODE_UGANDA -> rates.UGX
            Constants.CURRENCY_CODE_TANZANIA -> rates.TZS
            Constants.CURRENCY_CODE_KENYA -> rates.KES
            else -> {
                0.0
            }
        }
    }

    private fun errorCallBack() {}

    private fun isValidPayload(): Boolean {
        val firstName = isNotEmpty(mainActivity, binding.firstNameTextField, binding.firstNameLayout)
        val lastName = isNotEmpty(mainActivity, binding.lastNameTextField, binding.lastNameLayout)
        val phone = isValidPhoneNumber(mainActivity, binding.phoneTextField, binding.phoneLayout, selectedCountryCode)
        val amount = isValidAmount(mainActivity, binding.amountTextField, binding.amountLayout)
        return firstName && lastName && phone && amount
    }
}