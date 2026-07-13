package br.com.usinasantafe.cav.presenter.view.card.photo

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.TitleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import coil.compose.AsyncImage
import java.io.File

@Composable
fun PhotoScreen() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PhotoContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun PhotoContent(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    val fotos = remember {
        mutableStateListOf<Uri>()
    }

    var fotoAtual by remember {
        mutableStateOf<Uri?>(null)
    }

    val takePictureLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicture()
        ) { sucesso ->

            if (sucesso) {
                fotoAtual?.let { uri ->
                    if (fotos.size < 4) {
                        fotos.add(uri)
                    }
                }
            }

        }

    val cameraPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { granted ->

            if (granted) {
                fotoAtual?.let {
                    takePictureLauncher.launch(it)
                }
            }

        }

    Column(
        modifier = modifier.padding(16.dp)
    ) {

        TitleDesign(text = "Teste")

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {

            items(fotos) { uri ->

                Box(
                    modifier = Modifier.aspectRatio(1f)
                ) {

                    AsyncImage(
                        model = uri,
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
                                fotos.remove(uri)
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Excluir foto",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }

                }

            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            enabled = fotos.size < 4,
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                try {

                    val arquivo = File.createTempFile(
                        "foto_",
                        ".jpg",
                        context.cacheDir
                    )

                    val uri = FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        arquivo
                    )

                    fotoAtual = uri

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

            Text("Tirar Foto")

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PhotoPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            PhotoContent(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}