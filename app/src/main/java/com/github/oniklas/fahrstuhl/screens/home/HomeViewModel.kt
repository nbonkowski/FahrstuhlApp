package com.github.oniklas.fahrstuhl.screens.home

import android.preference.PreferenceManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Games
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
class HomeViewModel @Inject constructor(private val repository: GameRepository): ViewModel() {
    private var _game = MutableStateFlow<Games>(Games())
    val game = _game.asStateFlow()
        //if(_game.asStateFlow().value != null && _game.value.round > 0) _game.asStateFlow().value.finished else true

    init {

        viewModelScope.launch(Dispatchers.IO) {
            repository.removeAllGames()
            repository.getLastGame().distinctUntilChanged().collect { game: Games ->
                _game.value = game
            }
        }
    }
}