package br.com.usinasantafe.cav.di.usecase

import br.com.usinasantafe.cav.domain.usecases.config.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ConfigModule {

    @Binds
    @Singleton
    fun bindCheckPassword(usecase: ICheckPassword): CheckPassword

    @Binds
    @Singleton
    fun bindGetConfig(usecase: IGetConfig): GetConfig

    @Binds
    @Singleton
    fun bindSaveDataConfig(usecase: ISaveConfig): SaveConfig

    @Binds
    @Singleton
    fun bindSendDataConfig(usecase: ISendConfig): SendConfig

    @Binds
    @Singleton
    fun bindSetFinishUpdateAllTable(usecase: ISetFinishUpdateAllTable): SetFinishUpdateAllTable

}