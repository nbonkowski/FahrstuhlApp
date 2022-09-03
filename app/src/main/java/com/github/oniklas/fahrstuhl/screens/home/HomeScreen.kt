package com.github.oniklas.fahrstuhl.screens.home


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens
import java.util.*

@Composable
fun HomeScreen(navController: NavHostController, toContinue : Boolean = false, firstTimeOpened: Boolean) {
    Scaffold(
        topBar = {
            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){

                LanguageButton("de",navController)
                LanguageButton("en", navController)

                Button(modifier = Modifier.padding(5.dp), onClick = {navController.navigate(FahrstuhlScreens.TutorialScreen.name)}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent), shape = CircleShape, elevation = null) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "info", tint = MaterialTheme.colors.primaryVariant)
                }
            }

            },
    )
    {

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

        //New Game
        Button(onClick = {navController.navigate(FahrstuhlScreens.LobbyScreen.name)}, shape = RoundedCornerShape(
            CornerSize(10.dp)
        )
        ) {

            Text(
                stringResource(R.string.new_game_desc), modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                textAlign = TextAlign.Center)
        }

        //Resume Game
        Button(onClick = {navController.navigate(FahrstuhlScreens.InGameScreen.name)}, enabled = toContinue, shape = RoundedCornerShape(
            CornerSize(10.dp)
        )
        ) {
            Text(
                stringResource(R.string.resume_game_desc), modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                textAlign = TextAlign.Center)
        }
    }
}
}

@Composable
fun LanguageButton(locale_str : String, navController : NavHostController){
    val context = LocalContext.current
    val locale = Locale(locale_str)
    Button(modifier = Modifier.padding(4.dp), onClick = {
        Locale.setDefault(locale)
        val resources = context.getResources()
        val configuration = resources.getConfiguration()
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        navController.navigate(FahrstuhlScreens.HomeScreen.name)
    }, colors = ButtonDefaults.textButtonColors()) {
       Text(text = if (locale_str == "de") {"\uD83C\uDDE9\uD83C\uDDEA"} else{"\uD83C\uDDEC\uD83C\uDDE7"}, fontSize = MaterialTheme.typography.h5.fontSize)
    }

}