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

    @Binds
    @Singleton
    fun bindListNature(usecase: IListNature): ListNature

    @Binds
    @Singleton
    fun bindSetListNature(usecase: ISetNatureList): SetNatureList

    @Binds
    @Singleton
    fun bindGetAttendant(usecase: IGetAttendant): GetAttendant

    @Binds
    @Singleton
    fun bindGetCar(usecase: IGetCar): GetCar

    @Binds
    @Singleton
    fun bindGetNature(usecase: IGetNature): GetNature

    @Binds
    @Singleton
    fun bindGetTypeAccident(usecase: IGetTypeAccident): GetTypeAccident

    @Binds
    @Singleton
    fun bindListTypeAccident(usecase: IListTypeAccident): ListTypeAccident

    @Binds
    @Singleton
    fun bindSetTypeAccidentList(usecase: ISetTypeAccidentList): SetTypeAccidentList

    @Binds
    @Singleton
    fun bindCancelCard(usecase: ICancelCard): CancelCard

    @Binds
    @Singleton
    fun bindGetLocal(usecase: IGetLocal): GetLocal

    @Binds
    @Singleton
    fun bindListDataLocal(usecase: IListDataLocal): ListDataLocal

    @Binds
    @Singleton
    fun bindListOptionDataLocal(usecase: IListOptionDataLocal): ListOptionDataLocal

    @Binds
    @Singleton
    fun bindListItemDataLocal(usecase: IListItemDataLocal): ListItemDataLocal

    @Binds
    @Singleton
    fun bindSetDataLocalList(usecase: ISetDataLocalList): SetDataLocalList

}