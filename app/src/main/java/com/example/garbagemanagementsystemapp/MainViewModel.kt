package com.example.garbagemanagementsystemapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: AuthRepository
): ViewModel() {
    init {
        Log.d("Diellzaa", ": mainviwemodel")
        getAuthState()
    }

    fun getAuthState() = repository.getAuthState(viewModelScope)
}