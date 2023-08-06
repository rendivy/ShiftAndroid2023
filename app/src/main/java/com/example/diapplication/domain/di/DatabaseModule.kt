package com.example.diapplication.domain.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(): FirebaseDatabase{
        return Firebase.database
    }

    @Provides
    @Singleton
    fun provideCitiesReference(database: FirebaseDatabase) = database.getReference("cities")

}