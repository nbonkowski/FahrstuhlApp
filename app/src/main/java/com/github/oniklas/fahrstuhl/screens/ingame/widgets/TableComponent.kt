package com.github.oniklas.fahrstuhl.screens.ingame.widgets

import android.inputmethodservice.Keyboard
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.shared.NameInput

@Composable
fun RowScope.TableComponent(
    text: String,
    weight: Float,
    bordercolor : Color = Color.Black
){
    Text(
        text = text,
        Modifier
            .border(1.dp, bordercolor)
            .weight(weight)
            .padding(8.dp)
    )
}

@Composable
fun RowScope.RoundPlayerTableComponent(
    roundPlayer: RoundPlayer,
    weight: Float,
){
    Column(Modifier.fillMaxWidth()) {
    Text(
        text = roundPlayer.prediction.toString(),
        Modifier
            .border(1.dp, Color.Gray)
            .weight(weight)
            .padding(8.dp)
    )
    Text(
        text = roundPlayer.trick.toString(),
        Modifier
            .border(1.dp, Color.Gray)
            .weight(weight)
            .padding(8.dp)
    )
    }
}

@Composable
fun TableItem(modifier: Modifier, roundPlayer: RoundPlayer, onItemClick: () -> Unit){
    Column(modifier = modifier){
        NameInput(text = roundPlayer.prediction.toString(), label = "", onTextChange = {})
        NameInput(text = roundPlayer.trick.toString(), label = "", onTextChange = {})
    }
}