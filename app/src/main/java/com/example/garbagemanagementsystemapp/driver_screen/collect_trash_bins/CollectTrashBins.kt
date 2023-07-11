package com.example.garbagemanagementsystemapp.driver_screen.collect_trash_bins

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.data_classes.ComplaintsModel

@Composable
fun CollectTrashBins(
    myComplaints: MutableList<ComplaintsModel>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        openBinLocation(myComplaints = myComplaints, context)
    }

}

@Composable
fun openBinLocation(
    myComplaints: MutableList<ComplaintsModel>,
    context: Context
) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = myComplaints) { myComplaint ->
            var expanded by remember { mutableStateOf(false) }
            var selectedStatus by remember { mutableStateOf(myComplaint.status) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                elevation = 8.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Bin ID",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "Bin ID: ${myComplaint.binId}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Assignment,
                            contentDescription = "Status",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "Status: $selectedStatus",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .width(150.dp)
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = "${myComplaint.location}",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                        Button(
                            onClick = {
                                expanded = true
                                navigateToLocation(myComplaint.location, context)
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(112, 145, 98),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier
                                .padding(start = 0.dp)
                                .wrapContentWidth()
                        ) {
                            Text(text = "Open")
                        }
                    }
                }
            }
        }
    }
}

private fun navigateToLocation(location: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse("geo:0,0?q=$location")
        setPackage("com.google.android.apps.maps")
    }
    context.startActivity(intent)
}