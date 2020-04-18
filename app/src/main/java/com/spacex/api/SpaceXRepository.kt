package com.spacex.api

import androidx.lifecycle.MutableLiveData
import com.spacex.api.SpaceXApiClient
import com.spacex.model.Launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Repository to abstract the API call to endpoint client.
 */
class SpaceXRepository {

    fun getAllLaunches() : MutableLiveData<List<Launch>> {
        var launchList = MutableLiveData<List<Launch>>()
        var apiClient = SpaceXApiClient()
        apiClient.getAllLaunches().enqueue(object : Callback<List<Launch>> {
            override fun onResponse(call: Call<List<Launch>>, response: Response<List<Launch>>) {
                launchList.value = response.body()
            }

            override fun onFailure(call: Call<List<Launch>>, t: Throwable) {
                System.out.println(t)
            }
        })
        return launchList
    }
}