package com.github.oniklas.fahrstuhl.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Players(
    @PrimaryKey
    val id : UUID = UUID.randomUUID(),
    @ColumnInfo
    val name: String,
)
