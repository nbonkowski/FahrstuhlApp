package com.github.oniklas.fahrstuhl.screens.home


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens

@Composable
fun HomeScreen(navController: NavHostController, toContinue : Boolean = false, firstTimeOpened: Boolean) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

        Button(onClick = {navController.navigate(FahrstuhlScreens.LobbyScreen.name)}, shape = RoundedCornerShape(
            CornerSize(10.dp)
        )
        ) {
            Text("New Game", modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                textAlign = TextAlign.Center)
        }
        Button(onClick = {navController.navigate(FahrstuhlScreens.InGameScreen.name)}, enabled = toContinue, shape = RoundedCornerShape(
            CornerSize(10.dp)
        )
        ) {
            Text("Resume Game", modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                textAlign = TextAlign.Center)
        }
    }

}