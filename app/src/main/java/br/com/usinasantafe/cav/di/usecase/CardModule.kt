package br.com.usinasantafe.cav.di.usecase

import br.com.usinasantafe.cav.domain.usecases.card.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CardModule {

    @Binds
    @Singleton
    fun bindSetRegAttendant(usecase: ISetRegAttendant): SetRegAttendant

    @Binds
    @Singleton
    fun bindSetIdCar(usecase: ISetIdCar): SetIdCar

    @Binds
    @Singleton
    fun bindSetLocal(usecase: ISetLocal): SetLocal
}