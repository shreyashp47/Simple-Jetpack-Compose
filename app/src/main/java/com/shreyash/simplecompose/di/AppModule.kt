package com.shreyash.simplecompose.di

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
    fun provideMessageRepository(): MessageRepository {
        return MessageRepositoryImpl()
    }
}

interface MessageRepository {
    fun getMessages(): List<String>
}

class MessageRepositoryImpl : MessageRepository {
    override fun getMessages(): List<String> {
        return listOf("Hello from Hilt!", "Dependency Injection is working!")
    }
}