package com.github.oniklas.fahrstuhl.screens.ingame


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.Rounds
import com.github.oniklas.fahrstuhl.screens.ingame.widgets.DescriptionField
import com.github.oniklas.fahrstuhl.screens.ingame.widgets.InputField
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InGameScreen(
    players: List<Players>,
    rounds: List<Rounds>,
    roundPlayers: HashMap<UUID,List<RoundPlayer>>,
    addRoundPlayer: (Rounds, Players) -> Unit,
    updateRoundPlayer: (RoundPlayer) ->Unit,
)
{
    LazyRow(Modifier.fillMaxWidth()){
        item {
            Column(
                Modifier
                    .width(intrinsicSize = IntrinsicSize.Max)
                    .height(intrinsicSize = IntrinsicSize.Min)) {
                DescriptionField(
                    text = stringResource(R.string.name_description),
                    modifier = Modifier.fillMaxWidth()
                )
                for (round in rounds) {
                    DescriptionField(
                        text = stringResource(R.string.prediction_description),
                        modifier = Modifier.fillMaxWidth()
                    ) //TODO Slice String after X letters
                    DescriptionField(
                        text = stringResource(R.string.trick_description),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                DescriptionField(
                    text = stringResource(R.string.points_description),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Divider(color = Color.Black, modifier = Modifier.fillParentMaxHeight().width(1.dp))
        }

        itemsIndexed(players){ index, player ->
            Column(
                Modifier
                    .width(intrinsicSize = IntrinsicSize.Max)
                    ) {
                DescriptionField(text = player.name, modifier = Modifier.fillMaxWidth()) //TODO Slice Name after X letters
                if(!roundPlayers.isNullOrEmpty() && !roundPlayers[player.id].isNullOrEmpty()){
                        for (round in roundPlayers.get(player.id)!!) {//!! call because of isNull check above
                            var round_prediction by remember {
                                mutableStateOf(round.prediction.toString())
                            }
                            InputField(text = round_prediction, onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    }) round_prediction = it
                            }) {

                            }
                            var round_trick by remember {
                                mutableStateOf(round.prediction.toString())
                            }
                            InputField(text = round_trick, onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    }) round_trick = it
                            }) {

                            }
                        }
                }
                DescriptionField(
                    text = "0",//TODO Replace with points
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Divider(color = Color.DarkGray, modifier = Modifier.fillParentMaxHeight().width(1.dp))
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun IngameScreenPreview(){
//    InGameScreen(players =
//    listOf(Players(name = "Maxim", game = UUID.randomUUID()),Players(name = "Maxim2", game = UUID.randomUUID())), rounds = listOf(
//        Rounds(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID()),Rounds(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID())
//    ), roundPlayers = listOf(listOf(RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),0,0)),
//        listOf(RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1),RoundPlayer(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1))),
//        addRoundPlayer = {  rounds, players -> null
//
//        }){
//
//    }
//
//}