package com.example.garbagemanagementsystemapp.user_screen.my_complaints

data class ComplaintsModel(
    val binId: String,
    val city: String,
    val status: String,
    val loadType: String,
    val location: String
){
    override fun toString(): String{
        return "$binId, $city, $status, $loadType, $location"
    }
}