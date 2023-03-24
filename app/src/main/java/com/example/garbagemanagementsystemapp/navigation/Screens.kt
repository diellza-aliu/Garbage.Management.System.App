package com.example.garbagemanagementsystemapp.navigation

sealed class Screens(val route: String) {
    object LoginScreen : Screens(route = "SignIn_Screen")
    object RegisterScreen : Screens(route = "SignUp_Screen")


}