package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.implementation.IncidenciaRepoImpl
import data.implementation.UsuariRepoImpl
import data.implementation.Usuari_resolRepoImpl
import data.implementation.ZonesRepoImpl
import domain.repo.IncidenciaRepo
import domain.repo.Usuari_resolRepo
import domain.repo.UsuarisRepo

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
    abstract fun bindZonesRepo(impl : ZonesRepoImpl) : ZonesRepoImpl




}