package com.example.garbagemanagementsystemapp.user_screen.my_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun updateProfile(name: String, fullName: String, email: String) = viewModelScope.launch {
        repository.updateProfile(name, fullName, email)
    }
}