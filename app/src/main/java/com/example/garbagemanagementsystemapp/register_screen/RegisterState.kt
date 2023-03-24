package com.example.garbagemanagementsystemapp.register_screen

data class RegisterState(
    val isLoading: Boolean = false,
    val isSuccess: String? = "",
    val isError: String? = ""
)