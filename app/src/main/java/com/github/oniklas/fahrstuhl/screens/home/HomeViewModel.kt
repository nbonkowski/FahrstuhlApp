package com.github.oniklas.fahrstuhl.screens.home

import androidx.compose.material.FloatingActionButton
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.repositorys.GameRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: GameRepository): ViewModel() {
    private var _game = MutableStateFlow<Game>(Game())
    val game = _game.asStateFlow()
    var isFirst :Boolean = false

    init {
        viewModelScope.launch(Dispatchers.IO) {
//            Log.d("DBCall", HomeViewModel::class.simpleName.toString() + "remove All Game")
//           repository.removeAllGames()

            repository.getAllGames().distinctUntilChanged().collect {
                if (it.isEmpty()) {
                    isFirst = true
                    repository.addGame(_game.value)
                }
                else {
                    _game.value = it.last()
                }
            }

        }
    }
}