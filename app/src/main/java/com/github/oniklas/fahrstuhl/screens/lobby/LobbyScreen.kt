package com.github.oniklas.fahrstuhl.screens.lobby

import androidx.compose.foundation.background
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.screens.lobby.widgets.lobbyItem
import com.github.oniklas.fahrstuhl.shared.NameInput

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens

@Composable
fun LobbyScreen(
    modifier: Modifier = Modifier,
    game : Game,
    players: List<Player> = emptyList(),
    navController: NavHostController,
    onAddPlayer: (Player) -> Unit,
    newGame: () -> Unit,
    onRemovePlayer: (Player) -> Unit
){
    var name by remember {
        mutableStateOf("")
    }
    var player_list by remember {
        mutableStateOf(emptyList<Player>())
    }
    fun add(){
        if(name.isNotEmpty()){
             player_list += (Player(name = name, game = game.id))
            //onAddPlayer(Players(name = name, game = game.id))
            name =""
        }
    }
    Card( Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)) {
    Column(
       Modifier.padding(10.dp)) {
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
            NameInput(text = name, label = "Name", onImeAction = {if ( player_list.size <= 7) {add()}  }, onTextChange = {
                if (it.all { char ->
                        char.isLetter() || char.isWhitespace()
                    }) name = it
            })
            Button(onClick = {
                add()
            }, enabled = player_list.size <= 7) {
                Text(text = "Add")
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { newGame();player_list.forEach { onAddPlayer(it) }; navController.navigate(FahrstuhlScreens.InGameScreen.name) },
                modifier = Modifier.padding(8.dp),
                enabled = !player_list.isNullOrEmpty() || !players.isNullOrEmpty()
            ) {
                Text(text = "Start Game")
            }
        }

    }
    }
}



