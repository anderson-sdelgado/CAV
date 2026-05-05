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

    @Binds
    @Singleton
    fun bindUpdateTableItemDataLocal(usecase: IUpdateTableItemDataLocal): UpdateTableItemDataLocal

    @Binds
    @Singleton
    fun bindUpdateTableNature(usecase: IUpdateTableNature): UpdateTableNature

    @Binds
    @Singleton
    fun bindUpdateTableOptionDataLocal(usecase: IUpdateTableOptionDataLocal): UpdateTableOptionDataLocal

    @Binds
    @Singleton
    fun bindUpdateTableROptionItemDataLocal(usecase: IUpdateTableROptionItemDataLocal): UpdateTableROptionItemDataLocal

    @Binds
    @Singleton
    fun bindUpdateTableSupportTeams(usecase: IUpdateTableSupportTeams): UpdateTableSupportTeams

    @Binds
    @Singleton
    fun bindUpdateTableTypeAccident(usecase: IUpdateTableTypeAccident): UpdateTableTypeAccident

}