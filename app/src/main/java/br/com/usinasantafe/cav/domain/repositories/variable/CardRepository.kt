package br.com.usinasantafe.cav.domain.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.entities.variable.VehicleInvolved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardRepository :
    BasicCardRepository,
    InsertCardRepository,
    RecoverDataCardRepository,
    UpdateCardRepository,
    DeleteCardRepository,
    SaveAndSendCardRepository {

    suspend fun clean(): EmptyResult
    suspend fun has(): Result<Boolean>
}

interface SaveAndSendCardRepository {
    suspend fun save(): EmptyResult
    suspend fun send(): EmptyResult
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
    suspend fun setDetailInvolved(text: String): Result<Int>
    suspend fun setDetailWitness(text: String): Result<Int>
    suspend fun setDetailPassengerInvolved(text: String, idMain: Int): Result<Int>
    suspend fun setRegColab(regColab: Long): EmptyResult
    suspend fun setStateColab(state: State): EmptyResult
    suspend fun setBrand(text: String): EmptyResult
    suspend fun setPlate(text: String): EmptyResult
    suspend fun setDocument(text: String): EmptyResult
    suspend fun setStateInvolved(state: State): EmptyResult
    suspend fun setName(text: String): EmptyResult
    suspend fun setPhone(text: String): EmptyResult
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
    suspend fun getDetailPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?>
    suspend fun getDetailInvolved(idMain: Int): Result<String?>
    suspend fun getDetailWitness(idMain: Int): Result<String?>
    suspend fun getRegColab(id: Int): Result<Long>
    suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long>
    suspend fun getStateColab(id: Int): Result<State>
    suspend fun getStatePassengerColab(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStatePassengerInvolved(idMain: Int, idSecondary: Int): Result<State>
    suspend fun getStateInvolved(id: Int): Result<State>
    suspend fun getStateDriver(id: Int): Result<State>
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
    suspend fun getRegColab(): Result<Long?>
    suspend fun getStateColab(): Result<State?>
    suspend fun getDetailColab(): Result<String?>
    suspend fun getPhone(): Result<String?>
    suspend fun getStateInvolved(): Result<State?>
    suspend fun getDetailEquip(): Result<String?>
    suspend fun getDetailInvolved(): Result<String?>
    suspend fun getDetailVehicle(): Result<String?>
    suspend fun getIdEquip(): Result<Int?>
    suspend fun getPlate(): Result<String?>
    suspend fun getBrand(): Result<String?>
    suspend fun getDocument(): Result<String?>
    suspend fun getName(): Result<String?>
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
    suspend fun updateDetailPassengerInvolved(text: String, idMain: Int, idSecondary: Int): EmptyResult
    suspend fun updateDetailInvolved(text: String, id: Int): EmptyResult
    suspend fun updateDetailWitness(text: String, id: Int): EmptyResult
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

interface DeleteCardRepository {
    suspend fun deleteVehicleOwn(id: Int): EmptyResult
    suspend fun deleteEquipSecondary(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deleteVehicleInvolved(id: Int): EmptyResult
    suspend fun deleteInvolved(id: Int): EmptyResult
    suspend fun deleteWitness(id: Int): EmptyResult
    suspend fun deletePassengerColab(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePassengerInvolved(idMain: Int, idSecondary: Int): EmptyResult
    suspend fun deletePhoto(url: String): EmptyResult
}
