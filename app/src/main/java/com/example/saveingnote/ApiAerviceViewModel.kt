package com.example.saveingnote

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saveingnote.network.MarsApi
import kotlinx.coroutines.launch

class ApiAerviceViewModel: ViewModel(){

    fun getMarsPhotos() {
        viewModelScope.launch {
            val listResult = MarsApi.retrofitService.getPhotos()
            Log.i("MarsViewModel", "${listResult}")

        }
    }
}