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

    @Binds
    @Singleton
    fun bindListSupportTeams(usecase: IListSupportTeams): ListSupportTeams

    @Binds
    @Singleton
    fun bindSetSupportTeamsList(usecase: ISetSupportTeamsList): SetSupportTeamsList

    @Binds
    @Singleton
    fun bindGetSupportTeams(usecase: IGetSupportTeams): GetSupportTeams

    @Binds
    @Singleton
    fun bindGetNroEquip(usecase: IGetNroEquip): GetNroEquip

    @Binds
    @Singleton
    fun bindSetIdEquip(usecase: ISetEquip): SetEquip

    @Binds
    @Singleton
    fun bindGetDetailVehicleOwn(usecase: IGetDetailVehicleOwn): GetDetailVehicleOwn

    @Binds
    @Singleton
    fun bindSetDetailVehicleOwn(usecase: ISetDetailVehicleOwn): SetDetailVehicleOwn

    @Binds
    @Singleton
    fun bindGetRegColab(usecase: IGetRegColab): GetRegColab

    @Binds
    @Singleton
    fun bindSetColab(usecase: ISetColab): SetColab

    @Binds
    @Singleton
    fun bindGetStateColab(usecase: IGetStateColab): GetStateColab

    @Binds
    @Singleton
    fun bindSetStateColab(usecase: ISetStateColab): SetStateColab

    @Binds
    @Singleton
    fun bindListEquipSec(usecase: IListEquipSec): ListEquipSec

    @Binds
    @Singleton
    fun bindDeleteEquipSec(usecase: IDeleteEquipSec): DeleteEquipSec

    @Binds
    @Singleton
    fun bindListPassenger(usecase: IListPassenger): ListPassenger

    @Binds
    @Singleton
    fun bindDeletePassenger(usecase: IDeletePassenger): DeletePassenger

    @Binds
    @Singleton
    fun bindGetEquip(usecase: IGetEquip): GetEquip

    @Binds
    @Singleton
    fun bindGetEquipSec(usecase: IGetEquipSec): GetEquipSec

    @Binds
    @Singleton
    fun bindGetColab(usecase: IGetDriver): GetDriver

    @Binds
    @Singleton
    fun bindGetPassengers(usecase: IGetPassengers): GetPassengers

}