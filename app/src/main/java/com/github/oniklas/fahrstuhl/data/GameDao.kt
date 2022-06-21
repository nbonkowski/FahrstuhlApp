package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import com.github.oniklas.fahrstuhl.data.relationships.GameWithPlayers
import com.github.oniklas.fahrstuhl.data.relationships.GameWithRounds
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface GameDao {
    @Query("SELECT * FROM Game")
    fun getAllGames(): Flow<List<Game>>

    @Query("SELECT * FROM Game ORDER BY ID DESC LIMIT 1")
    fun getLastGame(): Flow<Game>

    @Query("SELECT * FROM GAME WHERE ID = :id")
    fun getGameById(id: UUID): Flow<Game>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: Game)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGame(game: Game)

    @Query("DELETE FROM GAME")
    suspend fun removeAllGames()

    @Transaction
    @Query("SELECT * FROM Game WHERE id = :game")
    fun getGameWithPlayers(game: UUID): Flow<GameWithPlayers>

    @Transaction
    @Query("SELECT * FROM Game WHERE id = :game")
    fun getGameWithRounds(game: UUID): Flow<GameWithRounds>
}