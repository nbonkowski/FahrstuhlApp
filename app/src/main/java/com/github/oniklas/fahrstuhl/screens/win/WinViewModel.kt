package com.github.oniklas.fahrstuhl.screens.win

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.repositorys.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WinViewModel @Inject constructor(private val repository: GameRepository): ViewModel() {
    private var _game = MutableStateFlow(Game())
    val game = _game.asStateFlow()

    private var _playerList = MutableStateFlow<List<Player>>(emptyList())
    val playerList = _playerList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllGamesWithPlayers().distinctUntilChanged().collect {
                _game.value = it.last().game
                _playerList.value = it.last().players.sortedByDescending { it.points }
            }

        }
    }
}