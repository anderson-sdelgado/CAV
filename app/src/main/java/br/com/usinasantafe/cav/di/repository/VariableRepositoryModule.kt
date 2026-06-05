package br.com.usinasantafe.cav.di.repository

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.infra.repositories.variable.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface VariableRepositoryModule {

    @Binds
    @Singleton
    fun bindConfigRepository(repository: IConfigRepository): ConfigRepository

    @Binds
    @Singleton
    fun bindCardRepository(repository: ICardRepository): CardRepository

    @Binds
    @Singleton
    fun bindBasicCardRepository(repository: IBasicCardRepository): BasicCardRepository

    @Binds
    @Singleton
    fun bindInsertCardRepository(repository: IInsertCardRepository): InsertCardRepository

    @Binds
    @Singleton
    fun bindRecoverDataCardRepository(repository: IRecoverDataCardRepository): RecoverDataCardRepository

    @Binds
    @Singleton
    fun bindUpdateCardRepository(repository: IUpdateCardRepository): UpdateCardRepository

}
