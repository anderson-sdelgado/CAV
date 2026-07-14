package br.com.usinasantafe.cav.presenter.view.card.photo

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.presenter.theme.TextButtonDesign
import coil.compose.AsyncImage
import java.io.File

@Composable
fun PhotoScreen(
    viewModel: PhotoViewModel = hiltViewModel(),
    onNavObs: () -> Unit,
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.recoverData()
            }

            PhotoContent(
                photos = uiState.photos,
                newPhoto = uiState.newPhoto,
                addPhoto = viewModel::addPhoto,
                removePhoto = viewModel::removePhoto,
                setNewPhoto = viewModel::setNewPhoto,
                onNavObs = onNavObs,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PhotoContent(
    photos: List<String>,
    newPhoto: String?,
    addPhoto: (String) -> Unit,
    removePhoto: (String) -> Unit,
    setNewPhoto: (String) -> Unit,
    onNavObs: () -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val currentNewPhoto by rememberUpdatedState(newPhoto)

    val takePictureLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { success ->

            if (success) {
                currentNewPhoto?.let { path ->
                    addPhoto(path)
                }
            }

        }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                currentNewPhoto?.let { path ->
                    val file = File(path)
                    if (!file.exists()) return@let
                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        file
                    )
                    takePictureLauncher.launch(uri)
                }
            }
        }

    Column(
        modifier = modifier.padding(16.dp)
    ) {
        TitleDesign(text = stringResource(id = R.string.text_title_photo))
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(photos) { path ->
                Box(
                    modifier = Modifier.aspectRatio(1f)
                ) {

                    AsyncImage(
                        model = path,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(6.dp)
                            .size(32.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.7f),
                                shape = CircleShape
                            )
                            .clickable {
                                removePhoto(path)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = R.string.text_delete_photo),
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        val prefix = stringResource(id = R.string.text_name_photo)
        val suffix = stringResource(id = R.string.text_extension_photo)
        Button(
            enabled = photos.size < 4,
            modifier = Modifier
                .fillMaxWidth()
            ,
            onClick = {
                try {
                    val photoDir = File(context.filesDir, "photos")

                    if (!photoDir.exists()) {
                        photoDir.mkdirs()
                    }

                    val file = File.createTempFile(
                        prefix,
                        suffix,
                        photoDir
                    )

                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        file
                    )

                    setNewPhoto(file.absolutePath)
                    if (
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        takePictureLauncher.launch(uri)
                    } else {

                        cameraPermissionLauncher.launch(
                            Manifest.permission.CAMERA
                        )

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        ) {
            Text(
                text = stringResource(id = R.string.text_take_photo),
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            Button(
                onClick = onNavObs,
                modifier = Modifier.weight(1f)
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_return),
                    padding = 10
                )
            }
            Button(
                onClick = {},
                modifier = Modifier.weight(1f),
            ) {
                TextButtonDesign(
                    text = stringResource(id = R.string.text_pattern_save),
                    padding = 10
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PhotoPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PhotoContent(
                photos = emptyList(),
                newPhoto = null,
                addPhoto = {},
                setNewPhoto = {},
                removePhoto = {},
                onNavObs = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}