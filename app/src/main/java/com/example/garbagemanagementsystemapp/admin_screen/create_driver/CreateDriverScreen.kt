package com.example.garbagemanagementsystemapp.admin_screen.create_driver

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.admin_screen.AdminScreenViewModel

@Composable
fun CreateDriverScreen(
    viewModel: AdminScreenViewModel = hiltViewModel()
) {
    CreateDriverContent(
        createDriver = { email, password, fullName, name, type ->
            viewModel.createDriver(email, password, fullName, name, type)
        }
    )

}