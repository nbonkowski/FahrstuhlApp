package com.github.oniklas.fahrstuhl.screens.tutorial

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens

@Composable
fun TutorialScreen(
    navController: NavHostController,
){
    Scaffold(
    topBar = {
        TopAppBar(modifier = Modifier, title = {Text(text = "Tutorial")}, backgroundColor = MaterialTheme.colors.primary)
    },
    content = { Card(Modifier.padding(10.dp)) {
        Text(modifier = Modifier.fillMaxSize(),text = stringResource(R.string.rules))
    }},
    bottomBar = { BottomAppBar{
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            FloatingActionButton(onClick = { navController.navigate(FahrstuhlScreens.LobbyScreen.name)}, shape = RoundedCornerShape(5.dp), modifier = Modifier.padding(10.dp),  backgroundColor = MaterialTheme.colors.secondary) {
                Text(modifier = Modifier.padding(5.dp), text = "New Game")
            }
        }
    }}
        )

}