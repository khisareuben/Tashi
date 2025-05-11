package com.example.tashi

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route : String) {
    @Serializable
    data object Splash : Screens("splash_route")
    @Serializable
    data object Home : Screens("home_route")

}