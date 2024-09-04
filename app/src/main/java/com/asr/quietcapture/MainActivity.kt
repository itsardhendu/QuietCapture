package com.asr.quietcapture


import SettingsScreen
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asr.quietcapture.functionality.CameraPreview
import com.asr.quietcapture.screens.AboutMeScreen
import com.asr.quietcapture.screens.HomeScreen
import com.asr.quietcapture.screens.RecordedScreen
import com.asr.quietcapture.screens.RecordingScreen
import com.asr.quietcapture.ui.theme.QuietCaptureTheme

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    private val REQUEST_PERMISSIONS = 1
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestPermissionsIfNecessary()  // Request permissions here
        setContent {
            QuietCaptureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    QuiteCaptureApp()
                }
            }
        }
    }

    private fun requestPermissionsIfNecessary() {
        if (permissions.all { ContextCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED }) {
            // All permissions are already granted
        } else {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS)
        }
    }

    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)} passing\n      in a {@link RequestMultiplePermissions} object for the {@link ActivityResultContract} and\n      handling the result in the {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSIONS) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                // All permissions are granted
            } else {
                Toast.makeText(this, "Permissions are required to use the app", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun QuiteCaptureApp() {
    val navController = rememberNavController()
    HomeScreen(navController = navController)
    NavHost(navController = navController, startDestination = Screen.Home.route) {
        composable(Screen.Home.route) {

        }
        composable(Screen.Recording.route) {
            RecordingScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        composable(Screen.Recorded.route) {
            RecordedScreen(navController = navController)
        }
        composable(Screen.AboutMe.route) {
            AboutMeScreen(navController = navController)
        }

    }
}

sealed class Screen(val route: String) {
    data object Home : Screen("HomeScreen")
    data object Recording : Screen("RecordingScreen")
    data object Recorded : Screen("RecordedScreen")
    data object Settings : Screen("SettingsScreen")
    data object AboutMe : Screen("AboutMeScreen")
}