package com.github.oniklas.fahrstuhl.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundDao {
    @Query("SELECT * FROM ROUNDS")
    fun getAllRounds(): Flow<List<Rounds>>
}