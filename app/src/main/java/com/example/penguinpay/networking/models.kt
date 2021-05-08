package com.example.penguinpay.networking


/**
 *@Created by Yerimah on 5/07/2021.
 */

data class CountryModel(
    var name: String = "",
    var currencyCode: String = "",
    var image: Int = 0,
    var countryCode: String = ""
)

data class RatesResponse(
        var disclaimer: String?,
        var license: String?,
        var timestamp: Long?,
        var base: String?,
        var rates: Rates?
)

data class Rates(
        var NGN: Double?,
        var KES: Double?,
        var TZS: Double?,
        var UGX: Double?
)