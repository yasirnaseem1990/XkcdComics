package com.xkcd.comics.di.modules

import android.app.Application
import com.xkcd.comics.data.remote.XkcdComicsApiService
import com.xkcd.comics.data.repository.XkcdComicsRepository
import com.xkcd.comics.data.repository.XkcdComicsRepositoryImpl
import com.xkcd.comics.utils.StringUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 * @author yasirnaseem1990@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(stringUtils: StringUtils, apiService: XkcdComicsApiService): XkcdComicsRepository {
        return XkcdComicsRepositoryImpl(stringUtils, apiService)
    }
}