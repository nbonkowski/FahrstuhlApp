package com.github.oniklas.fahrstuhl.repositorys

import com.github.oniklas.fahrstuhl.data.RoundDao
import com.github.oniklas.fahrstuhl.data.RoundPlayer
import com.github.oniklas.fahrstuhl.data.RoundPlayerDao
import com.github.oniklas.fahrstuhl.data.Rounds
import kotlinx.coroutines.flow.Flow
import java.util.*

import javax.inject.Inject
import kotlin.math.round

//Repository for retrieving and changing data from Rounds and RoundPlayer Table

class RoundRepository @Inject constructor(private val roundDao: RoundDao){
    fun getAllRounds(): Flow<List<Rounds>> = roundDao.getAllRounds()
    fun getRoundsOfGame(gameId : UUID) : Flow<List<Rounds>> = roundDao.getRoundsFromGame(gameId)

    fun lastRound():Flow<Rounds> = roundDao.getLastRound()

    suspend fun insertRound(round: Rounds) = roundDao.insertRound(round)
    suspend fun updateRound(round:Rounds) = roundDao.updateRound(round)
    suspend fun removeRounds() = roundDao.removeRounds()



}