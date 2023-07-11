package com.example.garbagemanagementsystemapp.data

import com.example.garbagemanagementsystemapp.data_classes.DriverModel
import com.example.garbagemanagementsystemapp.data_classes.UserModel
import com.example.garbagemanagementsystemapp.data_classes.ComplaintsModel
import com.example.garbagemanagementsystemapp.data_classes.MyProfileModel
import com.example.garbagemanagementsystemapp.util.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

typealias SignUpResponse = Response<Boolean>
typealias SignInResponse = Response<Boolean>
typealias AuthStateResponse = StateFlow<Boolean>


interface AuthRepository {

    suspend fun loginUser(email: String, password: String): SignInResponse
    suspend fun registerUser(name: String, surname: String, email: String, phone: String, password: String, confirmPassword: String): SignUpResponse
    fun getAuthState(viewModelScope: CoroutineScope): AuthStateResponse
    fun signOut()
    fun retrieveData(): MyProfileModel
    fun fetchMyComplaints(): MutableList<ComplaintsModel>
    fun fetchAllComplaints(): MutableList<ComplaintsModel>
    fun getCurrentUser(): String
    suspend fun registerBin(binId: String, city: String, location: String,  loadType: String, status: String)
    suspend fun updateBin(binId: String, status: String)
    suspend fun updateProfile(name : String, fullName : String, email : String)
    suspend fun createDriver(email: String, password : String, fullName: String, name: String, type: String): SignUpResponse
    fun fetchAllDrivers(): MutableList<DriverModel>
    fun fetchAllUsers(): MutableList<UserModel>
}