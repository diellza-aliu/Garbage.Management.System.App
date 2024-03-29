package com.example.garbagemanagementsystemapp.user_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.data_classes.ComplaintsModel
import com.example.garbagemanagementsystemapp.data_classes.UserViewPair

@Composable
fun MyComplaint(myComplaints: MutableList<ComplaintsModel>) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        myComplaintsRecyclerView(myComplaints = myComplaints) {}

    }
}

@Composable
fun myComplaintsRecyclerView(
    myComplaints: MutableList<ComplaintsModel>,
    onItemClick: ((UserViewPair) -> Unit)?
) {

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = myComplaints) { myComplaint ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                elevation = 8.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
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
                            text = stringResource(id = R.string.Bin_Id) + " : ${myComplaint.binId}",
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
                            imageVector = Icons.Default.Info,
                            contentDescription = "City",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = stringResource(id = R.string.City) + " : ${myComplaint.city}",
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
                            text = stringResource(id = R.string.Status) + " : ${myComplaint.status}",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "LoadType",
                            tint = Color(112, 145, 98)
                        )
                        Text(
                            text = stringResource(id = R.string.Load_Type) + " : ${myComplaint.loadType}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 8.dp)
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
                            text = stringResource(id = R.string.Location) + " : ${myComplaint.location}",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}



