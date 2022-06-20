package com.github.oniklas.fahrstuhl.screens.lobby.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.shared.IconButton

@Composable
fun lobbyItem(
    player: Player,
    modifier: Modifier = Modifier,
    onRemove:() -> Unit
){
    Row(modifier= modifier,verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text=player.name, modifier = Modifier.padding(10.dp).width(200.dp), style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.padding(8.dp))
        IconButton(modifier, onRemove = onRemove)
    }
}