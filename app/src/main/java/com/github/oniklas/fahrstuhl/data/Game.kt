package com.github.oniklas.fahrstuhl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity//(indices = [Index(value = ["id"], unique = true)])
data class Game(
    @PrimaryKey
    val id: UUID  = UUID.randomUUID(),
    @ColumnInfo
    val rounds:Int = 12,
    @ColumnInfo
    val round:Int = 0,
    @ColumnInfo
    val finished: Boolean = false
)
