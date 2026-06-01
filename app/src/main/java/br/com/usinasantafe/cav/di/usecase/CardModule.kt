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
    fun bindGetDetailVehicleOwn(usecase: IGetDetail): GetDetail

    @Binds
    @Singleton
    fun bindSetDetailVehicleOwn(usecase: ISetDetail): SetDetail

    @Binds
    @Singleton
    fun bindGetRegColab(usecase: IGetRegColab): GetRegColab

    @Binds
    @Singleton
    fun bindSetColab(usecase: ISetColab): SetColab

    @Binds
    @Singleton
    fun bindGetStateColab(usecase: IGetIdState): GetIdState

    @Binds
    @Singleton
    fun bindSetStateColab(usecase: ISetState): SetState

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
    fun bindGetDescEquip(usecase: IGetDescEquip): GetDescEquip

    @Binds
    @Singleton
    fun bindGetEquipSec(usecase: IGetDescEquipSec): GetDescEquipSec

    @Binds
    @Singleton
    fun bindGetColab(usecase: IGetDescColab): GetDescColab

    @Binds
    @Singleton
    fun bindGetPassengers(usecase: IGetDescPassengers): GetDescPassengers

    @Binds
    @Singleton
    fun bindListVehicleOwn(usecase: IListVehicleOwn): ListVehicleOwn

    @Binds
    @Singleton
    fun bindListVehicleInvolved(usecase: IListVehicleInvolved): ListVehicleInvolved

    @Binds
    @Singleton
    fun bindGetDescState(usecase: IGetDescState): GetDescState

    @Binds
    @Singleton
    fun bindGetDescVehicle(usecase: IGetDescVehicle): GetDescVehicle

    @Binds
    @Singleton
    fun bindGetDescDriver(usecase: IGetDescDriver): GetDescDriver

}