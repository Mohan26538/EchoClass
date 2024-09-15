package com.example.echoclass.core.di

import com.example.echoclass.data.repository.user.UserRepository
import com.example.echoclass.domain.firebase.authentication.UserAuthentication
import com.example.echoclass.domain.firebase.student.StudentOperations
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

}