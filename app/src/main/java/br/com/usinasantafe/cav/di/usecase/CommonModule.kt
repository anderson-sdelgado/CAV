package br.com.usinasantafe.cav.di.usecase

import br.com.usinasantafe.cav.domain.usecases.common.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {

    @Binds
    @Singleton
    fun bindGetToken(usecase: IGetToken): GetToken

    @Binds
    @Singleton
    fun bindGetStatusSend(usecase: IGetStatusSend): GetStatusSend

    @Binds
    @Singleton
    fun bindHasRegColab(usecase: IHasRegColab): HasRegColab

    @Binds
    @Singleton
    fun bindHasNroEquip(usecase: IHasNroEquip): HasNroEquip

}