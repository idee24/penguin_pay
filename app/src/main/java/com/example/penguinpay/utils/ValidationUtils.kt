package com.example.penguinpay.utils

import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.penguinpay.Constants
import com.example.penguinpay.R
import com.google.android.material.textfield.TextInputLayout
import com.hbb20.CountryCodePicker

/**
 *@Created by Yerimah on 5/07/2021.
 */

fun revalidateListener(inputField: EditText, inputLayout: TextInputLayout) {

    inputField.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val input = inputField.text.toString().trim()
            if (input.isNotEmpty()) { inputLayout.isErrorEnabled = false }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun initAmountListener(inputField: EditText, inputLayout: TextInputLayout) {

    val input = inputField.text.toString().trim()
    inputField.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (input.isNotEmpty() && input.matches("[01]+".toRegex())) {
                inputLayout.isErrorEnabled = false
            }
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    })
}

fun isValidAmount(activity: AppCompatActivity, inputField: EditText, inputLayout: TextInputLayout): Boolean {
    val  target = inputField.text.toString().trim()
    return if (target.matches("[01]+".toRegex())) {
        inputLayout.isErrorEnabled = false
        true
    } else {
        inputLayout.error = activity.getString(R.string.valid_binary_error)
        false
    }
}

fun isNotEmpty(activity: AppCompatActivity, inputField: EditText, inputLayout: TextInputLayout): Boolean {
    val  target = inputField.text.toString().trim()
    return if (!TextUtils.isEmpty(target)) {
        inputLayout.isErrorEnabled = false
        true
    } else {
        inputLayout.error = activity.resources.getString(R.string.empty_field_error_text)
        false
    }
}



fun isValidPhoneNumber(activity: AppCompatActivity, phoneField: EditText,
                       phoneLayout: TextInputLayout, countryCode: String): Boolean {
    val  target = phoneField.text.toString().trim()

    return if (!isValidCountryNumber(countryCode, target)) {
        phoneLayout.error = activity.resources.getString(R.string.invalid_number_error)
        false
    } else {
        phoneLayout.isErrorEnabled = false
        true
    }
}

fun isValidCountryNumber(countryCode: String, number: String): Boolean {
    return when (countryCode) {
        Constants.CURRENCY_CODE_NIGERIA, Constants.CURRENCY_CODE_UGANDA -> {
            number.length == 7
        }
        Constants.CURRENCY_CODE_KENYA, Constants.CURRENCY_CODE_TANZANIA -> {
            number.length == 9
        }
        else -> {
            false
        }
    }
}
