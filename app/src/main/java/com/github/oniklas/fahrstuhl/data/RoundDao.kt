package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import com.github.oniklas.fahrstuhl.data.relationships.PlayerWithRounds
import com.github.oniklas.fahrstuhl.data.relationships.RoundWithPlayers
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RoundDao {
    @Query("SELECT * FROM ROUND")
    fun getAllRounds(): Flow<List<Round>>

    @Query("SELECT * FROM ROUND WHERE game = :gameid")
    fun getRoundsFromGame(gameid : UUID): Flow<List<Round>>

    @Query("SELECT * FROM Round ORDER BY ID DESC LIMIT 1")
    fun getLastRound() : Flow<Round>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRound(rounds: Round)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRound(rounds: Round)

    @Query("Delete From Round")
    suspend fun removeRounds()

    @Transaction
    @Query("SELECT * FROM Round WHERE id = :round")
    suspend fun getRoundWithPlayers(round: UUID): List<RoundWithPlayers>

}