package br.com.usinasantafe.cav.presenter.view.card.local

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.activity.compose.BackHandler
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.MsgErrors
import br.com.usinasantafe.cav.utils.UiStatusState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlin.collections.isNotEmpty

@Composable
fun LocalScreen(
    viewModel: LocalViewModel = hiltViewModel(),
    onNavMenu: () -> Unit,
    onNavInputLocal:  () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LocalScreenContent(
                address = uiState.address,
                latitude = uiState.latitude,
                longitude = uiState.longitude,
                set = viewModel::set,
                onLocalChanged = viewModel::onLocalChanged,
                onCloseDialog = viewModel::onCloseDialog,
                status = uiState.status,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                onNavMenu = onNavMenu,
                onNavTypeLocal = onNavInputLocal,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun LocalScreenContent(
    address: String,
    latitude: Double?,
    longitude: Double?,
    set: () -> Unit,
    onLocalChanged: (String, Double, Double) -> Unit,
    onCloseDialog: () -> Unit,
    status: UiStatusState,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    onNavMenu: () -> Unit,
    onNavTypeLocal: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var searchQuery by remember { mutableStateOf("") }
    var deviceLatLng by remember { mutableStateOf<LatLng?>(null) }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-23.5505, -46.6333), 10f)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true) {
            getLastLocation(fusedLocationClient) { lat, long ->
                val pos = LatLng(lat, long)
                deviceLatLng = pos
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(pos, 16f))
                getAddressFromLocation(context, lat, long) { address ->
                    onLocalChanged(address, pos.latitude, pos.longitude)
                }
            }
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false)
        ) {
            deviceLatLng?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = stringResource(id = R.string.text_local_selection)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.TopCenter)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text(stringResource(id = R.string.text_input_address)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White.copy(alpha = 0.9f), RoundedCornerShape(12.dp)),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = {
                        searchLocation(context, searchQuery) { pos ->
                            deviceLatLng = pos
                            cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(pos, 16f))
                            getAddressFromLocation(context, pos.latitude, pos.longitude) { address ->
                                onLocalChanged(address, pos.latitude, pos.longitude)
                            }
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.text_field))
                    }
                },
                shape = RoundedCornerShape(12.dp)
            )
        }

        if(deviceLatLng != null) {
            Button(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 105.dp)
                    .fillMaxWidth(0.8f),
                onClick = {
                    if (deviceLatLng != null) {
                        onDialogCheck(true)
                    } else {
                        permissionLauncher.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION))
                    }
                }
            ) {
                Text(stringResource(id = R.string.text_capture_local))
            }
        }
        Button(
            onClick = {
                permissionLauncher.launch(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 55.dp)
                .fillMaxWidth(0.8f),
        ) {
            Text(stringResource(id = R.string.text_local_now))
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp)
                .fillMaxWidth(0.8f),
            onClick = onNavMenu
        ) {
            Text(stringResource(id = R.string.text_pattern_return))
        }
        BackHandler {}

        if(flagDialogCheck){
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_desc_local,
                    address, latitude?: 0.0, longitude?: 0.0
                ),
                onClickDismiss = { onDialogCheck(false) },
                onClickYes = set
            )
        }

        if(status.flagDialog) {
            MsgErrors(status.errors, onCloseDialog, status.failure)
        }

    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            onNavTypeLocal()
        }
    }

}

fun getAddressFromLocation(
    context: Context,
    lat: Double,
    lng: Double,
    onResult: (String) -> Unit
) {
    val geocoder = Geocoder(context)

    val formatAddress: (Address?) -> String = { addr ->
        if (addr != null) {
            val road = addr.thoroughfare ?: addr.featureName ?: ""
            val number = addr.subThoroughfare ?: ""
            val district = addr.subLocality ?: addr.locality ?: ""
            val city = addr.subAdminArea ?: addr.adminArea ?: ""
            "$road, $number - $district, $city"
        } else {
            ""
        }
    }
    if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
        geocoder.getFromLocation(lat, lng, 1, object : Geocoder.GeocodeListener {
            override fun onGeocode(addresses: MutableList<Address>) {
                val addressText =
                    if (addresses.isNotEmpty()) formatAddress(addresses[0]) else ""
                onResult(addressText)
            }

            override fun onError(errorMessage: String?) {
                onResult("")
            }
        })
    } else {
        @Suppress("DEPRECATION")
        val addresses = geocoder.getFromLocation(lat, lng, 1)
        val addressText = if (!addresses.isNullOrEmpty()) formatAddress(addresses[0]) else ""
        onResult(addressText)
    }
}

fun searchLocation(context: Context, query: String, onLocationFound: (LatLng) -> Unit) {
    if (query.isBlank()) return
    val geocoder = Geocoder(context)
    if (Build.VERSION_CODES.TIRAMISU <= Build.VERSION.SDK_INT) {
        geocoder.getFromLocationName(query, 1) { addresses ->
            if (addresses.isNotEmpty()) {
                val adder = addresses[0]
                onLocationFound(LatLng(adder.latitude, adder.longitude))
            }
        }
    } else {
        try {
            @Suppress("DEPRECATION")
            val addresses = geocoder.getFromLocationName(query, 1)
            if (!addresses.isNullOrEmpty()) {
                val adder = addresses[0]
                onLocationFound(LatLng(adder.latitude, adder.longitude))
            }
        } catch (e: Exception) { e.printStackTrace() }
    }
}

@SuppressLint("MissingPermission")
fun getLastLocation(client: FusedLocationProviderClient, onLocationReceived: (Double, Double) -> Unit) {
    client.lastLocation.addOnSuccessListener { location ->
        location?.let { onLocationReceived(it.latitude, it.longitude) }
    }
}

@Preview(showBackground = true)
@Composable
fun LocalScreenPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            LocalScreenContent(
                address = "",
                latitude = 0.0,
                longitude = 0.0,
                set = {},
                onLocalChanged = { _, _, _ -> },
                onCloseDialog = {},
                status = UiStatusState(),
                flagDialogCheck = false,
                onDialogCheck = {},
                onNavMenu = {},
                onNavTypeLocal = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}