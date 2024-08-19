package com.asr.quietcapture.Screens

import CameraSettingScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.asr.quietcapture.R

@Composable
fun HomeScreen(navController: NavHostController) {
//    val navController = rememberNavController()
    Scaffold(
        topBar = { TopNavigationBar(navController) },
    ) { innerPadding ->
        NavHostContainer(navController, Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(navController: NavController) {
    TopAppBar(title = { R.string.app_name })

}
@Composable
fun NavHostContainer(navController: NavController, modifier: Modifier) {
    NavHost(navController as NavHostController, startDestination = "HomeScreen", modifier = modifier) {
        composable("HomeScreen") { HomeScreen(navController) }
        composable("RecordingScreen") { RecordingScreen(navController) }
        composable("RecordedScreen") { RecordedScreen(navController) }
        composable("CameraSettingsScreen") { CameraSettingScreen(navController) }
        composable("AboutMeScreen") { AboutMeScreen(navController) }
    }
}