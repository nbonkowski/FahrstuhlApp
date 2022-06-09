package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.*


@Dao
interface PlayerDao {
    @Query("SELECT * FROM PLAYERS")
    fun getAllPlayers(): Flow<List<Players>>

    @Query("SELECT * FROM Players where game = :game")
    fun getAllPlayersFromGame(game: UUID): Flow<List<Players>>

    @Query("SELECT * FROM PLAYERS where id = :id")
    fun getPlayerById(id: UUID): Flow<Players>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(players: Players)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlayer(players: Players)

    @Delete
    suspend fun deletePlayer(players: Players)

}