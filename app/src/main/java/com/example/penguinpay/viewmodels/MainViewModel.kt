package com.example.penguinpay.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.penguinpay.AppRepository
import com.example.penguinpay.networking.ApiService
import com.example.penguinpay.networking.CountryModel
import com.example.penguinpay.networking.RatesResponse
import com.example.penguinpay.networking.Resource

/**
 *@Created by Yerimah on 5/07/2021.
 */
class MainViewModel(apiService: ApiService): ViewModel() {

    private val appRepository = AppRepository(apiService)

    fun getLatestRates(): LiveData<Resource<RatesResponse>> {
        return  appRepository.getLatestRates()
    }

    fun getCountries(): List<CountryModel> {
        return appRepository.getCountries()
    }

    fun getCountryByCode(countryCode: String): CountryModel {
        var country = CountryModel()
        appRepository.getCountries().forEach {
            if (countryCode == it.currencyCode) {
                country = it
                return@forEach
            }
        }
        return country
    }

}