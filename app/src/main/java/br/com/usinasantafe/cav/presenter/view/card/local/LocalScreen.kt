package br.com.usinasantafe.cav.presenter.view.card.local

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
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.lib.Errors
import br.com.usinasantafe.cav.lib.errors
import br.com.usinasantafe.cav.presenter.theme.AlertDialogCheckDesign
import br.com.usinasantafe.cav.presenter.theme.AlertDialogSimpleDesign
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlin.collections.isNotEmpty

@Composable
fun LocalScreen(
    viewModel: LocalViewModel = hiltViewModel(),
    onNavCard: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            LocalScreenContent(
                address = uiState.address,
                latitude = uiState.latitude,
                longitude = uiState.longitude,
                onLocalChanged = viewModel::onLocalChanged,
                setCloseDialog = viewModel::setCloseDialog,
                flagAccess = uiState.flagAccess,
                flagDialog = uiState.flagDialog,
                failure = uiState.failure,
                errors = uiState.errors,
                flagDialogCheck = uiState.flagDialogCheck,
                onDialogCheck = viewModel::onDialogCheck,
                onNavCard = onNavCard
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun LocalScreenContent(
    address: String,
    latitude: Double,
    longitude: Double,
    onLocalChanged: (String, Double, Double) -> Unit,
    setCloseDialog: () -> Unit,
    flagAccess: Boolean,
    flagDialog: Boolean,
    failure: String,
    errors: Errors,
    flagDialogCheck: Boolean,
    onDialogCheck: (Boolean) -> Unit,
    onNavCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }

    var showDialog by remember { mutableStateOf(false) }
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
                val address = getAddressFromLocation(context, lat, long)
                onLocalChanged(address, lat, long)
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
                            val address = getAddressFromLocation(context, pos.latitude, pos.longitude)
                            onLocalChanged(address, pos.latitude, pos.longitude)
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = stringResource(id = R.string.text_field))
                    }
                },
                shape = RoundedCornerShape(12.dp)
            )
        }



        Button(
            onClick = {
                permissionLauncher.launch(
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 105.dp),
        ) {
            Text(stringResource(id = R.string.text_local_now))
        }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 55.dp),
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

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 5.dp),
            onClick = {}
        ) {
            Text(stringResource(id = R.string.text_pattern_return))
        }

        if(flagDialogCheck){
            AlertDialogCheckDesign(
                text = stringResource(
                    id = R.string.text_local,
                    address, latitude, longitude
                ),
                setCloseDialog = { onDialogCheck(false) },
                setActionButtonYes = {}
            )
        }

        if(flagDialog) {
            val text =
                errors(errors, failure)
            AlertDialogSimpleDesign(text = text, setCloseDialog = setCloseDialog,)
        }

    }
}

fun getAddressFromLocation(context: Context, lat: Double, lng: Double): String {
    val geocoder = Geocoder(context)
    return try {
        val addresses = geocoder.getFromLocation(lat, lng, 1)
        if (!addresses.isNullOrEmpty()) {
            val addr = addresses[0]

            val logradouro = addr.thoroughfare ?: addr.featureName ?: "Local não identificado"
            val numero = addr.subThoroughfare ?: "S/N"
            val bairro = addr.subLocality ?: addr.locality ?: ""
            val cidade = addr.adminArea ?: ""

            "$logradouro, $numero - $bairro, $cidade"
        } else {
            "Coordenadas obtidas, mas endereço não encontrado."
        }
    } catch (e: Exception) {
        "Erro na conexão com o serviço de mapas."
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun searchLocation(context: Context, query: String, onLocationFound: (LatLng) -> Unit) {
    if (query.isBlank()) return
    val geocoder = Geocoder(context)
    geocoder.getFromLocationName(query, 1) { addresses ->
        if (addresses.isNotEmpty()) {
            val addr = addresses[0]
            onLocationFound(LatLng(addr.latitude, addr.longitude))
        }
    }
//    try {
//        val addresses = geocoder.getFromLocationName(query, 1)
//        if (!addresses.isNullOrEmpty()) {
//            val addr = addresses[0]
//            onLocationFound(LatLng(addr.latitude, addr.longitude))
//        }
//    } catch (e: Exception) { e.printStackTrace() }
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
                onLocalChanged = { _, _, _ -> },
                setCloseDialog = {},
                flagAccess = false,
                flagDialog = false,
                failure = "",
                errors = Errors.FIELD_EMPTY,
                flagDialogCheck = false,
                onDialogCheck = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}