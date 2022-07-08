package com.github.oniklas.fahrstuhl.screens.ingame.widgets

import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

public enum class CardColors(val symbol: String){
    Diamonds(symbol= "♦"),
    Clubs(symbol= "♣"),
    Spades(symbol= "♠"),
    Hearts(symbol= "♥")
}

fun getRandomColor():CardColors{
    return CardColors.values()[Random.nextInt(0,4)]
}

@Composable
fun RandomColorIcon(){
    val color: CardColors = CardColors.values()[Random.nextInt(0,4)]
    Text(text = color.symbol)
}

@Preview(showBackground = true)
@Composable
fun RandomColorIconPreview(){
    RandomColorIcon()
}