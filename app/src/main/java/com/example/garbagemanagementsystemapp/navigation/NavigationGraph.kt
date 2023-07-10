package com.example.garbagemanagementsystemapp.navigation


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.garbagemanagementsystemapp.AdminScreen
import com.example.garbagemanagementsystemapp.DriverScreen
import com.example.garbagemanagementsystemapp.MainActivity
import com.example.garbagemanagementsystemapp.UserServicesScreen
import com.example.garbagemanagementsystemapp.home_screen.HomeScreen
import com.example.garbagemanagementsystemapp.login_screen.AdminLoginScreen
import com.example.garbagemanagementsystemapp.login_screen.DriverLoginScreen
import com.example.garbagemanagementsystemapp.login_screen.LoginFormScreen
import com.example.garbagemanagementsystemapp.maps.BinLocationMap
import com.example.garbagemanagementsystemapp.register_screen.RegisterScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalFoundationApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screens.RegisterScreen.route
    ) {
        composable(route = Screens.HomeScreen.route) {
            HomeScreen(
                navigateToSignUpScreen = {
                    navController.navigate(Screens.RegisterScreen.route)
                },
                navigateToSignInScreen = {
                    navController.navigate(Screens.LoginScreen.route)
                },
                navigateToAdminLoginScreen = {
                    navController.navigate(Screens.AdminLoginScreen.route)
                },
                navigateToDriverLoginScreen = {
                    navController.navigate(Screens.DriverLoginScreen.route)
                }
            )
        }
        composable(route = Screens.LoginScreen.route) {
            LoginFormScreen(
                navigateToSignUpScreen = {
                    navController.navigate(Screens.RegisterScreen.route)
                }
            )
        }
        composable(route = Screens.AdminLoginScreen.route) {
            AdminLoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(Screens.RegisterScreen.route)
                }
            )
        }
        composable(route = Screens.DriverLoginScreen.route) {
            DriverLoginScreen(
                navigateToSignUpScreen = {
                    navController.navigate(Screens.RegisterScreen.route)
                }
            )
        }
        composable(route = Screens.RegisterScreen.route){
            RegisterScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screens.UserServicesScreen.route) {
            UserServicesScreen( navController = navController)
        }

        composable(route = Screens.AdminScreen.route) {
            AdminScreen()
        }

        composable(route = Screens.DriverScreen.route) {
            DriverScreen()
        }

        composable(route = Screens.MainScreen.route) {
            MainActivity()
        }

        composable(route = Screens.MapScreen.route){
            BinLocationMap(navController = navController)
        }

    }

}