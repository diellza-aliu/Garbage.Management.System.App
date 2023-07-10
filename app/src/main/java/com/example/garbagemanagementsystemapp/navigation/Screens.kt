package com.example.garbagemanagementsystemapp.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens(route = "SignIn_Screen")
    object AdminLoginScreen : Screens(route = "AdminSignIn_Screen")
    object DriverLoginScreen : Screens(route = "DriverSignIn_Screen")
    object RegisterScreen : Screens(route = "SignUp_Screen")
    object UserServicesScreen : Screens(route = "UserServices_Screen")
    object AdminScreen : Screens(route = "Admin_Screen")
    object DriverScreen : Screens(route = "Driver_Screen")
    object MainScreen : Screens(route = "Main_Screen")
    object HomeScreen : Screens(route = "Home_Screen")
    object MapScreen : Screens(route = "Map")


}