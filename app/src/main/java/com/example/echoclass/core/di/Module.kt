package com.example.echoclass.core.di

import com.example.echoclass.data.repository.student.StudentRepository
import com.example.echoclass.data.repository.user.UserRepository
import com.example.echoclass.domain.firebase.authentication.UserAuthentication
import com.example.echoclass.domain.firebase.student.StudentOperations
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Singleton
    @Provides
    fun providesUserRepository():UserAuthentication = UserRepository()

    @Singleton
    @Provides
    fun provideFirebaseAuth():FirebaseAuth = Firebase.auth

    @Singleton
    @Provides
    fun providesFirebaseFireStore():FirebaseFirestore = Firebase.firestore

    @Singleton
    @Provides
    fun providesStudentRepository(auth:FirebaseAuth,firestore: FirebaseFirestore):StudentOperations = StudentRepository(auth,firestore)



}