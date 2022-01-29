package com.bayu.moviepaging.core.di

import com.bayu.moviepaging.core.data.repositoryimp.TrendingRepositoryImp
import com.bayu.moviepaging.domain.repository.TrendingRepository
import com.bayu.moviepaging.domain.usecase.media.TrendingInteraction
import com.bayu.moviepaging.domain.usecase.media.TrendingUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class TrendingDomainModule {

    @Binds
    abstract fun provideTrendingRepository(impl: TrendingRepositoryImp): TrendingRepository

    @Binds
    abstract fun provideTrendingUseCase(impl: TrendingInteraction): TrendingUseCase


}