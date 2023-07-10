package com.example.garbagemanagementsystemapp.user_screen.update_bin

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.user_screen.my_complaints.ComplaintsModel

enum class BinStatus {
    New,
    InProgress,
    Done
}

@Composable
fun UpdateBinStatus(
    myComplaints: MutableList<ComplaintsModel>,
    updateBin: (binId: String, status: String) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        updateBinsStatusRecyclerView(myComplaints = myComplaints, updateBin)
    }

}

@Composable
fun updateBinsStatusRecyclerView(
    myComplaints: MutableList<ComplaintsModel>,
    updateBin: (binId: String, status: String) -> Unit
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
                            text = stringResource(id = R.string.Bin_Id)+ " : ${myComplaint.binId}",
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
                            text = stringResource(id = R.string.Status)+ " : ${myComplaint.status}",
                            fontSize = 16.sp,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .width(150.dp)
                        )
                        Button(
                            onClick = { expanded = true },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(112, 145, 98),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.padding(start = 56.dp)
                        ) {
                            Text(text = stringResource(id = R.string.Update))
                        }
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
                            text = stringResource(id = R.string.Location)+ " : ${myComplaint.location}",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            if (expanded) {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    BinStatus.values().forEach { status ->
                        DropdownMenuItem(
                            onClick = {
                                selectedStatus = status.name
                                updateBin(myComplaint.binId , selectedStatus)
                                expanded = false
                            }
                        ) {
                            Text(text = status.name)
                        }
                    }
                }
            }
        }
    }
}
