package com.example.penguinpay.utils

import com.example.penguinpay.R
import com.example.penguinpay.networking.CountryModel
import java.util.*

/**
 *Created by Yerimah on 5/7/2021.
 */

val countries = LinkedList<CountryModel>()

fun setCountries() {
    countries.add(CountryModel("NIGERIA", "NGN", R.drawable.flag_nigeria, "+234"))
    countries.add(CountryModel("GHANA", "GHN", R.drawable.flag_ghana, "+233"))
    countries.add(CountryModel("UNITED STATES", "USD", R.drawable.flag_united_states_of_america, "+1"))
    countries.add(CountryModel("UNITED KINGDOM", "GP", R.drawable.flag_united_kingdom, "+44"))
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

