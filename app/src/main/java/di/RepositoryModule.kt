package di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import data.implementation.IncidenciaRepoImpl
import data.implementation.UsuariRepoImpl
import data.implementation.Usuari_resolRepoImpl
import data.implementation.ZonesRepoImpl
import data.services.IncidenciesEndpoints
import data.services.RetrofitConnection
import data.services.UsuarisEndpoints
import data.services.UsuarisResolsEndpoints
import data.services.ZonesEndpoints
import domain.repo.IncidenciaRepo
import domain.repo.Usuari_resolRepo
import domain.repo.UsuarisRepo
import domain.repo.ZonesRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Singleton

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {

        @Binds
        abstract fun bindIncidenciaRepo(impl : IncidenciaRepoImpl) : IncidenciaRepo
        @Binds
        abstract fun bindUsuariResolRepo(impl : Usuari_resolRepoImpl) : Usuari_resolRepo
        @Binds
        abstract fun bindUsuariRepo(impl : UsuariRepoImpl) : UsuarisRepo
        @Binds
        abstract fun bindZonesRepo(impl : ZonesRepoImpl) : ZonesRepo



    }
    @Module
    @InstallIn(SingletonComponent::class)
    object ProvidesModule {
        @Provides
        @Singleton
        fun provideRetrofit(): RetrofitConnection {
            return RetrofitConnection
        }
        /*
        @Provides
        @Singleton
        fun provideCoroutineScope(): CoroutineScope {
            return CoroutineScope(Dispatchers.IO)
        }
         */

        @Provides
        @Singleton
        fun provideIncidenciesEndpoints(retrofit: RetrofitConnection): IncidenciesEndpoints {
            return retrofit.serviceIncidencies
        }

        @Provides
        @Singleton
        fun provideUsuarisEndpoints(retrofit: RetrofitConnection): UsuarisEndpoints {
            return retrofit.serviceUsuarisEndpoints
        }

        @Provides
        @Singleton
        fun provideZonesEndpoints(retrofit: RetrofitConnection): ZonesEndpoints {
            return retrofit.serviceZonesEndpoints
        }

        @Provides
        @Singleton
        fun provideUsuarisResolsEndpoints(retrofit: RetrofitConnection): UsuarisResolsEndpoints {
            return retrofit.serviceUsuariResolEndpoints
        }
    }