package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.UpdateCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject
import javax.inject.Provider

class IUpdateCardSharedPreferencesDatasource @Inject constructor(
    private val datasource: Provider<CardSharedPreferencesDatasource>
): UpdateCardSharedPreferencesDatasource {

    override suspend fun updateIdEquip(
        idEquip: Int,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.equip?.idEquip = idEquip
            }
        }

    override suspend fun updateIdEquipSecondary(
        idEquip: Int,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.equipSecList?.find { it.id == idSecondary }?.idEquip = idEquip
            }
        }

    override suspend fun updateDetailEquip(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.equip?.detail = text
            }
        }

    override suspend fun updateDetailEquipSecondary(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.equipSecList?.find { it.id == idSecondary }?.detail = text
            }
        }

    override suspend fun updateDetailColab(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.detail = text
            }
        }

    override suspend fun updateDetailDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.driver?.detail = text
            }
        }

    override suspend fun updateDetailPassengerColab(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.find { it.id == idSecondary }?.detail = text
            }
        }

    override suspend fun updateDetailVehicle(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.vehicle?.detail = text
            }
        }

    override suspend fun updateDetailPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.detail = text
            }
        }

    override suspend fun updateDetailInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                involvedList.find { it.id == idMain }?.detail = text
            }
        }

    override suspend fun updateDetailWitness(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                witnessList.find { it.id == idMain }?.detail = text
            }
        }

    override suspend fun updateRegColab(
        regColab: Long,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.reg = regColab
            }
        }

    override suspend fun updateRegPassengerColab(
        regColab: Long,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.find { it.id == idSecondary }?.reg = regColab
            }
        }

    override suspend fun updateStateColab(
        state: State,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.colab?.state = state
            }
        }

    override suspend fun updateStatePassengerColab(
        state: State,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.passengerColabList?.find { it.id == idSecondary }?.state = state
            }
        }

    override suspend fun updateStateDriver(
        state: State,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.driver?.state = state
            }
        }

    override suspend fun updateStatePassengerInvolved(
        state: State,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.state = state
            }
        }

    override suspend fun updateStateInvolved(
        state: State,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                involvedList.find { it.id == idMain }?.state = state
            }
        }

    override suspend fun updateStateWitness(
        state: State,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                witnessList.find { it.id == idMain }?.state = state
            }
        }

    override suspend fun updateAddressPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.address = text
            }
        }

    override suspend fun updateAddressInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                involvedList.find { it.id == idMain }?.address = text
            }
        }

    override suspend fun updateAddressDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.driver?.address = text
            }
        }

    override suspend fun updateBrand(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.vehicle?.brand = text
            }
        }

    override suspend fun updatePlate(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.vehicle?.plate = text
            }
        }

    override suspend fun updateDocumentDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.driver?.document = text
            }
        }

    override suspend fun updateDocumentPassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.document = text
            }
        }

    override suspend fun updateDocumentInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                involvedList.find { it.id == idMain }?.document = text
            }
        }

    override suspend fun updateNameDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.driver?.name = text
            }
        }

    override suspend fun updateNamePassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.name = text
            }
        }

    override suspend fun updateNameInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                involvedList.find { it.id == idMain }?.name = text
            }
        }

    override suspend fun updateNameWitness(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                witnessList.find { it.id == idMain }?.name = text
            }
        }

    override suspend fun updatePhoneDriver(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.driver?.phone = text
            }
        }

    override suspend fun updatePhoneInvolved(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                involvedList.find { it.id == idMain }?.phone = text
            }
        }

    override suspend fun updatePhoneWitness(
        text: String,
        idMain: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                witnessList.find { it.id == idMain }?.phone = text
            }
        }

    override suspend fun updatePhonePassengerInvolved(
        text: String,
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.passengerInvolvedList?.find { it.id == idSecondary }?.phone = text
            }
        }

}
