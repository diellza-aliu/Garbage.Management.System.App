package com.example.garbagemanagementsystemapp.driver_screen.collect_trash_bins

import androidx.lifecycle.ViewModel
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectTrashBinViewModel @Inject constructor(
    private val repository : AuthRepository
): ViewModel()