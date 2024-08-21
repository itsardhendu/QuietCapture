package com.asr.quietcapture

import SettingScreen
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.asr.quietcapture.Screens.AboutMeScreen
import com.asr.quietcapture.Screens.HomeScreen
import com.asr.quietcapture.Screens.RecordedScreen
import com.asr.quietcapture.Screens.RecordingScreen
import com.asr.quietcapture.ui.theme.QuietCaptureTheme

class MainActivity : ComponentActivity() {
    private val REQUEST_PERMISSIONS = 1
    private val permissions = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        requestPermissionsIfNecessary()  // Request permissions here
        setContent {
            QuietCaptureTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    QuitedCaptureApp(innerPadding)
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
fun QuitedCaptureApp(innerPadding: PaddingValues) {
    val navController = rememberNavController()
    Scaffold{
        NavHost(
            navController as NavHostController,
            startDestination = "HomeScreen",
            modifier = Modifier
        ) {
            composable("HomeScreen") { HomeScreen(navController) }
            composable("RecordingScreen") { RecordingScreen(navController) }
            composable("RecordedScreen") { RecordedScreen(navController) }
            composable("SettingScreen") { SettingScreen(navController) }
            composable("AboutMeScreen") { AboutMeScreen(navController) }
        }
    }
}