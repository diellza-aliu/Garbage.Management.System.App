package com.example.garbagemanagementsystemapp.driver_screen.collect_trash_bins

import androidx.compose.runtime.Composable
import com.example.garbagemanagementsystemapp.data_classes.ComplaintsModel

@Composable
fun CollectTrashBinScreen(myComplaints: MutableList<ComplaintsModel>) {

    CollectTrashBins(myComplaints = myComplaints)
}