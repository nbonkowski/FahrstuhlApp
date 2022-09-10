package com.github.oniklas.fahrstuhl.screens.home


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
    private var _isFirst = MutableStateFlow<Boolean>(true)
    val isFirst = _isFirst.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllGames().distinctUntilChanged().collect(){
                if (it.isEmpty()) {
                    _isFirst.value = true

                }
                else {
                    _isFirst.value = false
                    _game.value = it.last()
                }
            }

        }
    }
}