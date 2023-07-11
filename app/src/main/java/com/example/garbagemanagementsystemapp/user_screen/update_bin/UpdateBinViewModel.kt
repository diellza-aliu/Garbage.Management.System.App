package com.example.garbagemanagementsystemapp.user_screen.update_bin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.garbagemanagementsystemapp.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UpdateBinViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    fun updateBin(
        binId: String,
        status: String
    ) = viewModelScope.launch {
        repository.updateBin(binId, status)
    }
}