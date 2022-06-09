package com.github.oniklas.fahrstuhl.screens.tutorial

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.oniklas.fahrstuhl.R

@Composable
fun TutorialScreen(

){
    Scaffold(Modifier.padding(10.dp),
    topBar = {
        TopAppBar(modifier = Modifier, title = {Text(text = "Tutorial")}, backgroundColor = MaterialTheme.colors.primary)
    },
    content = { Text(modifier = Modifier.fillMaxSize(),text = stringResource(R.string.rules))},
    bottomBar = { BottomAppBar {

    }}
        )

}