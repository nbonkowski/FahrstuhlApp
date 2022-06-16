package com.github.oniklas.fahrstuhl.repositorys

import androidx.lifecycle.LiveData
import com.github.oniklas.fahrstuhl.data.GameDao
import com.github.oniklas.fahrstuhl.data.Games
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import java.util.*
import javax.inject.Inject

class GameRepository @Inject constructor(private  val gameDao: GameDao) {
    fun getAllGames(): Flow<List<Games>> = gameDao.getAllGames().flowOn(Dispatchers.IO).conflate()
    fun getLastGame(): Flow<Games> = gameDao.getLastGame().flowOn(Dispatchers.IO).conflate()
    fun getGameById(id: UUID) : Flow<Games> = gameDao.getGameById(id).flowOn(Dispatchers.IO).conflate()

    suspend fun updateGame(game: Games)= gameDao.updateGame(game)
    suspend fun addGame(game:Games) = gameDao.insertGame(game)
    suspend fun removeAllGames() = gameDao.removeAllGames()
}