package com.github.oniklas.fahrstuhl.repositorys

import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.RoundPlayerDao
import kotlinx.coroutines.flow.Flow
import java.util.*
import javax.inject.Inject

class RoundPlayerRepository @Inject constructor( private  val roundPlayerDao: RoundPlayerDao)
{
    fun getAllRoundPlayer(): Flow<List<RoundPlayer>> = roundPlayerDao.getAllRoundPlayers()
    fun getAllRoundsOfRound(roundid : UUID) : Flow<List<RoundPlayer>> = roundPlayerDao.getRoundPlayerFromRoundId(roundid)
    fun getAllRoundsOfPlayer(playerid: UUID) : Flow<List<RoundPlayer>> = roundPlayerDao.getRoundPlayerFromPlayerId(playerid)

    suspend fun addRoundPlayer(roundPlayer: RoundPlayer) = roundPlayerDao.insertRoundPlayer(roundPlayer)
    suspend fun updateRoundPlayer(roundPlayer: RoundPlayer) = roundPlayerDao.updateRoundPlayer(roundPlayer)
}