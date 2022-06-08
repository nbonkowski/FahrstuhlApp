package com.github.oniklas.fahrstuhl

import android.content.Context
import androidx.room.Room
import com.github.oniklas.fahrstuhl.data.FahrstuhlDatabase
import com.github.oniklas.fahrstuhl.data.GameDao
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
    fun provideDatabase(@ApplicationContext context: Context):FahrstuhlDatabase = Room.databaseBuilder(
        context,
        FahrstuhlDatabase::class.java,
        "fahrstuhl_db"
    ).fallbackToDestructiveMigration().build()

}