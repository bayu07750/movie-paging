package com.bayu.moviepaging.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

    @IODispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @DefaultDispatcher
    @Provides
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @MainDispatcher
    @Provides
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

}

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class IODispatcher

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class DefaultDispatcher

@Qualifier
@Retention(value = AnnotationRetention.BINARY)
annotation class MainDispatcher
