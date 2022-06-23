package com.github.oniklas.fahrstuhl.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Game::class,Player::class,Round::class,RoundPlayerCrossRef::class], version = 12, exportSchema = false)
abstract class FahrstuhlDatabase: RoomDatabase() {
    abstract fun gameDao():GameDao
    abstract fun roundDao():RoundDao
    abstract fun playerDao():PlayerDao
    abstract fun roundPlayerDao():RoundPlayerDao

}