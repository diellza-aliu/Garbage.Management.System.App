package com.example.garbagemanagementsystemapp.user_screen.my_profile

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.data_classes.MyProfileModel

@Composable
fun MyProfileScreen(
    myProfileModel: MyProfileModel,
    viewModel: MyProfileViewModel = hiltViewModel()
) {

    MyProfile(
        myProfileModel = myProfileModel,
        updateProfile = { name, fullName, email ->
            viewModel.updateProfile(name, fullName, email)
        }
    )
}