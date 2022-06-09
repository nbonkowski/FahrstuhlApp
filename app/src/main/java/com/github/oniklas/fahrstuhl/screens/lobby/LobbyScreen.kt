package com.github.oniklas.fahrstuhl.screens.lobby

import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.screens.lobby.widgets.lobbyItem
import com.github.oniklas.fahrstuhl.shared.NameInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.data.Games
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens

@Composable
fun LobbyScreen(
    modifier: Modifier = Modifier,
    game : Games = Games(),
    players: List<Players> = emptyList(),
    navController: NavHostController,
    onAddPlayer: (Players) -> Unit,
    newGame: (Games) -> Unit,
    onRemovePlayer: (Players) -> Unit
){
    var name by remember {
        mutableStateOf("")
    }
    var player_list by remember {
        mutableStateOf(emptyList<Players>())
    }
    fun add(){
        if(name.isNotEmpty()){
             player_list += (Players(name = name, game = game.id))
            //onAddPlayer(Players(name = name, game = game.id))
            name =""
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(10.dp)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(listOf(players,player_list).flatten()) { player ->
                lobbyItem(modifier = Modifier.padding(2.dp), player = player, onRemove = {
                    player_list -= player
                })
            }
        }
        Divider(modifier = Modifier.padding(5.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            NameInput(text = name, label = "Name", onImeAction = {add()}, onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) name = it
            })

            Button(onClick = {
                add()

            }) {
                Text(text = "Add")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { newGame(game); player_list.forEach { onAddPlayer(it) }; navController.navigate(FahrstuhlScreens.InGameScreen.name) },
                modifier = Modifier.padding(8.dp),
                enabled = !player_list.isNullOrEmpty() || !players.isNullOrEmpty()
            ) {
                Text(text = "Start Game")
            }
        }

    }

}



