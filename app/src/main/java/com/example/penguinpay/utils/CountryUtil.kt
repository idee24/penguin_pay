package com.example.penguinpay.utils

import com.example.penguinpay.R
import com.example.penguinpay.networking.CountryModel
import java.util.*

/**
 *Created by Yerimah on 5/7/2021.
 */

val countries = LinkedList<CountryModel>()

fun setCountries() {
    countries.add(CountryModel("Nigeria", "NGN", R.drawable.flag_nigeria, "+234"))
    countries.add(CountryModel("Kenya", "KES", R.drawable.flag_kenya, "+254"))
    countries.add(CountryModel("Tanzania", "TZS", R.drawable.flag_tanzania, "+255"))
    countries.add(CountryModel("Uganda", "UGX", R.drawable.flag_uganda, "+256"))
}

fun getCountryList(): List<CountryModel> {
    return countries
}

fun getCountryByCode(countryCode: String): CountryModel {
    var country = CountryModel()
    countries.forEach {
        if (countryCode == it.countryCode) {
            country = it
            return@forEach
        }
    }
    return country
}

