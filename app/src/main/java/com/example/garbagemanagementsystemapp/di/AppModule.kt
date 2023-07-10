package com.example.garbagemanagementsystemapp.di

import com.example.garbagemanagementsystemapp.data.AuthRepository
import com.example.garbagemanagementsystemapp.data.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth, firebaseFirestore: FirebaseFirestore) : AuthRepository{
        return AuthRepositoryImpl(firebaseAuth, firebaseFirestore)
    }

}