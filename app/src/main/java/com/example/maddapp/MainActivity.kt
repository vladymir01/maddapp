package com.example.maddapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.maddapp.ui.screen.MainApp
import com.example.maddapp.ui.theme.MaddAppTheme

//const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaddAppTheme {
                MainApp()
            }

        }
    }
}

