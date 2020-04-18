package com.spacex.viewmodel

import com.spacex.model.Launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * API client to call the main endpoint using Retrofit framework
 */
class SpaceXApiClient {

    fun getAllLaunches() : Call<List<Launch>> {
        val BASE_URL = "https://api.spacexdata.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SpaceXService::class.java).getAllLaunches()
    }
}