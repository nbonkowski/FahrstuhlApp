package com.github.oniklas.fahrstuhl.screens.ingame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.data.RoundPlayerCrossRef
import com.github.oniklas.fahrstuhl.data.Round
import com.github.oniklas.fahrstuhl.repositorys.GameRepository
import com.github.oniklas.fahrstuhl.repositorys.PlayerRepository
import com.github.oniklas.fahrstuhl.repositorys.RoundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap
import kotlin.math.abs

@HiltViewModel
class InGameViewModel @Inject constructor(private val gameRepository: GameRepository,
                                          private val playerRepository : PlayerRepository,
                                          private val roundRepository: RoundRepository,
) : ViewModel() {

    private var _game = MutableStateFlow<Game>(Game())
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Player>>(emptyList())
    val playerList = _playerList.asStateFlow()

    private var _rounds = MutableStateFlow<List<Round>>(emptyList())
    val rounds = _rounds.asStateFlow()

    private var _roundPlayers = MutableStateFlow<HashMap<UUID,List<RoundPlayerCrossRef>>>(HashMap<UUID,List<RoundPlayerCrossRef>>())
    val roundPlayers = _roundPlayers.asStateFlow()

    private var _playerPoints = MutableStateFlow<HashMap<UUID, Int>>(HashMap())
    val playerPoints = _playerPoints.asStateFlow()

    init {
        //initialising States
        runBlocking(Dispatchers.IO) {
            _game.value = gameRepository.getLastGame().first()
            _playerList.value = playerRepository.getAllPlayersFromGameId(_game.value.id).first()
            _rounds.value =  roundRepository.getRoundsOfGame(_game.value.id).first()

            if(_rounds.value.isNullOrEmpty()){
                    nextRound()

            }
//            _playerList.value.forEach { player ->
//                _roundPlayers.value[player.id] = roundRepository.getAllRoundsOfPlayer(player.id).first()
//            }
        }
    // subscribe to Flow events
        viewModelScope.launch(Dispatchers.IO){
            launch(Dispatchers.IO) {
//                roundRepository.removeRounds()
                gameRepository.getLastGame()
                    .distinctUntilChanged().collect {
                    _game.value = it
                }

            }
           launch(Dispatchers.IO) {
                playerRepository.getAllPlayersFromGameId(_game.value.id).distinctUntilChanged()
                    .collect {
                        _playerList.value = it

                    }
                }
            launch(Dispatchers.IO) {

                roundRepository.getRoundsOfGame(_game.value.id).distinctUntilChanged().collect {
                        _rounds.value = it

                    }
            }
            launch(Dispatchers.IO) {
                        roundRepository.getAllRoundPlayer().distinctUntilChanged().collect{
                            _playerList.value.forEach { player ->
                                _roundPlayers.value[player.id] = playerRepository.getPlayerWithRounds(player.id).last().roundPlayerCrossRef
                                var points = 0
                                _roundPlayers.value[player.id]!!.forEach{
                                     if(abs(it.prediction - it.trick) == 0 ){
                                         points += if (it.prediction > 0){ it.prediction * 5}else{5}

                                    }else {points -= abs(it.prediction - it.trick) * 2}
                                }
                                _playerPoints.value[player.id] = points
                            }
                        }
            }
        }
    }


    fun nextRound() = viewModelScope.launch{
        val newRound  = Round(game = _game.value.id, firstPlayer = _playerList.value[_rounds.value.size%_playerList.value.size].id, round = _rounds.value.size)
        roundRepository.insertRound(newRound)

        _playerList.value.forEach { player ->
            roundRepository.addRoundPlayer(RoundPlayerCrossRef(player = player.id, round = newRound.id , prediction = 0, trick = 0 ))
        }
    }

    fun updateRoundPlayer(roundPlayer: RoundPlayerCrossRef) = viewModelScope.launch {
            roundRepository.updateRoundPlayer(roundPlayer)
        }

    fun addRound() = viewModelScope.launch {
        roundRepository.insertRound(rounds.value[rounds.value.size].copy(
            round = rounds.value.size + 1,
            firstPlayer = playerList.value[rounds.value.size + 1 % playerList.value.size].id
        ))
    }

    fun addRoundPlayer(round : Round, player: Player)= viewModelScope.launch {
        roundRepository.addRoundPlayer(RoundPlayerCrossRef( round = round.id, player = player.id))
    }

}