package com.github.oniklas.fahrstuhl.data

import androidx.room.Database

@Database(entities = [Games::class], version = 1, exportSchema = false)
abstract class FahrstuhlDatabase() {
}