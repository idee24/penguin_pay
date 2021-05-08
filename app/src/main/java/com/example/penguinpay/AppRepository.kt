package com.example.penguinpay

import androidx.lifecycle.liveData
import com.example.penguinpay.networking.ApiService
import com.example.penguinpay.networking.CountryModel
import com.example.penguinpay.networking.Resource
import kotlinx.coroutines.Dispatchers
import java.util.*

/**
 *@Created by Yerimah on 5/07/2021.
 */

class AppRepository(private val apiService: ApiService) {

    fun getLatestRates() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiService.getLatestRates(Constants.EXCHANGE_APP_ID, Constants.BASE_CURRENCY)))
        }
        catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.error(data = null, message = e.message ?: "A problem occurred"))
        }
    }

    fun getCountries(): List<CountryModel> {
        val countries = LinkedList<CountryModel>()
        countries.add(CountryModel("Nigeria", Constants.CURRENCY_CODE_NIGERIA, R.drawable.flag_nigeria, "+234"))
        countries.add(CountryModel("Kenya", Constants.CURRENCY_CODE_KENYA, R.drawable.flag_kenya, "+254"))
        countries.add(CountryModel("Tanzania", Constants.CURRENCY_CODE_TANZANIA, R.drawable.flag_tanzania, "+255"))
        countries.add(CountryModel("Uganda", Constants.CURRENCY_CODE_UGANDA, R.drawable.flag_uganda, "+256"))
        return countries
    }

}