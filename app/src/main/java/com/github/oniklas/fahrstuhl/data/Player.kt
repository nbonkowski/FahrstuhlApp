package com.github.oniklas.fahrstuhl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(
    entity = Game::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("game"),
    onDelete = ForeignKey.CASCADE
)])
data class Player(
    @PrimaryKey
    val id : UUID = UUID.randomUUID(),
    @ColumnInfo
    val game: UUID,
    @ColumnInfo
    val name: String,
)
