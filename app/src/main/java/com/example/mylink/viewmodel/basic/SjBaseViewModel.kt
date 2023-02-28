package com.example.mylink.viewmodel.basic

import androidx.lifecycle.ViewModel

abstract class SjBaseViewModel : ViewModel() {

    var isPrivateMode: Boolean = false
        set(value) {
            field = value
            refreshData()
        }

    abstract fun refreshData()

}