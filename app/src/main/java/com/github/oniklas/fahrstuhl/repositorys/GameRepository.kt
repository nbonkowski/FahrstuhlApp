package com.github.oniklas.fahrstuhl.repositorys

import com.github.oniklas.fahrstuhl.data.GameDao
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.data.relationships.GameWithPlayers
import com.github.oniklas.fahrstuhl.data.relationships.GameWithRounds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject

class GameRepository @Inject constructor(private  val gameDao: GameDao) {
    fun getAllGames(): Flow<List<Game>> = gameDao.getAllGames().flowOn(Dispatchers.IO).conflate()
    fun getLastGame(): Flow<Game> = gameDao.getLastGame().flowOn(Dispatchers.IO).conflate()
    fun getGameById(id: UUID) : Flow<Game> = gameDao.getGameById(id).flowOn(Dispatchers.IO).conflate()

    suspend fun updateGame(game: Game)= gameDao.updateGame(game)
    suspend fun addGame(game:Game) = gameDao.insertGame(game)
    suspend fun removeAllGames() = gameDao.removeAllGames()

    fun getGameWithPlayers(game: UUID): Flow<GameWithPlayers> = gameDao.getGameWithPlayers(game)
    fun getGameWithRounds(game: UUID) : Flow<GameWithRounds> = gameDao.getGameWithRounds(game)
    fun getAllGamesWithPlayers() : Flow<List<GameWithPlayers>> = gameDao.getAllGameWithPlayers()
}