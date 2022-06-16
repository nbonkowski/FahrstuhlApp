package com.github.oniklas.fahrstuhl.screens.ingame.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionField(
    modifier: Modifier = Modifier,
    maxLetters: Int = 10,
    text: String,
    borderColor: Color = Color.Black
){
    Surface(modifier = modifier
        .fillMaxWidth()
        ) {

        Row(Modifier.fillMaxWidth().height(IntrinsicSize.Max)) {

       Text(text = if (text.length> maxLetters){text.slice(0 until maxLetters)}else{text},fontSize = MaterialTheme.typography.body2.fontSize,fontWeight = MaterialTheme.typography.body2.fontWeight,   textAlign = TextAlign.Center, modifier = Modifier
           .fillMaxWidth()
           .padding(4.dp))

//        Divider(color = Color.Green, modifier = Modifier.fillMaxHeight().width(1.dp))
        }
    }


    Divider(color = borderColor, thickness = 1.dp )

}

@ExperimentalComposeUiApi
@Composable
fun InputField(
    modifier: Modifier = Modifier,
    text: String,
    borderColor: Color = Color.DarkGray,
    onTextChange: (String) -> Unit,
    onImeAction: () -> Unit = {},

){
    val keyboardController = LocalSoftwareKeyboardController.current
//    Surface( modifier = modifier.border(BorderStroke(1.dp, color = Color.Gray), shape = RectangleShape)) {

    BasicTextField(value = text, onValueChange = onTextChange,
        textStyle = MaterialTheme.typography.body2.copy(
            textAlign = TextAlign.Center
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
        keyboardType = KeyboardType.Number,
        imeAction =  ImeAction.Done
    ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
            .padding(4.dp)
    )
        Divider(color = borderColor, thickness = 1.dp )
}
//}
