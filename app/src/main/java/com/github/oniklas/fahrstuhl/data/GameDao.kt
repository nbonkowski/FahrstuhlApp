package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface GameDao {
    @Query("SELECT * FROM Games")
    fun getAllGames(): Flow<List<Games>>

    @Query("SELECT * FROM Games ORDER BY ID DESC LIMIT 1")
    fun getLastGame(): Flow<Games>

    @Query("SELECT * FROM GAMES WHERE ID = :id")
    fun getGameById(id: UUID): Flow<Games>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(games: Games)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(games: Games)

    @Query("DELETE FROM GAMES")
    suspend fun removeAllGames()
}