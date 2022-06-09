package com.github.oniklas.fahrstuhl

import android.content.Context
import androidx.room.Room
import com.github.oniklas.fahrstuhl.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Singleton
    @Provides
    fun provideGameDao(fahrstuhlDatabase: FahrstuhlDatabase):GameDao = fahrstuhlDatabase.gameDao()

    @Singleton
    @Provides
    fun providePlayerDao(fahrstuhlDatabase: FahrstuhlDatabase):PlayerDao = fahrstuhlDatabase.playerDao()

    @Singleton
    @Provides
    fun provideRoundDao(fahrstuhlDatabase: FahrstuhlDatabase):RoundDao = fahrstuhlDatabase.roundDao()

    @Singleton
    @Provides
    fun provideRoundPlayerDao(fahrstuhlDatabase: FahrstuhlDatabase):RoundPlayerDao = fahrstuhlDatabase.roundPlayerDao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context):FahrstuhlDatabase = Room.databaseBuilder(
        context,
        FahrstuhlDatabase::class.java,
        "fahrstuhl_db"
    ).fallbackToDestructiveMigration().build()

}