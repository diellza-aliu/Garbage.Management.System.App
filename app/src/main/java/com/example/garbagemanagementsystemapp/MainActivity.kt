package com.example.garbagemanagementsystemapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import com.example.garbagemanagementsystemapp.navigation.NavigationGraph
import com.example.garbagemanagementsystemapp.navigation.Screens
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalAnimationApi
@AndroidEntryPoint
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberAnimatedNavController()
            NavigationGraph(
                navController = navController
            )
            Log.d("Diellzaa", "onCreate: ")
            AuthState()
        }
    }


    @Composable
    private fun AuthState() {
        Log.d("Diellzaa", ": AuthState")
        val isUserSignedOut = viewModel.getAuthState().collectAsState().value
        Log.d("Firebase", "AuthState: $isUserSignedOut")
        val currentUserType = viewModel.repository.getCurrentUser()
        Log.d("Firebase", "AuthState: $currentUserType ")

        if (isUserSignedOut) {
            NavigateToHomeScreen()
        } else if (currentUserType == "1") {
            NavigateToUserServices()
        } else if (currentUserType == "2") {
            NavigateToAdmin()
        } else if (currentUserType == "3") {
            NavigateToDriver()
        } else {
            NavigateToHomeScreen()
        }
    }

    @Composable
    private fun NavigateToAdmin() = navController.navigate(Screens.AdminScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToDriver() = navController.navigate(Screens.DriverScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }

    @Composable
    private fun NavigateToUserServices() =
        navController.navigate(Screens.UserServicesScreen.route) {
            popUpTo(navController.graph.id) {
                inclusive = true
            }
        }

    @Composable
    private fun NavigateToHomeScreen() = navController.navigate(Screens.HomeScreen.route) {
        popUpTo(navController.graph.id) {
            inclusive = true
        }
    }
}
