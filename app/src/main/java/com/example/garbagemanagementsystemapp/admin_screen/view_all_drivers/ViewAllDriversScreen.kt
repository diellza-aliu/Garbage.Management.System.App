package com.example.garbagemanagementsystemapp.admin_screen.view_all_drivers

import androidx.compose.runtime.Composable
import com.example.garbagemanagementsystemapp.admin_screen.create_driver.DriverModel


@Composable
fun ViewAllDriversScreen(
    viewAllDriversModel: MutableList<DriverModel>
) {

    ViewAllDriversContent(
        viewAllDriversModel = viewAllDriversModel,

    )
}