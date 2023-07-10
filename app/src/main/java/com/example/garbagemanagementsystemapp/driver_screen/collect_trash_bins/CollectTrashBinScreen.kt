package com.example.garbagemanagementsystemapp.driver_screen.collect_trash_bins

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.user_screen.my_complaints.ComplaintsModel
import com.example.garbagemanagementsystemapp.user_screen.update_bin.UpdateBinViewModel

@Composable
fun CollectTrashBinScreen(
    viewModel: UpdateBinViewModel = hiltViewModel(), myComplaints: MutableList<ComplaintsModel>) {

    CollectTrashBins(myComplaints = myComplaints)
}