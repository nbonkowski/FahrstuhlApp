package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RoundPlayerDao {
    @Query("SELECT * FROM RoundPlayerCrossRef")
    fun getAllRoundPlayers(): Flow<List<RoundPlayerCrossRef>>

    @Query("SELECT * FROM RoundPlayerCrossRef where round = :roundid")
    fun getRoundPlayerFromRoundId(roundid : UUID):Flow<List<RoundPlayerCrossRef>>

    @Query("SELECT * FROM RoundPlayerCrossRef where player = :playerid")
    fun getRoundPlayerFromPlayerId(playerid : UUID):Flow<List<RoundPlayerCrossRef>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoundPlayer(roundPlayer: RoundPlayerCrossRef)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoundPlayer(roundPlayer: RoundPlayerCrossRef)
}