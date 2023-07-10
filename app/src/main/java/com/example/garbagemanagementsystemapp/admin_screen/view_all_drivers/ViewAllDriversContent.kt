package com.example.garbagemanagementsystemapp.admin_screen.view_all_drivers

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.admin_screen.create_driver.DriverModel

@Composable
fun ViewAllDriversContent(
    viewAllDriversModel: MutableList<DriverModel>
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        viewAllDrivers(allDrivers = viewAllDriversModel)

    }
}

@Composable
fun viewAllDrivers(allDrivers: MutableList<DriverModel>) {

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = allDrivers,) { driver ->

            Card(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Email",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "Email: ${driver.email}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Abc,
                            contentDescription = "Name",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "Name: ${driver.name}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Abc,
                            contentDescription = "FullName",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "FullName: ${driver.fullName}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Diversity3,
                            contentDescription = "Type",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "Type: ${driver.type}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                }
            }
        }
    }
}
