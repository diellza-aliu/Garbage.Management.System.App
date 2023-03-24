package com.example.garbagemanagementsystemapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.garbagemanagementsystemapp.login_screen.LoginFormActivity
import com.example.garbagemanagementsystemapp.register_screen.RegisterFormActivity
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.RegisterScreen.route
    ) {
        composable(route = Screens.LoginScreen.route) {
            LoginFormActivity()
        }
        composable(route = Screens.RegisterScreen.route) {
            RegisterFormActivity()
        }
    }

}