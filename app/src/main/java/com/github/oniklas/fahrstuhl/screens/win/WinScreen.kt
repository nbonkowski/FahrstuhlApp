package com.github.oniklas.fahrstuhl.screens.win

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens
import org.intellij.lang.annotations.JdkConstants

@Composable
fun WinScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    playerList: List<Player>
){
    Card(modifier = Modifier.fillMaxSize()) {


    Column(modifier = Modifier
        .padding(80.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier
            .fillMaxWidth()) {
            LazyColumn(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally){
                item {
                    Icon(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(), imageVector = Icons.Default.ThumbUp, contentDescription = "#1", tint = MaterialTheme.colors.onBackground)
                }
                itemsIndexed(playerList) { index, player: Player ->
                    Text(text = stringResource(R.string.result_text,player.name, index + 1, player.points), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                }
                item{
                    Button(onClick = {navController.navigate(FahrstuhlScreens.HomeScreen.name)}) {
                        Text(text = stringResource(R.string.back_to_menu))
                    }
                }
            }
        }
    }
    }
}