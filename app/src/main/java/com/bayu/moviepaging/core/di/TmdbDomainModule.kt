package com.bayu.moviepaging.core.di

import com.bayu.moviepaging.core.data.repositoryimp.TmdbRepositoryImp
import com.bayu.moviepaging.domain.repository.TmdbRepository
import com.bayu.moviepaging.domain.usecase.media.TmdbInteraction
import com.bayu.moviepaging.domain.usecase.media.TmdbUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class TmdbDomainModule {

    @Binds
    abstract fun provideTmdbRepository(impl: TmdbRepositoryImp): TmdbRepository

    @Binds
    abstract fun provideTmdbUseCase(impl: TmdbInteraction): TmdbUseCase


}