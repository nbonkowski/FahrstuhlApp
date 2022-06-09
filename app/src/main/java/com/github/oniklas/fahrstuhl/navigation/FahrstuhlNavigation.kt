package com.github.oniklas.fahrstuhl.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.oniklas.fahrstuhl.data.Games
import com.github.oniklas.fahrstuhl.screens.home.HomeScreen
import com.github.oniklas.fahrstuhl.screens.home.HomeViewModel
import com.github.oniklas.fahrstuhl.screens.lobby.LobbyScreen
import com.github.oniklas.fahrstuhl.screens.lobby.LobbyViewModel
import com.github.oniklas.fahrstuhl.screens.tutorial.TutorialScreen

@Composable
fun FahrstuhlNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = FahrstuhlScreens.HomeScreen.name
    )       {
        composable(FahrstuhlScreens.HomeScreen.name){
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val game : Games = homeViewModel.game.collectAsState().value
            val firstTime : Boolean = game == null
            if(firstTime){
                TutorialScreen(navController)
            }else {
                HomeScreen(
                    navController = navController, toContinue = if (!firstTime) {
                        game.finished
                    } else {
                        false
                    }, firstTimeOpened = firstTime
                )
            }
        }

        composable(FahrstuhlScreens.LobbyScreen.name){
            val lobbyViewModel = hiltViewModel<LobbyViewModel>()
            LobbyScreen(
                navController = navController,
                game= lobbyViewModel.game.collectAsState().value,
                onAddPlayer = { lobbyViewModel.addPlayer(it)},
                newGame = { lobbyViewModel.newGame(it)},
                onRemovePlayer = {lobbyViewModel.removePlayer(it)}
            )
        }

        composable(FahrstuhlScreens.InGameScreen.name){

        }

        composable(FahrstuhlScreens.TutorialScreen.name){
            TutorialScreen(navController)
        }
    }
}