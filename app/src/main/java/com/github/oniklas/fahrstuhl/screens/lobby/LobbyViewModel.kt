package com.github.oniklas.fahrstuhl.screens.lobby


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Games
import com.github.oniklas.fahrstuhl.data.Players
import com.github.oniklas.fahrstuhl.repositorys.GameRepository
import com.github.oniklas.fahrstuhl.repositorys.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LobbyViewModel @Inject constructor(private val playerRepository: PlayerRepository, private val gameRepository: GameRepository): ViewModel() {
    private var _game = MutableStateFlow<Games>(Games())
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Players>>(emptyList())
    val playerList = _playerList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.getLastGame().distinctUntilChanged().collect {
              if(it == null){
                  _game.value = Games()
              }
                else{
                  _game.value = it
              }
            }
                playerRepository.getAllPlayersFromGameId(_game.value.id).distinctUntilChanged().collect{
                    if (it.isNullOrEmpty()){
                        _playerList.value = emptyList()
                    }
                    else{
                        _playerList.value = it
                    }

            }
        }
    }

    fun addPlayer(player: Players) = viewModelScope.launch {
        playerRepository.addPlayer(player)
    }
    fun removePlayer(player: Players) = viewModelScope.launch {
        playerRepository.deletePlayer(player)
    }

    fun updatePlayer(player: Players) = viewModelScope.launch {
        playerRepository.updatePlayer(player)
    }

    fun newGame(game: Games) = viewModelScope.launch {
        gameRepository.addGame(game)
    }

}