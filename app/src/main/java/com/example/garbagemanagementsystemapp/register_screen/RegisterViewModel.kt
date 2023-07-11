package com.example.garbagemanagementsystemapp.register_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import com.example.garbagemanagementsystemapp.data.SignUpResponse
import com.example.garbagemanagementsystemapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
        private set

    fun registerUser(
        name: String,
        surname: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ) = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse =
            repository.registerUser(name, surname, email, phone, password, confirmPassword)
    }
}