package com.example.garbagemanagementsystemapp.user_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.garbagemanagementsystemapp.user_screen.register_complaint.RegisterComplaintViewModel

@Composable
fun RegisterComplaintScreen(
    viewModel: RegisterComplaintViewModel = hiltViewModel(),
    navController: NavController
) {
    RegisterComplaintContent(
        registerBin = { binId, city, location, loadType, status ->
            viewModel.registerBin(binId, city, location, loadType, status)
        },
        navController = navController
    )
}
