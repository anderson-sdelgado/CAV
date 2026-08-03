package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.domain.repositories.variable.RecoverDataCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IRecoverDataCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource,
    private val equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource,
    private val involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource,
    private val vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource
): RecoverDataCardRepository {

    override suspend fun getIdEquip(id: Int): Result<Int> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getIdEquip(id).getOrThrow()
        }

    override suspend fun getIdEquipSecondary(idMain: Int, idSecondary: Int): Result<Int> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getIdEquipSecondary(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getDetailEquip(id: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailEquip(id).getOrThrow()
        }

    override suspend fun getDetailEquipSecondary(idMain: Int, idSecondary: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailEquipSecondary(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getDetailColab(id: Int): Result<String?>  =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailColab(id).getOrThrow()
        }

    override suspend fun getDetailPassengerColab(idMain: Int, idSecondary: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailPassengerColab(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getDetailVehicle(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailVehicle(idMain).getOrThrow()
        }

    override suspend fun getDetailDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailDriver(idMain).getOrThrow()
        }

    override suspend fun getDetailPassengerExternal(idMain: Int, idSecondary: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailPassengerExternal(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getDetailInvolvedExternal(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailInvolvedExternal(idMain).getOrThrow()
        }

    override suspend fun getDetailWitnessExternal(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailWitnessExternal(idMain).getOrThrow()
        }

    override suspend fun getDetailInvolvedColab(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailInvolvedColab(idMain).getOrThrow()
        }

    override suspend fun getDetailWitnessColab(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailWitnessColab(idMain).getOrThrow()
        }

    override suspend fun getRegColab(id: Int): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegColab(id).getOrThrow()
        }

    override suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegPassengerColab(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getRegColabInvolved(id: Int): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegColabInvolved(id).getOrThrow()
        }

    override suspend fun getRegColabWitness(id: Int): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegColabWitness(id).getOrThrow()
        }

    override suspend fun getStateColab(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateColab(id).getOrThrow()
        }

    override suspend fun getStatePassengerColab(
        idMain: Int,
        idSecondary: Int
    ): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStatePassengerColab(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getStatePassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStatePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getStateInvolvedExternal(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateInvolvedExternal(id).getOrThrow()
        }

    override suspend fun getStateInvolvedColab(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateInvolvedColab(id).getOrThrow()
        }

    override suspend fun getStateDriverExternal(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateDriver(id).getOrThrow()
        }

    override suspend fun getAddressPassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getAddressPassengerExternal(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getAddressDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getAddressDriver(idMain).getOrThrow()
        }

    override suspend fun getAddressExternal(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getAddressInvolved(idMain).getOrThrow()
        }

    override suspend fun getBrand(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getBrand(idMain).getOrThrow()
        }

    override suspend fun getPlate(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getPlate(idMain).getOrThrow()
        }

    override suspend fun getDocumentDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDocumentDriver(idMain).getOrThrow()
        }

    override suspend fun getDocumentPassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDocumentPassengerExternal(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getNameDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getNameDriver(idMain).getOrThrow()
        }

    override suspend fun getNamePassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getNamePassengerExternal(idMain, idSecondary).getOrThrow()
        }

    override suspend fun listEquipSecondary(idMain: Int): Result<List<EquipCard>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listEquipSecondary(idMain).getOrThrow()
        }

    override suspend fun listPassengerColab(idMain: Int): Result<List<ColabCard>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listPassengerColab(idMain).getOrThrow()
        }

    override suspend fun listPassengerExternal(idMain: Int): Result<List<PeopleExternal>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listPassengerExternal(idMain).getOrThrow()
        }

    override suspend fun listInvolvedExternal(): Result<List<PeopleExternal>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listInvolvedExternal().getOrThrow()
        }

    override suspend fun listWitnessExternal(): Result<List<PeopleExternal>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listWitnessExternal().getOrThrow()
        }

    override suspend fun listInvolvedColab(): Result<List<ColabCard>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listInvolvedColab().getOrThrow()
        }

    override suspend fun listWitnessColab(): Result<List<ColabCard>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listWitnessColab().getOrThrow()
        }

    override suspend fun getDocumentInvolved(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDocumentInvolved(idMain).getOrThrow()
        }

    override suspend fun getNameInvolved(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getNameInvolved(idMain).getOrThrow()
        }

    override suspend fun getNameWitness(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getNameWitness(idMain).getOrThrow()
        }

    override suspend fun getPhoneDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getPhoneDriver(idMain).getOrThrow()
        }

    override suspend fun getPhoneInvolved(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getPhoneInvolved(idMain).getOrThrow()
        }

    override suspend fun getPhoneWitness(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getPhoneWitness(idMain).getOrThrow()
        }

    override suspend fun getPhonePassengerInvolved(idMain: Int, idSecondary: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getPhonePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getResultBreathalyzer(idMain: Int): Result<Boolean?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getResultBreathalyzer(idMain).getOrThrow()
        }

    override suspend fun getRealizedBreathalyzer(idMain: Int): Result<Boolean?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRealizedBreathalyzer(idMain).getOrThrow()
        }

    override suspend fun getCountBreathalyzer(idMain: Int): Result<Double?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getCountBreathalyzer(idMain).getOrThrow()
        }

    override suspend fun getBreathalyzer(idMain: Int): Result<Triple<Boolean?, Boolean?, Double?>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getBreathalyzer(idMain).getOrThrow()
        }

    override suspend fun listVehicleOwn(): Result<List<VehicleOwn>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listVehicleOwn().getOrThrow()
        }

    override suspend fun listVehicleInvolved(): Result<List<VehicleExternal>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listVehicleExternal().getOrThrow()
        }

    override suspend fun getRegColab(): Result<Long?> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.getRegColab().getOrThrow()
        }

    override suspend fun getStateColab(): Result<State?> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.getState().getOrThrow()
        }

    override suspend fun getDetailColab(): Result<String?> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.getDetail().getOrThrow()
        }

    override suspend fun getPhone(): Result<String?> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.getPhone().getOrThrow()
        }

    override suspend fun getStateInvolvedExternal(): Result<State?> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.getState().getOrThrow()
        }

    override suspend fun getDetailEquip(): Result<String?> =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.getDetail().getOrThrow()
        }

    override suspend fun getDetailInvolvedExternal(): Result<String?> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.getDetail().getOrThrow()
        }

    override suspend fun getDetailVehicle(): Result<String?> =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.getDetail().getOrThrow()
        }

    override suspend fun getIdEquip(): Result<Int?> =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.getIdEquip().getOrThrow()
        }

    override suspend fun getPlate(): Result<String?> =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.getPlate().getOrThrow()
        }

    override suspend fun getBrand(): Result<String?> =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.getBrand().getOrThrow()
        }

    override suspend fun getDocument(): Result<String?> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.getDocument().getOrThrow()
        }

    override suspend fun getName(): Result<String?> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.getName().getOrThrow()
        }

    override suspend fun getResultBreathalyzer(): Result<Boolean?> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.getResultBreathalyzer().getOrThrow()
        }

    override suspend fun getRealizedBreathalyzer(): Result<Boolean?> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.getRealizedBreathalyzer().getOrThrow()
        }

    override suspend fun getCountBreathalyzer(): Result<Double?> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.getCountBreathalyzer().getOrThrow()
        }

}
