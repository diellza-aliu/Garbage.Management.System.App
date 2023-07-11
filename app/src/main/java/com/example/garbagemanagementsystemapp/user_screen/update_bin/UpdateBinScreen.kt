package com.example.garbagemanagementsystemapp.user_screen.update_bin

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.garbagemanagementsystemapp.data_classes.ComplaintsModel

@Composable
fun UpdateBinScreen(
    viewModel: UpdateBinViewModel = hiltViewModel(), myComplaints: MutableList<ComplaintsModel>
) {

    UpdateBinStatus(myComplaints = myComplaints,
        updateBin = { binId, status ->
            viewModel.updateBin(binId, status)
        }

    )

}