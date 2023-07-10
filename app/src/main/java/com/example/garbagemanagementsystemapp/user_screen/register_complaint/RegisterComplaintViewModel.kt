package com.example.garbagemanagementsystemapp.user_screen.register_complaint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterComplaintViewModel @Inject constructor(
    private val repository : AuthRepository
): ViewModel() {

    fun registerBin(
        binId: String,
        city: String,
        location: String,
        loadType: String,
        status: String
    ) = viewModelScope.launch {
        repository.registerBin(binId, city, location, loadType, status)
    }
}