package br.com.usinasantafe.cav.domain.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.entities.variable.VehicleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardRepository :
    BasicCardRepository,
    InsertCardRepository,
    RecoverDataCardRepository,
    UpdateCardRepository,
    DeleteCardRepository,
    SendCardRepository {

    suspend fun clean(): EmptyResult
    suspend fun has(): Result<Boolean>
}

interface SendCardRepository {
    suspend fun save(): EmptyResult
    suspend fun send(token: String): EmptyResult
    suspend fun hasSend(): Result<Boolean>
    suspend fun delete(): EmptyResult
}

interface BasicCardRepository{
    suspend fun setRegAttendant(regColab: Long): EmptyResult
    suspend fun setIdCar(idEquip: Int): EmptyResult
    suspend fun setLocal(entity: Local): EmptyResult
    suspend fun listIdNature(): Result<List<Int>>
    suspend fun setIdNatureList(idList: List<Int>): EmptyResult
    suspend fun getRegAttendant(): Result<Long?>
    suspend fun getIdCar(): Result<Int?>
    suspend fun listIdTypeAccident(): Result<List<Int>>
    suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult
    suspend fun getLocal(): Result<Local?>
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

interface InsertCardRepository {
    suspend fun setIdEquip(idEquip: Int): EmptyResult
    suspend fun setDetailEquip(text: String): EmptyResult
    suspend fun setDetailEquipSec(text: String, idMain: Int): Result<Int>
    suspend fun setDetailDriver(text: String): Result<Int>
    suspend fun setDetailColab(text: String): Result<Int>
    suspend fun setDetailPassengerColab(text: String, idMain: Int): Result<Int>
    suspend fun setDetailVehicle(text: String): EmptyResult
    suspend fun setDetailInvolvedExternal(text: String): Result<Int>
    suspend fun setDetailWitnessExternal(text: String): Result<Int>
    suspend fun setDetailPassengerExternal(text: String, idMain: Int): Result<Int>
    suspend fun setDetailInvolvedColab(text: String): Result<Int>
    suspend fun setDetailWitnessColab(text: String): Result<Int>
    suspend fun setRegColab(regColab: Long): EmptyResult
    suspend fun setStateColab(state: State): EmptyResult
    suspend fun setBrand(text: String): EmptyResult
    suspend fun setPlate(text: String): EmptyResult
    suspend fun setDocument(text: String): EmptyResult
    suspend fun setStateInvolved(state: State): EmptyResult
    suspend fun setName(text: String): EmptyResult
    suspend fun setPhone(text: String): EmptyResult
    suspend fun setDataInitialBreathalyzer(flagRealized: Boolean?, flagResult: Boolean?): EmptyResult
    suspend fun setCountBreathalyzer(count: Double?): EmptyResult
}

interface RecoverDataCardRepository {
    suspend fun getIdEquip(id: Int): Result<Int>
    suspend fun getIdEquipSecondary(idMain: Int, idSecondary: Int): Result<Int>
    suspend fun getDetailEquip(id: Int): Result<String?>
    suspend fun getDetailEquipSecondary(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailColab(id: Int): Result<String?>
    suspend fun getDetailPassengerColab(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailVehicle(idMain: Int): Result<String?>
    suspend fun getDetailDriver(idMain: Int): Result<String?>
    suspend fun getDetailPassengerExternal(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailInvolvedExternal(idMain: Int): Result<String?>
    suspend fun getDetailWitnessExternal(idMain: Int): Result<String?>
    suspend fun getDetailInvolvedColab(idMain: Int): Result<String?>
    suspend fun getDetailWitnessColab(idMain: Int): Result<String?>
    suspend fun getRegColab(id: Int): Result<Long>
    suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long>
    suspend fun getRegColabInvolved(id: Int): Result<Long>
    suspend fun getRegColabWitness(id: Int): Result<Long>
    suspend fun getStateColab(id: Int): Result<State>
    suspend fun getStatePassengerColab(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStatePassengerExternal(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStateInvolvedExternal(id: Int): Result<State>
    suspend fun getStateInvolvedColab(id: Int): Result<State>
    suspend fun getStateDriverExternal(id: Int): Result<State>
    suspend fun getAddressPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getAddressDriver(idMain: Int): Result<String?>
    suspend fun getAddressExternal(idMain: Int): Result<String?>
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
    suspend fun getResultBreathalyzer(idMain: Int): Result<Boolean?>
    suspend fun getRealizedBreathalyzer(idMain: Int): Result<Boolean?>
    suspend fun getCountBreathalyzer(idMain: Int): Result<Double?>
    suspend fun listVehicleOwn(): Result<List<VehicleOwn>>
    suspend fun listVehicleInvolved(): Result<List<VehicleExternal>>
    suspend fun getRegColab(): Result<Long?>
    suspend fun getStateColab(): Result<State?>
    suspend fun getDetailColab(): Result<String?>
    suspend fun getPhone(): Result<String?>
    suspend fun getStateInvolvedExternal(): Result<State?>
    suspend fun getDetailEquip(): Result<String?>
    suspend fun getDetailInvolvedExternal(): Result<String?>
    suspend fun getDetailVehicle(): Result<String?>
    suspend fun getIdEquip(): Result<Int?>
    suspend fun getPlate(): Result<String?>
    suspend fun getBrand(): Result<String?>
    suspend fun getDocument(): Result<String?>
    suspend fun getName(): Result<String?>
    suspend fun getResultBreathalyzer(): Result<Boolean?>
    suspend fun getRealizedBreathalyzer(): Result<Boolean?>
    suspend fun getCountBreathalyzer(): Result<Double?>
}

interface UpdateCardRepository {
    suspend fun updateIdEquip(idEquip: Int, id: Int): EmptyResult
    suspend fun updateIdEquipSecondary(idEquip: Int, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailEquip(text: String, id: Int): EmptyResult
    suspend fun updateDetailEquipSecondary(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailColab(text: String, id: Int): EmptyResult
    suspend fun updateDetailDriver(text: String, id: Int): EmptyResult
    suspend fun updateDetailPassengerColab(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailVehicle(text: String, id: Int): EmptyResult
    suspend fun updateDetailPassengerExternal(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailInvolvedExternal(text: String, id: Int): EmptyResult
    suspend fun updateDetailWitnessExternal(text: String, id: Int): EmptyResult
    suspend fun updateDetailInvolvedColab(text: String, id: Int): EmptyResult
    suspend fun updateDetailWitnessColab(text: String, id: Int): EmptyResult
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
    suspend fun updateDataInitialBreathalyzer(flagRealized: Boolean?, flagResult: Boolean?, idMain: Int): EmptyResult
    suspend fun updateCountBreathalyzer(count: Double?, idMain: Int): EmptyResult
}

interface DeleteCardRepository {
    suspend fun deleteVehicleOwn(id: Int): EmptyResult
    suspend fun deleteEquipSecondary(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deleteVehicleExternal(id: Int): EmptyResult
    suspend fun deleteInvolvedExternal(id: Int): EmptyResult
    suspend fun deleteWitnessExternal(id: Int): EmptyResult
    suspend fun deleteInvolvedColab(id: Int): EmptyResult
    suspend fun deleteWitnessColab(id: Int): EmptyResult
    suspend fun deletePassengerColab(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePassengerExternal(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePhoto(url: String): EmptyResult
}
