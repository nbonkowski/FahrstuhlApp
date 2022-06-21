package com.github.oniklas.fahrstuhl.data

import androidx.room.*
import com.github.oniklas.fahrstuhl.data.relationships.GameWithPlayers
import com.github.oniklas.fahrstuhl.data.relationships.GameWithRounds
import com.github.oniklas.fahrstuhl.data.relationships.PlayerWithRounds
import kotlinx.coroutines.flow.Flow
import java.util.*


@Dao
interface PlayerDao {
    @Query("SELECT * FROM PLAYER")
    fun getAllPlayers(): Flow<List<Player>>

    @Query("SELECT * FROM Player where game = :game")
    fun getAllPlayersFromGame(game: UUID): Flow<List<Player>>

    @Query("SELECT * FROM PLAYER where id = :id")
    fun getPlayerById(id: UUID): Flow<Player>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(players: Player)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updatePlayer(players: Player)

    @Delete
    suspend fun deletePlayer(players: Player)

    @Transaction
    @Query("SELECT * FROM Player WHERE id = :player")
    suspend fun getPlayerWithRounds(player: UUID): PlayerWithRounds
}