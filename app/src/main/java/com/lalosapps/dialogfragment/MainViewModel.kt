package com.lalosapps.dialogfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _delay = MutableLiveData<Int?>(null)
    val delay: LiveData<Int?> = _delay

    fun startCountdown() {
        viewModelScope.launch {
            Countdown.start {
                _delay.value = it
            }
        }
    }
}