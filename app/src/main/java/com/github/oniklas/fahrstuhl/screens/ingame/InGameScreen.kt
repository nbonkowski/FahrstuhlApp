package com.github.oniklas.fahrstuhl.screens.ingame


import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.github.oniklas.fahrstuhl.R
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.data.RoundPlayerCrossRef
import com.github.oniklas.fahrstuhl.data.Round
import com.github.oniklas.fahrstuhl.navigation.FahrstuhlScreens
import com.github.oniklas.fahrstuhl.screens.ingame.widgets.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.math.abs
import kotlin.math.round

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun InGameScreen(
    navController: NavHostController,
    players: List<Player>,
    rounds: List<Round>,
    roundPlayers: HashMap<UUID,List<RoundPlayerCrossRef>>,
    playerPoints: HashMap<UUID, Int>,
    addRoundPlayer: (Round, Player) -> Unit,
    nextRound: () ->Unit,
    updateRoundPlayer: (RoundPlayerCrossRef) ->Unit,
    endGame: () -> Unit,
)
{
    val extraRounds : Int = players.size
    Scaffold(
        content = {

    Surface(  Modifier.verticalScroll(rememberScrollState())) {

        LazyRow(Modifier.fillMaxWidth()) {
            stickyHeader {
                Column(
                    Modifier
                        .width(intrinsicSize = IntrinsicSize.Max)
                        .height(intrinsicSize = IntrinsicSize.Min)
                        .background(MaterialTheme.colors.surface)
                ) {
                    DescriptionField(
                        text = getRandomColor().symbol,
                        //text = stringResource(R.string.name_description),
                        modifier = Modifier.fillMaxWidth()
                    )
                    for (round in rounds) {
                        Column(
                            modifier = Modifier.background(
                                if (round.round % 2 == 0) {
                                    MaterialTheme.colors.background
                                } else {
                                    MaterialTheme.colors.surface
                                }
                            )
                        ) {

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
                            roundDescriptionItem(
                                text = "Points",
                            )
                        }

                    }
                    Row(
                        modifier = Modifier
                            .background(
                                if (rounds.size % 2 == 0) {
                                    MaterialTheme.colors.background
                                } else {
                                    MaterialTheme.colors.surface
                                }
                            )
                    ) {
                        DescriptionField(
                            text = stringResource(R.string.points_description),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    ItemDivider()
                }
            }

            itemsIndexed(players) { index, player ->
                Column(
                    Modifier
                        .width(intrinsicSize = IntrinsicSize.Max)
                        .defaultMinSize(minWidth = 80.dp, 10.dp)
                    //   Uncomment to mark column in different colors
//                        .background(
//                            if (index % 2 == 0) {
//                                MaterialTheme.colors.background
//                            } else {
//                                MaterialTheme.colors.surface
//                            }
//                        )
                ) {
                    DescriptionField(text = player.name, modifier = Modifier.fillMaxWidth())
                    if (!roundPlayers.isNullOrEmpty() && !roundPlayers[player.id].isNullOrEmpty()) {
                        for ((round_index, round) in roundPlayers[player.id]!!.iterator()
                            .withIndex()) {//!! call because of isNull check above
                            Column(modifier =  Modifier.background(if (round_index %2 == 0){MaterialTheme.colors.background}else{MaterialTheme.colors.surface})){
                            roundDescriptionItem(
                                text = if (index == 0) {
                                    "Cards ${
                                        when (round_index) {
                                            in 0..5 -> round_index * 2 + 1
                                            in 6..5 + extraRounds -> 13
                                            in 5 + extraRounds..11 + extraRounds -> 13 - (round_index - 5 - extraRounds) * 2
                                            else -> {
                                                0
                                            }
                                        }

                                    }"
                                } else {
                                    ""
                                },
                            )
                            /*Input field for Prediction */
                            var round_prediction by remember {
                                mutableStateOf(round.prediction.toString())
                            }

                            var round_trick by remember {
                                mutableStateOf(round.trick.toString())
                            }

                            InputField(text = round_prediction, onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    }) round_prediction = it
                            }) {
                                updateRoundPlayer(
                                    round.copy(
                                        prediction = round_prediction.toInt(),
                                        trick = round_trick.toInt(),
                                        points = getPoints(round_prediction.toInt(), round_trick.toInt())
                                    )
                                )
                            }


                            /*Input field for Trick */
                            InputField(text = round_trick, onTextChange = {
                                if (it.all { char ->
                                        char.isDigit()
                                    }) round_trick = it
                            }) {
                                Log.d("imeAction", "imeAction")
                                updateRoundPlayer(
                                    round.copy(
                                        prediction = round_prediction.toInt(),
                                        trick = round_trick.toInt(),
                                        points = getPoints(round_prediction.toInt(), round_trick.toInt())
                                    )
                                )
                            }
                            roundDescriptionItem(
                                text = round.points.toString(),
                            )
                        }
                        }
                    }
                    Column( modifier = Modifier
                        .background(
                            if (rounds.size % 2 == 0) {
                                MaterialTheme.colors.background
                            } else {
                                MaterialTheme.colors.surface
                            }
                        )) {


                    DescriptionField(
                        text = player.points.toString(),//playerPoints[player.id].toString(),
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }}
            }
        }
    }

    },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton ={if (rounds.size < 12 + players.size) {

                FloatingActionButton(onClick = { nextRound() }) {
                    Text(modifier = Modifier.padding(10.dp), text = stringResource(R.string.next_round_text))
                }
            } else{
            FloatingActionButton(onClick = { endGame();navController.navigate(FahrstuhlScreens.WinningScreen.name) }) {
                Text(modifier = Modifier.padding(10.dp), text = stringResource(R.string.finish_text))
            }
        }
        }

    )
}

@Composable
fun roundDescriptionItem(text: String) {
    DescriptionField(
        text =  text,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        fontSize = 10.sp,
        padding = 0.dp
    )
}

fun getPoints(prediction: Int, trick: Int): Int {
    var points: Int  = 0
    if(prediction - trick == 0 ){
        points += if (prediction != 0){ prediction * 5}else{5}

    }else {points -= abs(prediction - trick) * 2}
    return points
}

//@Preview(showBackground = true)
//@Composable
//fun IngameScreenPreview(){
//    InGameScreen(players =
//    listOf(Players(name = "Maxim", game = UUID.randomUUID()),Players(name = "Maxim2", game = UUID.randomUUID())), rounds = listOf(
//        Round(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID()),Round(game = UUID.randomUUID(), round = 1, firstPlayer = UUID.randomUUID())
//    ), roundPlayers = listOf(listOf(RoundPlayerCrossRef(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),0,0)),
//        listOf(RoundPlayerCrossRef(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1),RoundPlayerCrossRef(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),1,1))),
//        addRoundPlayer = {  rounds, players -> null
//
//        }){
//
//    }
//
//}