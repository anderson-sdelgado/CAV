package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardSharedPreferencesDatasource:
        BasicCardSharedPreferencesDatasource,
        InsertCardSharedPreferencesDatasource,
        RecoverDataCardSharedPreferencesDatasource,
        UpdateCardSharedPreferencesDatasource,
        DeleteCardSharedPreferencesDatasource {
    suspend fun clean(): EmptyResult
    suspend fun has(): Result<Boolean>
    suspend fun get(): Result<CardSharedPreferencesModel>
    suspend fun save(model: CardSharedPreferencesModel): EmptyResult
    suspend fun updateModel(
        block: CardSharedPreferencesModel.() -> Unit
    )
    suspend fun <T> readModel(
        block: CardSharedPreferencesModel.() -> T
    ): T
}

interface BasicCardSharedPreferencesDatasource {
    suspend fun setRegAttendant(regColab: Long): EmptyResult
    suspend fun setIdCar(idEquip: Int): EmptyResult
    suspend fun setLocal(model: LocalSharedPreferencesModel): EmptyResult
    suspend fun listIdNature(): Result<List<Int>>
    suspend fun setIdNatureList(idList: List<Int>): EmptyResult
    suspend fun getRegAttendant(): Result<Long?>
    suspend fun getIdCar(): Result<Int?>
    suspend fun listIdTypeAccident(): Result<List<Int>>
    suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult
    suspend fun getLocal(): Result<LocalSharedPreferencesModel?>
    suspend fun listIdDataLocal(): Result<List<Int>>
    suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult
    suspend fun listIdSupportTeams(): Result<List<Int>>
    suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult
    suspend fun getObs(): Result<String?>
    suspend fun setObs(text: String): EmptyResult
    suspend fun setPhoto(url: String): EmptyResult
    suspend fun listPhoto(): Result<List<String>>
    suspend fun hasLocal(): Result<Boolean>
}

interface InsertCardSharedPreferencesDatasource {
    suspend fun addVehicleOwn(entity: VehicleOwnSharedPreferencesModel): Result<Int>
    suspend fun addEquipSec(equipCardSharedPreferencesModel: EquipCardSharedPreferencesModel, idMain: Int): Result<Int>
    suspend fun addPassengerColab(colabCardSharedPreferencesModel: ColabCardSharedPreferencesModel, idMain: Int): Result<Int>
    suspend fun addVehicleExternal(entity: VehicleExternalSharedPreferencesModel): Result<Int>
    suspend fun addInvolvedExternal(entity: PeopleExternalSharedPreferencesModel): Result<Int>
    suspend fun addWitnessExternal(entity: PeopleExternalSharedPreferencesModel): Result<Int>
    suspend fun addPassengerExternal(entity: PeopleExternalSharedPreferencesModel, idMain: Int): Result<Int>
    suspend fun addInvolvedColab(entity: ColabCardSharedPreferencesModel): Result<Int>
    suspend fun addWitnessColab(entity: ColabCardSharedPreferencesModel): Result<Int>
}

interface RecoverDataCardSharedPreferencesDatasource {
    suspend fun getIdEquip(idMain: Int): Result<Int>
    suspend fun getIdEquipSecondary(idMain: Int, idSecondary: Int): Result<Int>
    suspend fun getDetailEquip(idMain: Int): Result<String?>
    suspend fun getDetailEquipSecondary(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailColab(idMain: Int): Result<String?>
    suspend fun getDetailPassengerColab(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailVehicle(idMain: Int): Result<String?>
    suspend fun getDetailDriver(idMain: Int): Result<String?>
    suspend fun getDetailPassengerExternal(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailInvolvedExternal(idMain: Int): Result<String?>
    suspend fun getDetailWitnessExternal(idMain: Int): Result<String?>
    suspend fun getDetailInvolvedColab(idMain: Int): Result<String?>
    suspend fun getDetailWitnessColab(idMain: Int): Result<String?>
    suspend fun getRegColab(idMain: Int): Result<Long>
    suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long>
    suspend fun getRegColabInvolved(idMain: Int): Result<Long>
    suspend fun getRegColabWitness(idMain: Int): Result<Long>
    suspend fun getStateColab(idMain: Int): Result<State>
    suspend fun getStatePassengerColab(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStatePassengerInvolved(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStateInvolvedExternal(idMain: Int): Result<State>
    suspend fun getStateInvolvedColab(idMain: Int): Result<State>
    suspend fun getStateDriver(idMain: Int): Result<State>
    suspend fun getAddressPassengerExternal(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getAddressDriver(idMain: Int): Result<String?>
    suspend fun getAddressInvolved(idMain: Int): Result<String?>
    suspend fun getBrand(idMain: Int): Result<String?>
    suspend fun getPlate(idMain: Int): Result<String?>
    suspend fun getDocumentDriver(idMain: Int): Result<String?>
    suspend fun getDocumentPassengerExternal(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getNameDriver(idMain: Int): Result<String?>
    suspend fun getNamePassengerExternal(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun listEquipSecondary(idMain: Int): Result<List<EquipCard>>
    suspend fun listPassengerColab(idMain: Int): Result<List<ColabCard>>
    suspend fun listPassengerExternal(idMain: Int): Result<List<PeopleExternal>>
    suspend fun listInvolvedExternal(): Result<List<PeopleExternal>>
    suspend fun listWitnessExternal(): Result<List<PeopleExternal>>
    suspend fun listInvolvedColab(): Result<List<ColabCard>>
    suspend fun listWitnessColab(): Result<List<ColabCard>>
    suspend fun getDocumentInvolved(idMain: Int): Result<String?>
    suspend fun getNameInvolved(idMain: Int): Result<String?>
    suspend fun getNameWitness(idMain: Int): Result<String?>
    suspend fun getPhoneDriver(idMain: Int): Result<String?>
    suspend fun getPhoneInvolved(idMain: Int): Result<String?>
    suspend fun getPhoneWitness(idMain: Int): Result<String?>
    suspend fun getPhonePassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun listVehicleOwn(): Result<List<VehicleOwn>>
    suspend fun listVehicleExternal(): Result<List<VehicleExternal>>
    suspend fun getResultBreathalyzer(idMain: Int): Result<Boolean?>
    suspend fun getRealizedBreathalyzer(idMain: Int): Result<Boolean?>
    suspend fun getCountBreathalyzer(idMain: Int): Result<Double?>
}

interface UpdateCardSharedPreferencesDatasource {
    suspend fun updateIdEquip(idEquip: Int, idMain: Int): EmptyResult
    suspend fun updateIdEquipSecondary(idEquip: Int, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailEquip(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailEquipSecondary(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailColab(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailDriver(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailPassengerColab(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailVehicle(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailPassengerExternal(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailInvolvedExternal(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailWitnessExternal(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailInvolvedColab(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailWitnessColab(text: String, idMain: Int): EmptyResult
    suspend fun updateRegColab(regColab: Long, idMain: Int): EmptyResult
    suspend fun updateRegPassengerColab(regColab: Long, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateRegColabInvolved(regColab: Long, idMain: Int): EmptyResult
    suspend fun updateRegColabWitness(regColab: Long, idMain: Int): EmptyResult
    suspend fun updateStateColab(state: State, idMain: Int): EmptyResult
    suspend fun updateStatePassengerColab(state: State, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateStateDriver(state: State, idMain: Int): EmptyResult
    suspend fun updateStatePassengerExternal(state: State, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateStateInvolvedExternal(state: State, idMain: Int): EmptyResult
    suspend fun updateStateWitnessExternal(state: State, idMain: Int): EmptyResult
    suspend fun updateStateInvolvedColab(state: State, idMain: Int): EmptyResult
    suspend fun updateStateWitnessColab(state: State, idMain: Int): EmptyResult
    suspend fun updateAddressPassengerInvolved(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateAddressInvolved(text: String, idMain: Int): EmptyResult
    suspend fun updateAddressDriver(text: String, idMain: Int): EmptyResult
    suspend fun updateBrand(text: String, idMain: Int): EmptyResult
    suspend fun updatePlate(text: String, idMain: Int): EmptyResult
    suspend fun updateDocumentDriver(text: String, idMain: Int): EmptyResult
    suspend fun updateDocumentPassengerInvolved(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDocumentInvolved(text: String, idMain: Int): EmptyResult
    suspend fun updateNameDriver(text: String, idMain: Int): EmptyResult
    suspend fun updateNamePassengerInvolved(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateNameInvolved(text: String, idMain: Int): EmptyResult
    suspend fun updateNameWitness(text: String, idMain: Int): EmptyResult
    suspend fun updatePhoneDriver(text: String, idMain: Int): EmptyResult
    suspend fun updatePhoneInvolved(text: String, idMain: Int): EmptyResult
    suspend fun updatePhoneWitness(text: String, idMain: Int): EmptyResult
    suspend fun updatePhonePassengerInvolved(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDataInitialBreathalyzer(
        flagRealized: Boolean?,
        flagResult: Boolean?,
        idMain: Int
    ): EmptyResult
    suspend fun updateCountBreathalyzer(
        count: Double?,
        idMain: Int
    ): EmptyResult
}

interface DeleteCardSharedPreferencesDatasource {
    suspend fun deleteVehicleOwn(idMain: Int): EmptyResult
    suspend fun deleteEquipSecondary(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deleteVehicleExternal(idMain: Int): EmptyResult
    suspend fun deleteInvolvedExternal(idMain: Int): EmptyResult
    suspend fun deleteWitnessExternal(idMain: Int): EmptyResult
    suspend fun deleteInvolvedColab(idMain: Int): EmptyResult
    suspend fun deleteWitnessColab(idMain: Int): EmptyResult
    suspend fun deletePassengerColab(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePassengerInvolved(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePhoto(url: String): EmptyResult
}
