package com.example.mylink.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mylink.data.repository.SjRepository

class ReadLinkViewModel : ViewModel() {
    val repository: SjRepository = SjRepository()
    val linksWithDomains get() = repository.linksWithDomains



}