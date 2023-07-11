package com.example.garbagemanagementsystemapp.register_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    RegisterContent(
        registerUser = { name, surname, email, phone, password, confirmPassword ->
            viewModel.registerUser(name, surname, email, phone, password, confirmPassword)
        },
        navigateBack = navigateBack
    )

}