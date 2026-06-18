package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleInvolved
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.domain.repositories.variable.RecoverDataCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IRecoverDataCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource
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

    override suspend fun getDetailPassengerInvolved(idMain: Int, idSecondary: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailPassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getDetailInvolved(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailInvolved(idMain).getOrThrow()
        }

    override suspend fun getDetailWitness(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDetailWitness(idMain).getOrThrow()
        }

    override suspend fun getRegColab(id: Int): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegColab(id).getOrThrow()
        }

    override suspend fun getRegPassengerColab(idMain: Int, idSecondary: Int): Result<Long> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getRegPassengerColab(idMain, idSecondary).getOrThrow()
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

    override suspend fun getStateWitness(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateWitness(id).getOrThrow()
        }

    override suspend fun getStatePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStatePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getStateInvolved(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateInvolved(id).getOrThrow()
        }

    override suspend fun getStateDriver(id: Int): Result<State> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getStateDriver(id).getOrThrow()
        }

    override suspend fun getAddressPassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getAddressPassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getAddressDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getAddressDriver(idMain).getOrThrow()
        }

    override suspend fun getAddressInvolved(idMain: Int): Result<String?> =
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

    override suspend fun getDocumentPassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDocumentPassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun getNameDriver(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getNameDriver(idMain).getOrThrow()
        }

    override suspend fun getNamePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getNamePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun listIdEquipSecondary(idMain: Int): Result<List<Int>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listIdEquipSecondary(idMain).getOrThrow()
        }

    override suspend fun listPassengerColab(idMain: Int): Result<List<ColabCard>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listPassengerColab(idMain).getOrThrow()
        }

    override suspend fun listPassengerInvolved(idMain: Int): Result<List<Involved>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listPassengerInvolved(idMain).getOrThrow()
        }

    override suspend fun listInvolved(): Result<List<Involved>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listInvolved().getOrThrow()
        }

    override suspend fun listWitness(): Result<List<Involved>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listWitness().getOrThrow()
        }

    override suspend fun getDocumentInvolved(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDocumentInvolved(idMain).getOrThrow()
        }

    override suspend fun getDocumentWitness(idMain: Int): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getDocumentWitness(idMain).getOrThrow()
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

    override suspend fun getPhonePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.getPhonePassengerInvolved(idMain, idSecondary).getOrThrow()
        }

    override suspend fun listVehicleOwn(): Result<List<VehicleOwn>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listVehicleOwn().getOrThrow()
        }

    override suspend fun listVehicleInvolved(): Result<List<VehicleInvolved>> =
        call(getClassAndMethod()) {
            cardSharedPreferencesDatasource.listVehicleInvolved().getOrThrow()
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

    override suspend fun getPhoneInvolved(): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getStateInvolved(): Result<State?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailEquip(): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailInvolved(): Result<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun getDetailVehicle(): Result<String?> {
        TODO("Not yet implemented")
    }

}
