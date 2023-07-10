package com.example.garbagemanagementsystemapp.user_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class UserServicesViewModel @Inject constructor(
    private val repo: AuthRepository
): ViewModel() {

    fun signOut() = repo.signOut()
    val userInfo = repo.retrieveData()
    val myComplaints = repo.fetchMyComplaints()
}