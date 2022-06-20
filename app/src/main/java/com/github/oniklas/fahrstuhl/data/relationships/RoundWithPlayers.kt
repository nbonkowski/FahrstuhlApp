package com.github.oniklas.fahrstuhl.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.github.oniklas.fahrstuhl.data.Round
import com.github.oniklas.fahrstuhl.data.RoundPlayerCrossRef

data class RoundWithPlayers(
    @Embedded val round: Round,
    @Relation(
        parentColumn = "id",
        entityColumn = "round"
    )
    val roundPlayerCrossRef : List<RoundPlayerCrossRef>
)
