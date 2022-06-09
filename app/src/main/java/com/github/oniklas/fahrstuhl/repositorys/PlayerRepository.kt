package com.github.oniklas.fahrstuhl.repositorys

import com.github.oniklas.fahrstuhl.data.PlayerDao
import com.github.oniklas.fahrstuhl.data.Players
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerDao: PlayerDao) {
    fun getAllPlayers(): Flow<List<Players>> = playerDao.getAllPlayers()
    fun getAllPlayersFromGameId(gameId: UUID): Flow<List<Players>> = playerDao.getAllPlayersFromGame(gameId)
    fun getPlayerFromId(id : UUID): Flow<Players> = playerDao.getPlayerById(id)

    suspend fun updatePlayer(player: Players) = playerDao.updatePlayer(player)
    suspend fun addPlayer(player: Players) = playerDao.insertPlayer(player)
    suspend fun deletePlayer(player: Players)= playerDao.deletePlayer(player)

}