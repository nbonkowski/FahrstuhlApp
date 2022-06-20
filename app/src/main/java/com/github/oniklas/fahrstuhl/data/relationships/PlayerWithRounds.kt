package com.github.oniklas.fahrstuhl.data.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.github.oniklas.fahrstuhl.data.Player
import com.github.oniklas.fahrstuhl.data.RoundPlayerCrossRef

data class PlayerWithRounds(
    @Embedded val player: Player,
    @Relation(
        parentColumn = "id",
        entityColumn = "player"
    )
    val roundPlayerCrossRef : List<RoundPlayerCrossRef>
)
