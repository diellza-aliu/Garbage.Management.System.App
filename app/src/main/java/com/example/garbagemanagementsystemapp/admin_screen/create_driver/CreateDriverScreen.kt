package com.example.garbagemanagementsystemapp.admin_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

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