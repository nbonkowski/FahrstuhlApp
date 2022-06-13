package com.github.oniklas.fahrstuhl.screens.ingame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Games
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.Rounds
import com.github.oniklas.fahrstuhl.repositorys.GameRepository
import com.github.oniklas.fahrstuhl.repositorys.PlayerRepository
import com.github.oniklas.fahrstuhl.repositorys.RoundRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InGameViewModel @Inject constructor(private val gameRepository: GameRepository,
                                          private val playerRepository : PlayerRepository,
                                          private val roundRepository: RoundRepository) : ViewModel(){

    private var _game = MutableStateFlow<Games>(Games())
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Players>>(emptyList())
    val playerList = _playerList.asStateFlow()

    private var _rounds = MutableStateFlow<List<Rounds>>(emptyList())
    val rounds = _rounds.asStateFlow()

    private var _roundPlayers = MutableStateFlow<List<List<RoundPlayer>>>(emptyList())
    val roundPlayers = _roundPlayers.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.getLastGame().distinctUntilChanged().collect {
                    _game.value = it
            }
            playerRepository.getAllPlayersFromGameId(_game.value.id).distinctUntilChanged().collect{
                if (it.isNullOrEmpty()){
                    _playerList.value = emptyList()
                }
                else{
                    _playerList.value = it
                }
            }
            roundRepository.getRoundsOfGame(_game.value.id).distinctUntilChanged().collect {
                if (it.isNullOrEmpty()){
                    roundRepository.insertRound(Rounds(game = game.value.id, firstPlayer = _playerList.value[0].id, round = 0))
                    _rounds.value = emptyList()
                }
                else{
                    _rounds.value = it
                }
            }
        }
    }
}