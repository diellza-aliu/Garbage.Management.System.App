package com.example.garbagemanagementsystemapp.login_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import com.example.garbagemanagementsystemapp.data.SignInResponse
import com.example.garbagemanagementsystemapp.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository : AuthRepository
): ViewModel() {
    var signInResponse by mutableStateOf<SignInResponse>(Response.Success(false))


    fun loginUser(email: String, password: String) = viewModelScope.launch {
        signInResponse = Response.Loading
        signInResponse = repository.loginUser(email, password)
    }
}