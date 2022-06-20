package com.github.oniklas.fahrstuhl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import java.util.*

@Entity(
    primaryKeys = ["player", "round"],
    foreignKeys = [
        ForeignKey(entity = Round::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("round"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(entity = Player::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("player"),
        onDelete = ForeignKey.CASCADE
        )
    ]
)

//not gonna be used as actual Crosref with Junction
data class RoundPlayerCrossRef(
    @ColumnInfo
    val player : UUID,
    @ColumnInfo
    val round : UUID,
    @ColumnInfo
    val prediction: Int = 0,
    @ColumnInfo
    val trick : Int = 0,
)
