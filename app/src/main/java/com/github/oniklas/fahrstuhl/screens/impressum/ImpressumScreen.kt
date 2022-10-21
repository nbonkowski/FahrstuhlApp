package com.github.oniklas.fahrstuhl.screens.impressum

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.R

@Composable
fun ImpressumScreen(
    navController: NavHostController,
){
        Scaffold(
            topBar = {
                TopAppBar(modifier = Modifier, title = {Text(text = "Impressum")}, backgroundColor = MaterialTheme.colors.primary)
            },
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {
                Text(modifier = Modifier.fillMaxSize().padding(10.dp),text = stringResource(R.string.impressum)


                )
            }
}
}
