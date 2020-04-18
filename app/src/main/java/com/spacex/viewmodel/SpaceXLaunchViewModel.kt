package com.spacex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.spacex.api.SpaceXRepository
import com.spacex.model.Launch

/**
 * View Model to hold the LiveData of list of Launches
 */

class SpaceXLaunchViewModel : ViewModel() {
    private var launches : LiveData<List<Launch>>

    init {
        val service = SpaceXRepository()
        launches = service.getAllLaunches()
    }


    fun getLaunches(): LiveData<List<Launch>> {
        return launches
    }
}