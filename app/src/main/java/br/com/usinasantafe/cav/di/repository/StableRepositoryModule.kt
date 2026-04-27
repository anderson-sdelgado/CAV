package br.com.usinasantafe.cav.di.repository

import br.com.usinasantafe.cav.domain.repositories.stable.*
import br.com.usinasantafe.cav.infra.repositories.stable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StableRepositoryModule {

    @Binds
    @Singleton
    fun bindColabRepository(repository: IColabRepository): ColabRepository

    @Binds
    @Singleton
    fun bindEquipRepository(repository: IEquipRepository): EquipRepository

}