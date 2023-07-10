package com.example.garbagemanagementsystemapp.user_screen

import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.garbagemanagementsystemapp.ApplicationFirebaseAuth
import com.example.garbagemanagementsystemapp.R
import com.example.garbagemanagementsystemapp.home_screen.defineText
import com.example.garbagemanagementsystemapp.navigation.Screens
import com.example.garbagemanagementsystemapp.register_screen.RegisterViewModel
import com.example.garbagemanagementsystemapp.ui.theme.composables.CustomTextField
import com.example.garbagemanagementsystemapp.ui.theme.composables.dropDownMenu
import com.google.android.gms.maps.model.LatLng
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegisterComplaintContent(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController : NavController,
    registerBin: (binId: String,
                   city: String,
                   location: String,
                   loadType: String,
                   status: String) -> Unit

) {
    var binId by rememberSaveable { mutableStateOf("") }
    var city by rememberSaveable { mutableStateOf("") }
    var location by rememberSaveable { mutableStateOf("") }
    var loadTypeList = listOf("Low", "Medium", "High")
    var statusList = listOf("New", "In Progress", "Done")

    val bottomSheetState = rememberBottomSheetState(BottomSheetValue.Collapsed)
    val coroutineScope = rememberCoroutineScope()

    Log.d("Diellzaa", "RegisterComplaint: $bottomSheetState")
    // Reset the content when the sheet is collapsed
    LaunchedEffect(bottomSheetState.isCollapsed) {
        if (bottomSheetState.isCollapsed) {
            binId = ""
            city = ""
            getAddressFromCoordinates(null)
            loadTypeList = listOf("Low", "Medium", "High")
            statusList = listOf("New", "In Progress", "Done")
        }
    }

    val latlng = navController
        .currentBackStackEntry
        ?.savedStateHandle
        ?.getLiveData<LatLng>("location_data")
        ?.value


    Column {
        defineText(text = "Bin ID", start = 20.dp, top = 8.dp, end = 0.dp, bottom = 0.dp, fraction = 1f)
        CustomTextField(value = binId, onValueChange = {binId = it})
        defineText(text = "City", start = 20.dp, top = 8.dp, end = 0.dp, bottom = 0.dp, fraction = 1f)
        CustomTextField(value = city, onValueChange = {city = it})
        defineText(text = "Location", start = 20.dp, top = 8.dp, end = 0.dp, bottom = 0.dp, fraction = 1f)

        if (latlng == null) {
            OutlinedButton(
                onClick = { navController.navigate(Screens.MapScreen.route) },
                modifier = Modifier
                    .height(55.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .background(Color(229, 229, 229))
                    .border(
                        width = 3.dp,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(112, 145, 98),
                                Color(151, 175, 140)
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                shape = RoundedCornerShape(12.dp)
            )
            {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(112, 145, 98),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Text(text = "Bin location",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontStyle = FontStyle.Italic,
                        fontWeight = FontWeight.ExtraLight,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        } else {
            OutlinedButton(
                onClick = { navController.navigate(Screens.MapScreen.route) },
                modifier = Modifier
                    .height(55.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .border(
                        width = 3.dp,
                        brush = Brush.horizontalGradient(
                            listOf(
                                Color(112, 145, 98),
                                Color(151, 175, 140)
                            )
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    disabledBackgroundColor = Color.Transparent)

            )
            {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(229, 229, 229)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color(112, 145, 98),
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Text(
                        text = "${getAddressFromCoordinates(latlng)}",
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black
                    )
                }
            }
        }

        defineText(text = "Load Type", start = 20.dp, top = 8.dp, end = 0.dp, bottom = 0.dp, fraction = 1f)
        var loadType = dropDownMenu(loadTypeList)
        defineText(text = "Status", start = 20.dp, top = 8.dp, end = 0.dp, bottom = 0.dp, fraction = 1f)
        var status = dropDownMenu(list =statusList)
        Button(
            onClick = {
                registerBin(binId, city, getAddressFromCoordinates(latlng) ?: "", loadType, status)
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 10.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(112, 145, 98), contentColor = Color.White)
        ){
            Text(text = stringResource(R.string.Submit), fontSize = 20.sp)
        }
    }
}

fun getAddressFromCoordinates(latLng: LatLng?): String? {
    val geocoder = Geocoder(ApplicationFirebaseAuth.context, Locale.getDefault())
    if (latLng != null) {
        val addresses: List<Address?> = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1) as List<Address>
        return if (addresses.isEmpty()) {
            ""
        } else {
            val address = addresses[0]
            address?.getAddressLine(0)
        }
    } else {
        return ""
    }
}