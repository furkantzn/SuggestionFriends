package com.example.suggestfriend.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.suggestfriend.ui.navigation.SetUpNavHost
import com.example.suggestfriend.ui.theme.SuggestFriendTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    SuggestFriendTheme {
        val navController = rememberNavController()
        SetUpNavHost(navController = navController)
    }
}