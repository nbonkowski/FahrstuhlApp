package com.github.oniklas.fahrstuhl.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.oniklas.fahrstuhl.screens.home.HomeScreen

@Composable
fun FahrstuhlNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = FahrstuhlScreens.HomeScreen.name
    )       {
        composable(FahrstuhlScreens.HomeScreen.name){
            HomeScreen(navController = navController, toContinue = false)/*ToDo replace toContinue dynamic*/
        }
    }
}