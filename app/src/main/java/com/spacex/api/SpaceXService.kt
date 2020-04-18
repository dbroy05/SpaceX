package com.spacex.api

import com.spacex.model.Launch
import retrofit2.Call
import retrofit2.http.GET

interface SpaceXService {
    @GET("/v3/launches")
    fun getAllLaunches() : Call<List<Launch>>

}
