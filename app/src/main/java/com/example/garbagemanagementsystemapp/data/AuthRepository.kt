package com.example.garbagemanagementsystemapp.data

import com.example.garbagemanagementsystemapp.admin_screen.create_driver.DriverModel
import com.example.garbagemanagementsystemapp.admin_screen.user_details.UserModel
import com.example.garbagemanagementsystemapp.user_screen.my_complaints.ComplaintsModel
import com.example.garbagemanagementsystemapp.user_screen.my_profile.MyProfileModel
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