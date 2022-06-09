package com.github.oniklas.fahrstuhl.navigation

import android.util.Log
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
import dagger.hilt.android.lifecycle.HiltViewModel

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
            HomeScreen(navController = navController, toContinue = if (!firstTime){game.finished}else{false}, firstTimeOpened = firstTime)
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
    }
}