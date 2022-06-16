package com.github.oniklas.fahrstuhl.screens.ingame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Games
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.Rounds
import com.github.oniklas.fahrstuhl.repositorys.GameRepository
import com.github.oniklas.fahrstuhl.repositorys.PlayerRepository
import com.github.oniklas.fahrstuhl.repositorys.RoundPlayerRepository
import com.github.oniklas.fahrstuhl.repositorys.RoundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

@HiltViewModel
class InGameViewModel @Inject constructor(private val gameRepository: GameRepository,
                                          private val playerRepository : PlayerRepository,
                                          private val roundRepository: RoundRepository,
                                          private val roundPlayerRepository: RoundPlayerRepository
) : ViewModel() {

    private var _game = MutableStateFlow<Games>(Games())
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Players>>(emptyList())
    val playerList = _playerList.asStateFlow()

    private var _rounds = MutableStateFlow<List<Rounds>>(emptyList())
    val rounds = _rounds.asStateFlow()

    private var _roundPlayers = MutableStateFlow<HashMap<UUID,List<RoundPlayer>>>(HashMap<UUID,List<RoundPlayer>>())
    val roundPlayers = _roundPlayers.asStateFlow()


    init {
        //initialising States
        runBlocking(Dispatchers.IO) {
            _game.value = gameRepository.getLastGame().first()
            _playerList.value = playerRepository.getAllPlayersFromGameId(_game.value.id).first()
            _rounds.value =  roundRepository.getRoundsOfGame(_game.value.id).first()

            if(_rounds.value.isNullOrEmpty()){
               val newRound  = Rounds(game = _game.value.id, firstPlayer = _playerList.value[0].id, round = 0)

                roundRepository.insertRound(newRound)
                _rounds.value  = roundRepository.getRoundsOfGame(_game.value.id).first()

                _playerList.value.forEach { player ->
                     roundPlayerRepository.addRoundPlayer(RoundPlayer(player.id, _rounds.value[0].id, prediction = 0, trick = 0 ))
                }
            }
            _playerList.value.forEach { player ->
                _roundPlayers.value[player.id] = roundPlayerRepository.getAllRoundsOfPlayer(player.id).first()
            }
        }
    // subscribe to Flow events
        viewModelScope.launch(Dispatchers.IO){
            launch(Dispatchers.IO) {
//                roundRepository.removeRounds()
                gameRepository.getLastGame()
                    .distinctUntilChanged().onEach {
                    _game.value = it
                }

            }
           launch(Dispatchers.IO) {
                playerRepository.getAllPlayersFromGameId(_game.value.id).distinctUntilChanged()
                    .onEach {
                        _playerList.value = it

                    }
            }
            launch(Dispatchers.IO) {
                roundRepository.getRoundsOfGame(_game.last().id).distinctUntilChanged()
                    .onEach {
                        _rounds.value = it
                    }
            }
            launch(Dispatchers.IO) {
                _playerList.value.forEach { player ->
                        roundPlayerRepository.getAllRoundsOfPlayer(playerid = player.id).distinctUntilChanged().collect{
                            _roundPlayers.value[player.id] = it
                        }
                }
            }
        }
    }


//Todo create createRound Function
//    fun createRound(game: Games, firstPlayer: Players, round: Int){
//        viewModelScope.launch {
//            val newRound  = Rounds(game = game.id, firstPlayer = firstPlayer.id, round = round)
//            roundRepository.insertRound(newRound)
//
//    if (_rounds.value.isNullOrEmpty()) {
//        createRound(game.value, _playerList.last()[0], 0)
//    }
//
//            for(player in _playerList.value){
//                roundPlayerRepository.addRoundPlayer(RoundPlayer( player =player.id, round = newRound.id))
//            }
//        }
//    }

    fun updateRoundPlayer(roundPlayer: RoundPlayer) = viewModelScope.launch {
            roundPlayerRepository.updateRoundPlayer(roundPlayer)
        }

    fun addRound() = viewModelScope.launch {
        roundRepository.insertRound(rounds.value[rounds.value.size].copy(
            round = rounds.value.size + 1,
            firstPlayer = playerList.value[rounds.value.size + 1 % playerList.value.size].id
        ))
    }

    fun addRoundPlayer(round : Rounds, player: Players)= viewModelScope.launch {
        roundPlayerRepository.addRoundPlayer(RoundPlayer( round = round.id, player = player.id))
    }

}