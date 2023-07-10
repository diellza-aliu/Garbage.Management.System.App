package com.example.garbagemanagementsystemapp.login_screen

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth

@Composable
fun DriverLoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit
) {
    LoginContent(
        logIn = { email, password ->
            if(email.lowercase().contains("driver")){
                viewModel.loginUser(email, password)
            }else{
                Toast.makeText(ApplicationFirebaseAuth.context, "You are not a driver", Toast.LENGTH_LONG).show()
            }
        },
        navigateToSignUpScreen = navigateToSignUpScreen
    )
}