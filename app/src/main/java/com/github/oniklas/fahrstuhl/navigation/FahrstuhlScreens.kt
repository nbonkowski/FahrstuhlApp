package com.github.oniklas.fahrstuhl.navigation

import com.github.oniklas.fahrstuhl.screens.datenschutzerklärung.DatenschutzerklaerungsScreen
import java.lang.IllegalArgumentException

enum class FahrstuhlScreens {
    HomeScreen,
    TutorialScreen,
    LobbyScreen,
    InGameScreen,
    ImpressumScreen,
    DatenschutzerklaerungsScreen,
    WinningScreen;

    companion object{
        fun fromRoute(route:String?): FahrstuhlScreens = when (route?.substringBefore("/")){
            HomeScreen.name -> HomeScreen
            TutorialScreen.name -> TutorialScreen
            LobbyScreen.name -> LobbyScreen
            InGameScreen.name -> InGameScreen
            WinningScreen.name -> WinningScreen
            ImpressumScreen.name -> ImpressumScreen
            DatenschutzerklaerungsScreen.name -> DatenschutzerklaerungsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not valid")
        }
    }
}