package br.com.usinasantafe.cav.di.usecase

import br.com.usinasantafe.cav.domain.usecases.update.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UpdateModule {

    @Binds
    @Singleton
    fun bindUpdateTableColab(usecase: IUpdateTableColab): UpdateTableColab

    @Binds
    @Singleton
    fun bindUpdateTableEquip(usecase: IUpdateTableEquip): UpdateTableEquip

}