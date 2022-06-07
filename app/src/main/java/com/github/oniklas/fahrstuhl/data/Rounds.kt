package com.github.oniklas.fahrstuhl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [ForeignKey(entity = Players::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("firstPlayer"))]
)
data class Rounds(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),
    @ColumnInfo
    val firstPlayer: UUID,


)
