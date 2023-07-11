package com.example.garbagemanagementsystemapp.admin_screen

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.example.garbagemanagementsystemapp.login_screen.LoginContent
import com.example.garbagemanagementsystemapp.login_screen.LoginViewModel

@Composable
fun AdminLoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit,

    ) {
    LoginContent(
        logIn = { email, password ->
            if (email.lowercase().contains("admin")) {
                viewModel.loginUser(email, password)
            } else {
                Toast.makeText(
                    ApplicationFirebaseAuth.context,
                    "You are not admin",
                    Toast.LENGTH_LONG
                ).show()
            }
        },
        navigateToSignUpScreen = navigateToSignUpScreen
    )
}