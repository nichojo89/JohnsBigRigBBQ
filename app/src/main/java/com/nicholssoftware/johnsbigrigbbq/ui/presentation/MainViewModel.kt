package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _topBarTitle = MutableLiveData("")
    val topBarTitle: LiveData<String>
        get() = _topBarTitle

    fun setTitle(title: String){
        _topBarTitle.value = title
    }
}