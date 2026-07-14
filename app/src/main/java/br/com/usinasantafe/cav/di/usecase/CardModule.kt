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
    fun bindGetStateColab(usecase: IGetState): GetState

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
    fun bindDeletePassenger(usecase: IDeleteInvolved): DeleteInvolved

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
    fun bindGetDescVehicle(usecase: IGetDescVehicle): GetDescVehicle

    @Binds
    @Singleton
    fun bindGetDescDriver(usecase: IGetDescDriver): GetDescDriver

    @Binds
    @Singleton
    fun bindGetPlate(usecase: IGetPlate): GetPlate

    @Binds
    @Singleton
    fun bindSetPlate(usecase: ISetPlate): SetPlate

    @Binds
    @Singleton
    fun bindGetBrand(usecase: IGetBrand): GetBrand

    @Binds
    @Singleton
    fun bindSetBrand(usecase: ISetBrand): SetBrand

    @Binds
    @Singleton
    fun bindGetDocument(usecase: IGetDocument): GetDocument

    @Binds
    @Singleton
    fun bindSetDocument(usecase: ISetDocument): SetDocument

    @Binds
    @Singleton
    fun bindGetName(usecase: IGetName): GetName

    @Binds
    @Singleton
    fun bindSetName(usecase: ISetName): SetName

    @Binds
    @Singleton
    fun bindGetAddress(usecase: IGetAddress): GetAddress

    @Binds
    @Singleton
    fun bindSetAddress(usecase: ISetAddress): SetAddress

    @Binds
    @Singleton
    fun bindSetPhone(usecase: ISetPhone): SetPhone

    @Binds
    @Singleton
    fun bindGetPhone(usecase: IGetPhone): GetPhone

    @Binds
    @Singleton
    fun bindDeleteEquip(usecase: IDeleteVehicleOwn): DeleteVehicleOwn

    @Binds
    @Singleton
    fun bindDeleteVehicle(usecase: IDeleteVehicleInvolved): DeleteVehicleInvolved

    @Binds
    @Singleton
    fun bindListInvolved(usecase: IListInvolved): ListInvolved

    @Binds
    @Singleton
    fun bindListWitness(usecase: IListWitness): ListWitness

    @Binds
    @Singleton
    fun bindGetDescOption(usecase: IGetDescOption): GetDescOption

    @Binds
    @Singleton
    fun bindGetRegAttendant(usecase: IGetRegAttendant): GetRegAttendant

    @Binds
    @Singleton
    fun bindGetNroCar(usecase: IGetNroCar): GetNroCar

    @Binds
    @Singleton
    fun bindGetObs(usecase: IGetObs): GetObs

    @Binds
    @Singleton
    fun bindSetObs(usecase: ISetObs): SetObs

    @Binds
    @Singleton
    fun bindListPhoto(usecase: IListPhoto): ListPhoto

    @Binds
    @Singleton
    fun bindSetPhoto(usecase: ISetPhoto): SetPhoto

    @Binds
    @Singleton
    fun bindDeletePhoto(usecase: IDeletePhoto): DeletePhoto

    @Binds
    @Singleton
    fun bindSaveCard(usecase: ISaveCard): SaveCard

}