package com.github.oniklas.fahrstuhl.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.oniklas.fahrstuhl.data.Games
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.Rounds
import com.github.oniklas.fahrstuhl.screens.home.HomeScreen
import com.github.oniklas.fahrstuhl.screens.home.HomeViewModel
import com.github.oniklas.fahrstuhl.screens.ingame.InGameScreen
import com.github.oniklas.fahrstuhl.screens.ingame.InGameViewModel
import com.github.oniklas.fahrstuhl.screens.lobby.LobbyScreen
import com.github.oniklas.fahrstuhl.screens.lobby.LobbyViewModel
import com.github.oniklas.fahrstuhl.screens.tutorial.TutorialScreen
import java.util.*

@Composable
fun FahrstuhlNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = FahrstuhlScreens.HomeScreen.name
    )       {
        composable(FahrstuhlScreens.HomeScreen.name){
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val game : Games = homeViewModel.game.collectAsState().value
            val firstTime : Boolean = false
            if(firstTime){
                TutorialScreen(navController)
            }else {
                HomeScreen(
                    navController = navController, toContinue = if (!firstTime) {
                        true
                    } else {
                        true
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
        val inGameViewModel = hiltViewModel<InGameViewModel>()
            InGameScreen(
                players = inGameViewModel.playerList.collectAsState().value,
                rounds = inGameViewModel.rounds.collectAsState().value,
                roundPlayers = inGameViewModel.roundPlayers.collectAsState().value,
                addRoundPlayer = { round, player ->
                    inGameViewModel.addRoundPlayer(round,player)},
                updateRoundPlayer ={
                    inGameViewModel.updateRoundPlayer(it)
                }
            )
            //Test Screen
//            InGameScreen(players =
//            listOf(Players(name = "Maxim", game = UUID.randomUUID()),Players(name = "Maxim2", game = UUID.randomUUID())), rounds = listOf(
//                Rounds(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID()),Rounds(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID())
//            ), roundPlayers = listOf(listOf(RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),0,0)),
//                listOf(RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1),RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1))),
//                addRoundPlayer = {  rounds, players -> null
//
//                }){
//
//            }

        }

        composable(FahrstuhlScreens.TutorialScreen.name){
            TutorialScreen(navController)
        }
    }
}