package com.github.oniklas.fahrstuhl.screens.lobby


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.data.Player
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
class LobbyViewModel @Inject constructor(private val playerRepository: PlayerRepository,
                                         private val gameRepository: GameRepository): ViewModel() {
    private  var _game  = MutableStateFlow<Game>(Game())
    private var isInit = false
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Player>>(emptyList())
    val playerList = _playerList.asStateFlow()

    fun addPlayer(player: Player) = viewModelScope.launch {

        playerRepository.addPlayer(player.copy(
            game = _game.value.id
        ))
    }
    fun removePlayer(player: Player) = viewModelScope.launch {
        playerRepository.deletePlayer(player)
    }

    fun updatePlayer(player: Player) = viewModelScope.launch {
        playerRepository.updatePlayer(player)
    }

    fun newGame() = viewModelScope.launch {
        gameRepository.addGame(_game.value)
    }

}