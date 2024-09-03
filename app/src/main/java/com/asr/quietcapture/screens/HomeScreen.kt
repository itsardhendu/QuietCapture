package com.asr.quietcapture.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.asr.quietcapture.R
import com.asr.quietcapture.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = { TopNavigationBar() },
        content = { paddingValues ->

            ButtonGrid(navController = navController, modifier = Modifier.padding(paddingValues))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), fontSize = 20.sp) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.background,
        ),
    )
}
@Composable
fun ButtonGrid(navController: NavHostController, modifier: Modifier) {
    val buttons = listOf(
        "RecordingScreen" to R.drawable.videocam_24px,
        "RecordedScreen" to R.drawable.save_24px,
        "SettingsScreen" to R.drawable.settings_24px,
        "AboutMeScreen" to R.drawable.code_24px
    )

    val buttonWidth = 150.dp
    Column(
        modifier = Modifier
            .padding(top = 600.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        buttons.chunked(2).forEach { rowButtons ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                rowButtons.forEach { (screen, icon) ->
                    FilledTonalButton(
                        onClick = {
                            when (screen) {
                                "RecordingScreen" -> navController.navigate(Screen.Recording.route)
                                "RecordedScreen" -> navController.navigate(Screen.Recorded.route)
                                "SettingsScreen" -> navController.navigate(Screen.Settings.route)
                                "AboutMeScreen" -> navController.navigate(Screen.AboutMe.route)
                            }
                        },
                        modifier = Modifier.size(width = buttonWidth, height = 48.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = screen.replace("Screen", ""),
                            modifier = Modifier
                                .padding(end = 2.dp)
                                .size(24.dp)
                        )
                        Text(text = screen.replace("Screen", ""))
                    }
                }
            }
        }
    }
}

