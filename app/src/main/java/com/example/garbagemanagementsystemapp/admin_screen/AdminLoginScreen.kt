package com.example.garbagemanagementsystemapp.login_screen

import android.app.Activity
import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.runtime.rememberCompositionContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.google.api.Context

@Composable
fun AdminLoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit,

) {
    LoginContent(
        logIn = { email, password ->
            if(email.lowercase().contains("admin")){
                viewModel.loginUser(email, password)
            }else{
                Toast.makeText(ApplicationFirebaseAuth.context, "You are not admin", Toast.LENGTH_LONG).show()
            }
        },
        navigateToSignUpScreen = navigateToSignUpScreen
    )
}