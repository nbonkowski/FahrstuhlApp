package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RoundPlayerDao {
    @Query("SELECT * FROM RoundPlayer")
    fun getAllRoundPlayers(): Flow<List<RoundPlayer>>

    @Query("SELECT * FROM ROUNDPLAYER where game = :gameid")
    fun getRoundPlayersFromGameId(gameid : UUID)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRoundPlayer(roundPlayer: RoundPlayer)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRoundPlayer(roundPlayer: RoundPlayer)
}