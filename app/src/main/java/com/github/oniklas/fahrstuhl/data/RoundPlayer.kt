package com.github.oniklas.fahrstuhl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.*

@Entity(primaryKeys = ["player", "round"], foreignKeys = [
    ForeignKey(entity = Rounds::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("round"),
        onDelete = ForeignKey.CASCADE
    ),
    ForeignKey(entity = Players::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("player"),
    onDelete = ForeignKey.CASCADE
    )
])
data class RoundPlayer(
    @ColumnInfo
    val player : UUID,
    @ColumnInfo
    val round : UUID,
    @ColumnInfo
    val prediction: Int = 0,
    @ColumnInfo
    val trick : Int = 0,
)
