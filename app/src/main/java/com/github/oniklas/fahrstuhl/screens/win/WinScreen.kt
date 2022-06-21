package com.github.oniklas.fahrstuhl.screens.win

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.data.Player

@Composable
fun WinScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    playerList: List<Player>
){
    Card(modifier = Modifier.padding(80.dp).fillMaxSize()) {
        LazyColumn{
            items(playerList) { player: Player ->  
                Text(text = "${player.name} hat ${player.points} Punkte", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}