package com.example.garbagemanagementsystemapp.admin_screen

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
class AdminScreenViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    var signUpResponse by mutableStateOf<SignUpResponse>(Response.Success(false))
        private set

    fun signOut() = repo.signOut()
    val userInfo = repo.retrieveData()
    val myComplaints = repo.fetchAllComplaints()
    val viewAllDrivers = repo.fetchAllDrivers()
    val viewAllUsers = repo.fetchAllUsers()

    fun createDriver(
        email: String,
        password: String,
        fullName: String,
        name: String,
        type: String
    ) = viewModelScope.launch {
        signUpResponse = Response.Loading
        signUpResponse = repo.createDriver(email, password, fullName, name, type)
    }
}