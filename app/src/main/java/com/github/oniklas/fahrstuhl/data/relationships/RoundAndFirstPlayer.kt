package com.github.oniklas.fahrstuhl.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.data.Round

//Not implemented because its not used
data class RoundAndFirstPlayer(
    @Embedded val player: Player,
    @Relation(
        parentColumn = "id",
        entityColumn = "firstPlayer"
    )
    val round: Round
)
