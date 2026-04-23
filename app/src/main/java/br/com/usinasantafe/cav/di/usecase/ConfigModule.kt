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

}