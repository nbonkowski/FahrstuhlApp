package com.github.oniklas.fahrstuhl.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.github.oniklas.fahrstuhl.data.Game
import com.github.oniklas.fahrstuhl.data.Round

data class GameWithRounds (
    @Embedded val game: Game,
    @Relation(
        parentColumn = "id",
        entityColumn = "game"
    )
    val rounds: List<Round>
    )