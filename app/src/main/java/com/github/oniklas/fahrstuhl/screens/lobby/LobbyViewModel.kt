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
    private  var _game  = MutableStateFlow<Games>(Games())
    private var isInit = false
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Players>>(emptyList())
    val playerList = _playerList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            gameRepository.getLastGame().distinctUntilChanged().collect {
                _game = MutableStateFlow<Games>(it) //TODO change and test _game.value = it
                isInit = true
            }
            if (!isInit){
                gameRepository.addGame(_game.value)
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            playerRepository.getAllPlayers().distinctUntilChanged().collect {
                    _playerList.value = it
            }
        }
    }

    fun addPlayer(player: Players) = viewModelScope.launch {

        playerRepository.addPlayer(player.copy(
            game = _game.value.id
        ))
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