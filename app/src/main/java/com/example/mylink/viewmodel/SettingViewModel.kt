package com.example.mylink.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.mylink.data.repository.SjDataStoreRepository

class SettingViewModel(application: Application) : AndroidViewModel(application) {


    private val repository: SjDataStoreRepository = SjDataStoreRepository.getInstance()
    val passwordFlow = repository.getPassword(application.applicationContext)
    val privateFlow = repository.isPrivateMode(application.applicationContext)
    val isPrivateMode: LiveData<Boolean> = privateFlow.asLiveData()

    fun setPrivateMode(isPrivateMode: Boolean) {
        repository.setPrivateMode(getApplication<Application>().applicationContext, isPrivateMode)
    }


}