package com.example.garbagemanagementsystemapp.maps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.garbagemanagementsystemapp.util.rememberMapViewWithLifecycle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun BinLocationMap(
    navController: NavController
) {
    val mapView = rememberMapViewWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(bottom = 36.dp)
    ) {
        AndroidView({ mapView }) { mapView ->
            coroutineScope.launch {
                val map = mapView.awaitMap()
                map.uiSettings.isZoomControlsEnabled = true
                val location = LatLng(42.665440, 21.165319)

                map.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(location, 20f)
                )

                val markerOptions = MarkerOptions()
                    .title("Welcome to Prishtina")
                    .position(location)
                map.addMarker(markerOptions)

                setMapLongClick(map = map, navController = navController)
            }
        }
    }
}

private fun setMapLongClick(
    map: GoogleMap,
    navController: NavController
) {
    map.setOnMapLongClickListener { latlng ->
        val snippet = String.format(
            Locale.getDefault(),
            "Lat: %1$.2f, Lng: %2$.2f",
            latlng.latitude,
            latlng.longitude
        )

        map.addMarker(
            MarkerOptions().position(latlng).title("Bin location").snippet(snippet)
        ).apply {
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("location_data", latlng)
        }
    }
}