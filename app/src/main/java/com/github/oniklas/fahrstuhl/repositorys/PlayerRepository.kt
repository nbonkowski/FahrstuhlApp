package com.github.oniklas.fahrstuhl.repositorys

import com.github.oniklas.fahrstuhl.data.PlayerDao
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.data.relationships.PlayerWithRounds
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class PlayerRepository @Inject constructor(private val playerDao: PlayerDao) {
    fun getAllPlayers(): Flow<List<Player>> = playerDao.getAllPlayers()
    fun getAllPlayersFromGameId(gameId: UUID): Flow<List<Player>> = playerDao.getAllPlayersFromGame(gameId)
    fun getPlayerFromId(id : UUID): Flow<Player> = playerDao.getPlayerById(id)

    suspend fun updatePlayer(player: Player) = playerDao.updatePlayer(player)
    suspend fun addPlayer(player: Player) = playerDao.insertPlayer(player)
    suspend fun deletePlayer(player: Player)= playerDao.deletePlayer(player)

    suspend fun getPlayerWithRounds(player: UUID): PlayerWithRounds = playerDao.getPlayerWithRounds(player)
}