package com.github.oniklas.fahrstuhl.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.screens.datenschutzerklärung.DatenschutzerklaerungsScreen
import com.github.oniklas.fahrstuhl.screens.home.HomeScreen
import com.github.oniklas.fahrstuhl.screens.home.HomeViewModel
import com.github.oniklas.fahrstuhl.screens.impressum.ImpressumScreen
import com.github.oniklas.fahrstuhl.screens.ingame.InGameScreen
import com.github.oniklas.fahrstuhl.screens.ingame.InGameViewModel
import com.github.oniklas.fahrstuhl.screens.lobby.LobbyScreen
import com.github.oniklas.fahrstuhl.screens.lobby.LobbyViewModel
import com.github.oniklas.fahrstuhl.screens.tutorial.TutorialScreen
import com.github.oniklas.fahrstuhl.screens.win.WinScreen
import com.github.oniklas.fahrstuhl.screens.win.WinViewModel

@Composable
fun FahrstuhlNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController,
            startDestination = FahrstuhlScreens.HomeScreen.name
    )       {
        composable(FahrstuhlScreens.HomeScreen.name){
            val homeViewModel = hiltViewModel<HomeViewModel>()
            val game : Game = homeViewModel.game.collectAsState().value
            val firstTime : Boolean  = homeViewModel.isFirst.collectAsState().value
//            if(firstTime) {
//                navController.navigate(FahrstuhlScreens.TutorialScreen.name)
//            }
                HomeScreen(
                    navController = navController, toContinue = !firstTime && !game.finished, firstTimeOpened = firstTime
                )

        }

        composable(FahrstuhlScreens.LobbyScreen.name){
            val lobbyViewModel = hiltViewModel<LobbyViewModel>()
            LobbyScreen(
                navController = navController,
                game= lobbyViewModel.game.collectAsState().value,
                onAddPlayer = { lobbyViewModel.addPlayer(it)},
                newGame = { lobbyViewModel.newGame()},
                onRemovePlayer = {lobbyViewModel.removePlayer(it)}
            )
        }

        composable(FahrstuhlScreens.InGameScreen.name){
        val inGameViewModel = hiltViewModel<InGameViewModel>()
            InGameScreen(
                navController = navController,
                players = inGameViewModel.playerList.collectAsState().value,
                rounds = inGameViewModel.rounds.collectAsState().value,
                roundPlayers = inGameViewModel.roundPlayers.collectAsState().value,
                playerPoints = inGameViewModel.playerPoints.collectAsState().value,
                nextRound = {
                    inGameViewModel.nextRound()
                },
                addRoundPlayer = { round, player ->
                    inGameViewModel.addRoundPlayer(round,player)},
                updateRoundPlayer ={
                    inGameViewModel.updateRoundPlayer(it)
                },
                endGame = {inGameViewModel.endGame()}
            )
            //Test Screen
//            InGameScreen(players =
//            listOf(Players(name = "Maxim", game = UUID.randomUUID()),Players(name = "Maxim2", game = UUID.randomUUID())), rounds = listOf(
//                Round(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID()),Round(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID())
//            ), roundPlayers = listOf(listOf(RoundPlayerCrossRef(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),0,0)),
//                listOf(RoundPlayerCrossRef(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1),RoundPlayerCrossRef(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1))),
//                addRoundPlayer = {  rounds, players -> null
//
//                }){
//
//            }

        }

        composable(FahrstuhlScreens.TutorialScreen.name){
            TutorialScreen(navController)
        }

        composable(FahrstuhlScreens.ImpressumScreen.name){
            ImpressumScreen(navController = navController)
        }
        composable(FahrstuhlScreens.DatenschutzerklaerungsScreen.name){
            DatenschutzerklaerungsScreen(navController = navController)
        }
        
        composable(FahrstuhlScreens.WinningScreen.name){
            val winViewModel = hiltViewModel<WinViewModel>()
            WinScreen(navController = navController, playerList = winViewModel.playerList.collectAsState().value )
        }
    }
}