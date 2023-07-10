package com.example.garbagemanagementsystemapp.home_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.register_screen.RegisterViewModel

@Composable
fun HomeScreen(
    navigateToSignUpScreen: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    navigateToAdminLoginScreen: () -> Unit,
    navigateToDriverLoginScreen: () -> Unit
) {
    HomeContent(
        navigateToSignUpScreen = navigateToSignUpScreen,
        navigateToSignInScreen = navigateToSignInScreen,
        navigateToAdminLoginScreen = navigateToAdminLoginScreen,
        navigateToDriverLoginScreen = navigateToDriverLoginScreen
    )
}