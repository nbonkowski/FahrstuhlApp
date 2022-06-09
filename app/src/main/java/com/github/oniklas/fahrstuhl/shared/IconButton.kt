package com.github.oniklas.fahrstuhl.shared

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    modifier:Modifier =  Modifier,
    enabled :Boolean = true,
    imageVector: ImageVector = Icons.Rounded.Delete,
    onRemove:() -> Unit){
    IconButton(onClick = onRemove,enabled =enabled, modifier = modifier.border(1.dp, Color.DarkGray, shape = RoundedCornerShape(
        CornerSize(5.dp)))) {
        Icon(imageVector = imageVector, contentDescription = "delete icon")

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IconButton() {

    }
}


