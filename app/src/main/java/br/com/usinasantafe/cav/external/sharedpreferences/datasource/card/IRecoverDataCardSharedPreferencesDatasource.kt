package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import br.com.usinasantafe.cav.domain.entities.variable.ColabCard
import br.com.usinasantafe.cav.domain.entities.variable.EquipCard
import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleExternal
import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.RecoverDataCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.sharedPreferencesModelToEntity
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject
import javax.inject.Provider

class IRecoverDataCardSharedPreferencesDatasource @Inject constructor(
    private val datasource: Provider<CardSharedPreferencesDatasource>
): RecoverDataCardSharedPreferencesDatasource {

    override suspend fun getIdEquip(idMain: Int): Result<Int> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.equip?.idEquip.required("idEquip")
            }
        }

    override suspend fun getIdEquipSecondary(
        idMain: Int,
        idSecondary: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.equipSecList?.find { it.id == idSecondary }?.idEquip.required("idEquip")
            }
        }

    override suspend fun getDetailEquip(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.equip?.detail
            }
        }

    override suspend fun getDetailEquipSecondary(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.equipSecList?.find { it.id == idSecondary }?.detail
            }
        }

    override suspend fun getDetailColab(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.detail
            }
        }

    override suspend fun getDetailPassengerColab(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.find { it.id == idSecondary }?.detail
            }
        }

    override suspend fun getDetailVehicle(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.vehicle?.detail
            }
        }

    override suspend fun getDetailDriver(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.driver?.detail
            }
        }

    override suspend fun getDetailPassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.detail
            }
        }

    override suspend fun getDetailInvolvedExternal(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.find { it.id == idMain }?.detail
            }
        }

    override suspend fun getDetailWitnessExternal(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessExternalList.find { it.id == idMain }?.detail
            }
        }

    override suspend fun getDetailInvolvedColab(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedColabList.find { it.id == idMain }?.detail
            }
        }

    override suspend fun getDetailWitnessColab(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessColabList.find { it.id == idMain }?.detail
            }
        }

    override suspend fun getRegColab(idMain: Int): Result<Long> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.reg.required("reg")
            }
        }

    override suspend fun getRegPassengerColab(
        idMain: Int,
        idSecondary: Int
    ): Result<Long> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.find { it.id == idSecondary }?.reg.required("reg")
            }
        }

    override suspend fun getRegColabInvolved(idMain: Int): Result<Long> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedColabList.find { it.id == idMain }?.reg.required("reg")
            }
        }

    override suspend fun getRegColabWitness(idMain: Int): Result<Long> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessColabList.find { it.id == idMain }?.reg.required("reg")
            }
        }

    override suspend fun getStateColab(idMain: Int): Result<State> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.state.required("state")
            }
        }

    override suspend fun getStatePassengerColab(
        idMain: Int,
        idSecondary: Int
    ): Result<State> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.find { it.id == idSecondary }?.state.required("state")
            }
        }

    override suspend fun getStatePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<State> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.state.required("state")
            }
        }

    override suspend fun getStateInvolvedExternal(idMain: Int): Result<State> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.find { it.id == idMain }?.state.required("state")
            }
        }

    override suspend fun getStateInvolvedColab(idMain: Int): Result<State> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedColabList.find { it.id == idMain }?.state.required("state")
            }
        }

    override suspend fun getStateDriver(idMain: Int): Result<State> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.driver?.state.required("state")
            }
        }

    override suspend fun getAddressPassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.address
            }
        }

    override suspend fun getAddressDriver(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.driver?.address
            }
        }

    override suspend fun getAddressInvolved(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.find { it.id == idMain }?.address
            }
        }

    override suspend fun getBrand(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.vehicle?.brand
            }
        }

    override suspend fun getPlate(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.vehicle?.plate
            }
        }

    override suspend fun getDocumentDriver(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.driver?.document
            }
        }

    override suspend fun getDocumentPassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.document
            }
        }

    override suspend fun getNameDriver(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.driver?.name
            }
        }

    override suspend fun getNamePassengerExternal(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.name
            }
        }

    override suspend fun listEquipSecondary(idMain: Int): Result<List<EquipCard>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.equipSecList?.map { it.sharedPreferencesModelToEntity() } ?: emptyList()
            }
        }

    override suspend fun listPassengerColab(idMain: Int): Result<List<ColabCard>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.map { it.sharedPreferencesModelToEntity() } ?: emptyList()
            }
        }

    override suspend fun listPassengerExternal(idMain: Int): Result<List<PeopleExternal>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.map { it.sharedPreferencesModelToEntity() } ?: emptyList()
            }
        }

    override suspend fun listInvolvedExternal(): Result<List<PeopleExternal>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.map { it.sharedPreferencesModelToEntity() }
            }
        }

    override suspend fun listWitnessExternal(): Result<List<PeopleExternal>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessExternalList.map { it.sharedPreferencesModelToEntity() }
            }
        }

    override suspend fun listInvolvedColab(): Result<List<ColabCard>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedColabList.map { it.sharedPreferencesModelToEntity() }
            }
        }

    override suspend fun listWitnessColab(): Result<List<ColabCard>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessColabList.map { it.sharedPreferencesModelToEntity() }
            }
        }

    override suspend fun getDocumentInvolved(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.find { it.id == idMain }?.document
            }
        }

    override suspend fun getNameInvolved(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.find { it.id == idMain }?.name
            }
        }

    override suspend fun getNameWitness(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessExternalList.find { it.id == idMain }?.name
            }
        }

    override suspend fun getPhoneDriver(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.driver?.phone
            }
        }

    override suspend fun getPhoneInvolved(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                involvedExternalList.find { it.id == idMain }?.phone
            }
        }

    override suspend fun getPhoneWitness(idMain: Int): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                witnessExternalList.find { it.id == idMain }?.phone
            }
        }

    override suspend fun getPhonePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): Result<String?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.phone
            }
        }

    override suspend fun listVehicleOwn(): Result<List<VehicleOwn>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.map { it.sharedPreferencesModelToEntity() }
            }
        }

    override suspend fun listVehicleExternal(): Result<List<VehicleExternal>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleExternalList.map { it.sharedPreferencesModelToEntity() }
            }
        }

    override suspend fun getResultBreathalyzer(idMain: Int): Result<Boolean?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.flagResultBreathalyzer
            }
        }

    override suspend fun getRealizedBreathalyzer(idMain: Int): Result<Boolean?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.flagRealizedBreathalyzer
            }
        }

    override suspend fun getCountBreathalyzer(idMain: Int): Result<Double?> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.countBreathalyzer
            }
        }

    override suspend fun getBreathalyzer(idMain: Int): Result<Triple<Boolean?, Boolean?, Double?>> =
        result(getClassAndMethod()) {
            datasource.get().readModel {
                val colab = vehicleOwnList.find { it.id == idMain }?.colab
                Triple(colab?.flagResultBreathalyzer, colab?.flagRealizedBreathalyzer, colab?.countBreathalyzer)
            }
        }
}
