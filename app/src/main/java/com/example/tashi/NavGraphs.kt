package com.example.tashi

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainFunction() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screens.Splash.route) {
            composable(Screens.Splash.route) {
                SplashScreen(navController)
            }
            composable(Screens.Home.route) {
                HomeScreen(navController)
            }
        }

    }
}