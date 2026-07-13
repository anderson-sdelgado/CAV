package br.com.usinasantafe.cav.infra.datasource.sharedpreferences

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleInvolved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.infra.models.sharedpreferences.CardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.LocalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
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
    suspend fun getLocal(): Result<LocalSharedPreferencesModel>
    suspend fun listIdDataLocal(): Result<List<Int>>
    suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult
    suspend fun listIdSupportTeams(): Result<List<Int>>
    suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult
    suspend fun getObs(): Result<String?>
    suspend fun setObs(text: String): EmptyResult
}

interface InsertCardSharedPreferencesDatasource {
    suspend fun addVehicleOwn(entity: VehicleOwnSharedPreferencesModel): Result<Int>
    suspend fun addEquipSec(equipSharedPreferencesModel: EquipSharedPreferencesModel, idMain: Int): Result<Int>
    suspend fun addPassengerColab(colabSharedPreferencesModel: ColabSharedPreferencesModel, idMain: Int): Result<Int>
    suspend fun addVehicleInvolved(entity: VehicleInvolvedSharedPreferencesModel): Result<Int>
    suspend fun addInvolved(entity: InvolvedSharedPreferencesModel): Result<Int>
    suspend fun addWitness(entity: InvolvedSharedPreferencesModel): Result<Int>
    suspend fun addPassengerInvolved(entity: InvolvedSharedPreferencesModel, idMain: Int): Result<Int>
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
    suspend fun getDetailPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailInvolved(idMain: Int): Result<String?>
    suspend fun getDetailWitness(idMain: Int): Result<String?>
    suspend fun getRegColab(idMain: Int): Result<Long>
    suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long>
    suspend fun getStateColab(idMain: Int): Result<State>
    suspend fun getStatePassengerColab(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStatePassengerInvolved(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStateInvolved(idMain: Int): Result<State>
    suspend fun getStateDriver(idMain: Int): Result<State>
    suspend fun getAddressPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getAddressDriver(idMain: Int): Result<String?>
    suspend fun getAddressInvolved(idMain: Int): Result<String?>
    suspend fun getBrand(idMain: Int): Result<String?>
    suspend fun getPlate(idMain: Int): Result<String?>
    suspend fun getDocumentDriver(idMain: Int): Result<String?>
    suspend fun getDocumentPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getNameDriver(idMain: Int): Result<String?>
    suspend fun getNamePassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun listEquipSecondary(idMain: Int): Result<List<EquipCard>>
    suspend fun listPassengerColab(idMain: Int): Result<List<ColabCard>>
    suspend fun listPassengerInvolved(idMain: Int): Result<List<Involved>>
    suspend fun listInvolved(): Result<List<Involved>>
    suspend fun listWitness(): Result<List<Involved>>
    suspend fun getDocumentInvolved(idMain: Int): Result<String?>
    suspend fun getNameInvolved(idMain: Int): Result<String?>
    suspend fun getNameWitness(idMain: Int): Result<String?>
    suspend fun getPhoneDriver(idMain: Int): Result<String?>
    suspend fun getPhoneInvolved(idMain: Int): Result<String?>
    suspend fun getPhoneWitness(idMain: Int): Result<String?>
    suspend fun getPhonePassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun listVehicleOwn(): Result<List<VehicleOwn>>
    suspend fun listVehicleInvolved(): Result<List<VehicleInvolved>>
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
    suspend fun updateDetailPassengerInvolved(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailInvolved(text: String, idMain: Int): EmptyResult
    suspend fun updateDetailWitness(text: String, idMain: Int): EmptyResult
    suspend fun updateRegColab(regColab: Long, idMain: Int): EmptyResult
    suspend fun updateRegPassengerColab(regColab: Long, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateStateColab(state: State, idMain: Int): EmptyResult
    suspend fun updateStatePassengerColab(state: State, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateStateDriver(state: State, idMain: Int): EmptyResult
    suspend fun updateStatePassengerInvolved(state: State, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateStateInvolved(state: State, idMain: Int): EmptyResult
    suspend fun updateStateWitness(state: State, idMain: Int): EmptyResult
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
}

interface DeleteCardSharedPreferencesDatasource {
    suspend fun deleteVehicleOwn(idMain: Int): EmptyResult
    suspend fun deleteEquipSecondary(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deleteVehicleInvolved(idMain: Int): EmptyResult
    suspend fun deleteInvolved(idMain: Int): EmptyResult
    suspend fun deleteWitness(idMain: Int): EmptyResult
    suspend fun deletePassengerColab(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePassengerInvolved(idMain: Int, idSecondary: Int): EmptyResult
}
