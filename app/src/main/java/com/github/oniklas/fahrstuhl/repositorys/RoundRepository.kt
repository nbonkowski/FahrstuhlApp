package com.github.oniklas.fahrstuhl.repositorys

import com.github.oniklas.fahrstuhl.data.RoundDao
import com.github.oniklas.fahrstuhl.data.RoundPlayerCrossRef
import com.github.oniklas.fahrstuhl.data.RoundPlayerDao
import com.github.oniklas.fahrstuhl.data.Round
import com.github.oniklas.fahrstuhl.data.relationships.PlayerWithRounds
import com.github.oniklas.fahrstuhl.data.relationships.RoundWithPlayers
import kotlinx.coroutines.flow.Flow
import java.util.*

import javax.inject.Inject

//Repository for retrieving and changing data from Round and RoundPlayerCrossRef Table

class RoundRepository @Inject constructor(private val roundDao: RoundDao,private  val roundPlayerDao: RoundPlayerDao){
    fun getAllRounds(): Flow<List<Round>> = roundDao.getAllRounds()
    fun getRoundsOfGame(gameId : UUID) : Flow<List<Round>> = roundDao.getRoundsFromGame(gameId)

    fun lastRound():Flow<Round> = roundDao.getLastRound()

    suspend fun insertRound(round: Round) = roundDao.insertRound(round)
    suspend fun updateRound(round:Round) = roundDao.updateRound(round)
    suspend fun removeRounds() = roundDao.removeRounds()

    fun getAllRoundPlayer(): Flow<List<RoundPlayerCrossRef>> = roundPlayerDao.getAllRoundPlayers()
    fun getAllRoundsOfRound(roundid : UUID) : Flow<List<RoundPlayerCrossRef>> = roundPlayerDao.getRoundPlayerFromRoundId(roundid)
    fun getAllRoundsOfPlayer(playerid: UUID) : Flow<List<RoundPlayerCrossRef>> = roundPlayerDao.getRoundPlayerFromPlayerId(playerid)

    suspend fun addRoundPlayer(roundPlayer: RoundPlayerCrossRef) = roundPlayerDao.insertRoundPlayer(roundPlayer)
    suspend fun updateRoundPlayer(roundPlayer: RoundPlayerCrossRef) = roundPlayerDao.updateRoundPlayer(roundPlayer)

    suspend fun getRoundWithPlayers(round : UUID) : List<RoundWithPlayers> = roundDao.getRoundWithPlayers(round)

}