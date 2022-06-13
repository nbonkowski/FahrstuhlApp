package com.github.oniklas.fahrstuhl.screens.ingame


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.Rounds
import java.util.*

@Composable
fun InGameScreen(
    players: List<Players>,
    rounds: List<Rounds>,
    roundPlayers: List<List<RoundPlayer>>,
    addRoundPlayer: (Rounds, Players) -> Unit,
)
{
    LazyRow(Modifier.fillMaxWidth()){
        item {
            Column(
                Modifier
                    .width(intrinsicSize = IntrinsicSize.Max)
                    .padding(10.dp)) {
                Text(text = stringResource(R.string.name_description), modifier = Modifier.fillMaxWidth())
                for (round in rounds){
                    Text(text = stringResource(R.string.prediction_description), modifier = Modifier.fillMaxWidth()) //TODO Slice String after X letters
                }
            }
        }
        itemsIndexed(players){ index, player ->
            Column(
                Modifier
                    .width(intrinsicSize = IntrinsicSize.Max)
                    .padding(10.dp)) {
                Text(text = player.name.toString(), modifier = Modifier.fillMaxWidth()) //TODO Slice Name after X letters
                for (round in roundPlayers[index]){
                    Text(text = round.prediction.toString(), modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun IngameScreenPreview(){
    InGameScreen(players =
        listOf(Players(name = "Maxim", game = UUID.randomUUID()),Players(name = "Maxim2", game = UUID.randomUUID())), rounds = listOf(
        Rounds(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID()),Rounds(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID())
    ), roundPlayers = listOf(listOf(RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),0,0)),
        listOf(RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1),RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1)))){
        rounds, players -> null
    }

}