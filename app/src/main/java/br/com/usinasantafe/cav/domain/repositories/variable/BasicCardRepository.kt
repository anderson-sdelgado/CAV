package br.com.usinasantafe.cav.domain.repositories.variable

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.utils.EmptyResult

interface CardRepository :
    BasicCardRepository,
    InsertCardRepository,
    RecoverDataCardRepository,
    UpdateCardRepository

interface BasicCardRepository{
    suspend fun setRegAttendant(regColab: Long): EmptyResult
    suspend fun setIdCar(idEquip: Int): EmptyResult
    suspend fun setLocal(entity: Local): EmptyResult
    suspend fun listIdNature(): Result<List<Int>>
    suspend fun setIdNatureList(idList: List<Int>): EmptyResult
    suspend fun getRegAttendant(): Result<Long>
    suspend fun getIdCar(): Result<Int>
    suspend fun listIdTypeAccident(): Result<List<Int>>
    suspend fun setIdTypeAccidentList(idList: List<Int>): EmptyResult
    suspend fun clean(): EmptyResult
    suspend fun getLocal(): Result<Local>
    suspend fun listIdDataLocal(): Result<List<Int>>
    suspend fun setIdDataLocalList(idList: List<Int>): EmptyResult
    suspend fun listIdSupportTeams(): Result<List<Int>>
    suspend fun setIdSupportTeamsList(idList: List<Int>): EmptyResult
}

interface InsertCardRepository {
    suspend fun setIdEquip(idEquip: Int): EmptyResult
    suspend fun setDetailEquip(text: String): EmptyResult
    suspend fun setDetailEquipSecondary(text: String, idMain: Int): EmptyResult
    suspend fun setDetailColab(text: String): Result<Int>
    suspend fun setDetailDriver(text: String): Result<Int>
    suspend fun setDetailPassengerColab(text: String, idMain: Int): EmptyResult
    suspend fun setDetailVehicle(text: String): EmptyResult
    suspend fun setDetailPassengerInvolved(text: String, idMain: Int): EmptyResult
    suspend fun setDetailInvolved(text: String): Result<Int>
    suspend fun setDetailWitness(text: String): Result<Int>
    suspend fun setRegColab(regColab: Long): EmptyResult
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
    suspend fun getRegColab(id: Int): Result<Int>
    suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Int>
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
}
