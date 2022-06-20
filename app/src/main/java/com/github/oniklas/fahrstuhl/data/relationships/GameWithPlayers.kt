package com.github.oniklas.fahrstuhl.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.data.Player

data class GameWithPlayers(
    @Embedded val game : Game,
    @Relation(
        parentColumn = "id",
        entityColumn = "game"
    )
    val players: List<Player>
)
