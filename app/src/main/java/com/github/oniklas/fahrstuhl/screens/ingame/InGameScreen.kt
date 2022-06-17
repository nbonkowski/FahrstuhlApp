package com.github.oniklas.fahrstuhl.screens.ingame


import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.Rounds
import com.github.oniklas.fahrstuhl.screens.ingame.widgets.DescriptionField
import com.github.oniklas.fahrstuhl.screens.ingame.widgets.InputField
import java.util.*

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun InGameScreen(
    players: List<Players>,
    rounds: List<Rounds>,
    roundPlayers: HashMap<UUID,List<RoundPlayer>>,
    addRoundPlayer: (Rounds, Players) -> Unit,
    nextRound: () ->Unit,
    updateRoundPlayer: (RoundPlayer) ->Unit,
)
{
    @Composable
    fun roundDescriptionItem(text: String) {
        DescriptionField(
        text =  text,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 10.sp,
        padding = 0.dp
    )
    }
    val extraRounds : Int = players.size
    Surface(  Modifier.verticalScroll(rememberScrollState())){
    LazyRow(Modifier.fillMaxWidth()){
        stickyHeader {
            Column(
                Modifier
                    .width(intrinsicSize = IntrinsicSize.Max)
                    .height(intrinsicSize = IntrinsicSize.Min)) {
                DescriptionField(
                    text = stringResource(R.string.name_description),
                    modifier = Modifier.fillMaxWidth()
                )
                for (round in rounds) {
                    roundDescriptionItem(
                        text = "Round " + round.round.toString(),
                    )
                    DescriptionField(
                        text = stringResource(R.string.prediction_description),
                        modifier = Modifier.fillMaxWidth()
                    )
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
            Divider(color = Color.Black, modifier = Modifier
                .fillParentMaxHeight()
                .width(1.dp))
        }

        itemsIndexed(players){ index, player ->
            Column(
                Modifier
                    .width(intrinsicSize = IntrinsicSize.Max)
                    .defaultMinSize(minWidth = 80.dp, 10.dp)
                    ) {
                DescriptionField(text = player.name, modifier = Modifier.fillMaxWidth())
                if(!roundPlayers.isNullOrEmpty() && !roundPlayers[player.id].isNullOrEmpty()){
                        for ((round_index,round) in roundPlayers[player.id]!!.iterator().withIndex()) {//!! call because of isNull check above
                            roundDescriptionItem(
                                text = if (index == 0){"Cards ${when(round_index){
                                    in 0..5 -> round_index * 2 + 1
                                    in 6..5+extraRounds -> 13
                                    in 5+extraRounds..11+extraRounds -> 13- (round_index-5-extraRounds)*2
                                    else -> {0}
                                }
                                    
                                }"}else{""},
                            )
                            /*Input field for Prediction */
                            var round_prediction by remember {
                                mutableStateOf(round.prediction.toString())
                            }
                            InputField(text = round_prediction, onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    }) round_prediction = it
                            }) {
                                updateRoundPlayer(round.copy(
                                    prediction = round_prediction.toInt()
                                ))
                            }
                            var round_trick by remember {
                                mutableStateOf(round.trick.toString())
                            }

                            /*Input field for Trick */
                            InputField(text = round_trick, onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    }) round_trick = it
                            }) {
                                updateRoundPlayer(round.copy(
                                    trick = round_trick.toInt()
                                ))
                            }
                        }
                }
                DescriptionField(
                    text = "0",//TODO Replace with points
                    modifier = Modifier.fillMaxWidth()
                )
            }
//            Divider(color = Color.DarkGray, modifier = Modifier
//                .fillParentMaxHeight()
//                .width(1.dp))
        }
    }
}
    Button(onClick = {nextRound()}) {
        Text(text = "Next Round")
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