package com.example.penguinpay.networking

import retrofit2.http.GET
import retrofit2.http.Query

/**
 *@Created by Yerimah on 5/07/2021.
 */

interface ApiService {

    @GET(Routes.RATES)
    suspend fun getLatestRates(@Query(Params.APP_ID_PARAM) appId: String,
                               @Query(Params.BASE_PARAM) base: String): RatesResponse
}