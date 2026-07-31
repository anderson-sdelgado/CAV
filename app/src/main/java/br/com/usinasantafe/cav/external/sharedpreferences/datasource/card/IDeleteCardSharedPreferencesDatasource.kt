package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.DeleteCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject
import javax.inject.Provider

class IDeleteCardSharedPreferencesDatasource @Inject constructor(
    private val datasource: Provider<CardSharedPreferencesDatasource>
): DeleteCardSharedPreferencesDatasource {

    override suspend fun deleteVehicleOwn(idMain: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = vehicleOwnList.toMutableList()
                list.removeIf { it.id == idMain }
                this.vehicleOwnList = list
            }
        }

    override suspend fun deleteEquipSecondary(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.let { vehicleOwn ->
                    val list = vehicleOwn.equipSecList.toMutableList()
                    list.removeIf { it.id == idSecondary }
                    vehicleOwn.equipSecList = list
                }
            }
        }

    override suspend fun deleteVehicleExternal(idMain: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = vehicleExternalList.toMutableList()
                list.removeIf { it.id == idMain }
                this.vehicleExternalList = list
            }
        }

    override suspend fun deleteInvolvedExternal(idMain: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = involvedExternalList.toMutableList()
                list.removeIf { it.id == idMain }
                this.involvedExternalList = list
            }
        }

    override suspend fun deleteWitnessExternal(idMain: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = witnessExternalList.toMutableList()
                list.removeIf { it.id == idMain }
                this.witnessExternalList = list
            }
        }

    override suspend fun deleteInvolvedColab(idMain: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = involvedColabList.toMutableList()
                list.removeIf { it.id == idMain }
                this.involvedColabList = list
            }
        }

    override suspend fun deleteWitnessColab(idMain: Int): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = witnessColabList.toMutableList()
                list.removeIf { it.id == idMain }
                this.witnessColabList = list
            }
        }

    override suspend fun deletePassengerColab(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.let { vehicleOwn ->
                    val list = vehicleOwn.passengerColabList.toMutableList()
                    list.removeIf { it.id == idSecondary }
                    vehicleOwn.passengerColabList = list
                }
            }
        }

    override suspend fun deletePassengerInvolved(
        idMain: Int,
        idSecondary: Int
    ): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                vehicleExternalList.find { it.id == idMain }?.let { vehicleInvolved ->
                    val list = vehicleInvolved.passengerInvolvedList.toMutableList()
                    list.removeIf { it.id == idSecondary }
                    vehicleInvolved.passengerInvolvedList = list
                }
            }
        }

    override suspend fun deletePhoto(url: String): EmptyResult =
        result(getClassAndMethod()) {
            datasource.get().updateModel {
                val list = urlPhotoList.toMutableList()
                list.remove(url)
                this.urlPhotoList = list
            }
        }
}