package com.example.garbagemanagementsystemapp.login_screen

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.currentCoroutineContext
import kotlin.coroutines.coroutineContext

@Composable
fun LoginFormScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignUpScreen: () -> Unit
) {
    LoginContent(
        logIn = { email, password ->
            if(!email.lowercase().contains("admin") && !email.lowercase().contains("driver")) {
                viewModel.loginUser(email, password)
            }else{
                Toast.makeText(ApplicationFirebaseAuth.context, "You are not an admin or a driver", Toast.LENGTH_LONG).show()
            }
    },
        navigateToSignUpScreen = navigateToSignUpScreen
    )
}