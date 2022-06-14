package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface RoundDao {
    @Query("SELECT * FROM ROUNDS")
    fun getAllRounds(): Flow<List<Rounds>>

    @Query("SELECT * FROM ROUNDS WHERE game = :gameid")
    fun getRoundsFromGame(gameid : UUID): Flow<List<Rounds>>

    @Query("SELECT * FROM Rounds ORDER BY ID DESC LIMIT 1")
    fun getLastRound() : Flow<Rounds>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRound(rounds: Rounds)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRound(rounds: Rounds)

    @Query("Delete From Rounds")
    suspend fun removeRounds()
}