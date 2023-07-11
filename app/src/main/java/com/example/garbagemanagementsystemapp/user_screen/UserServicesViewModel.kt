package com.example.garbagemanagementsystemapp.user_screen

import androidx.lifecycle.ViewModel
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserServicesViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    fun signOut() = repo.signOut()
    val userInfo = repo.retrieveData()
    val myComplaints = repo.fetchMyComplaints()
}