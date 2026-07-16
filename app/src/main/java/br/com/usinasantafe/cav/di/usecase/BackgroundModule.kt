package br.com.usinasantafe.cav.di.usecase

import br.com.usinasantafe.cav.domain.usecases.background.IStartWorkManager
import br.com.usinasantafe.cav.domain.usecases.background.StartWorkManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BackgroundModule {

    @Binds
    @Singleton
    fun bindStartWorkManager(usecase: IStartWorkManager): StartWorkManager

}