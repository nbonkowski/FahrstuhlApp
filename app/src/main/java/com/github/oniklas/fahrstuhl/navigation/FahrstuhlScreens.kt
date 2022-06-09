package com.github.oniklas.fahrstuhl.navigation

import java.lang.IllegalArgumentException

enum class FahrstuhlScreens {
    HomeScreen,
    TutorialScreen,
    LobbyScreen,
    InGameScreen,
    WinningScreen;

    companion object{
        fun fromRoute(route:String?): FahrstuhlScreens = when (route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            TutorialScreen.name -> TutorialScreen
            LobbyScreen.name -> LobbyScreen
            InGameScreen.name -> InGameScreen
            WinningScreen.name -> WinningScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not valid")
        }
    }
}