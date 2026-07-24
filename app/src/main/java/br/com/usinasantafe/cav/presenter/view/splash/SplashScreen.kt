package br.com.usinasantafe.cav.presenter.view.splash

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.usinasantafe.cav.R
import br.com.usinasantafe.cav.presenter.theme.AlertDialogSimpleDesign
import br.com.usinasantafe.cav.presenter.theme.CAVTheme
import br.com.usinasantafe.cav.utils.UiStatusState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    onNavInitialMenu: () -> Unit,
    onNavMenuDataInitial: () -> Unit
) {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(Unit) {
                viewModel.startApp()
            }

            SplashContent(
                flagAccessCard = uiState.flagAccessCard,
                setCloseDialog = viewModel::setCloseDialog,
                status = uiState.status,
                onNavInitialMenu = onNavInitialMenu,
                onNavMenuDataInitial = onNavMenuDataInitial,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun SplashContent(
    flagAccessCard: Boolean,
    setCloseDialog: () -> Unit,
    status: UiStatusState,
    onNavInitialMenu: () -> Unit,
    onNavMenuDataInitial: () -> Unit,
    modifier: Modifier = Modifier
) {
    var visibility by remember { mutableStateOf(true) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                visibility = !visibility
                delay(2000)
            }
        }
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = visibility,
            enter = fadeIn(animationSpec = tween(durationMillis = 1100)),
            exit = fadeOut(animationSpec = tween(durationMillis = 1100))
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.app_name),
                contentScale = ContentScale.Fit,
                modifier = modifier.size(250.dp)
            )
        }
    }

    if(status.flagDialog) {
        AlertDialogSimpleDesign(
            text = stringResource(id = R.string.text_failure, status.failure),
            setCloseDialog = setCloseDialog,
            setActionButtonOK = onNavInitialMenu
        )
    }

    LaunchedEffect(status.flagAccess) {
        if(status.flagAccess) {
            if(flagAccessCard) onNavMenuDataInitial() else onNavInitialMenu()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPagePreview() {
    CAVTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            SplashContent(
                flagAccessCard = true,
                setCloseDialog = {},
                status = UiStatusState(),
                onNavInitialMenu = {},
                onNavMenuDataInitial = {},
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}