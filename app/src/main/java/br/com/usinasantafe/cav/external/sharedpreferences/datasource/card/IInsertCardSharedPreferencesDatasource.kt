package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.util.Log
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InsertCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.result
import javax.inject.Inject
import javax.inject.Provider

class IInsertCardSharedPreferencesDatasource @Inject constructor(
    private val datasource: Provider<CardSharedPreferencesDatasource>
): InsertCardSharedPreferencesDatasource {

    override suspend fun addVehicleOwn(entity: VehicleOwnSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = vehicleOwnList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.vehicleOwnList = list
            }
            id
        }

    override suspend fun addEquipSec(
        equipSharedPreferencesModel: EquipSharedPreferencesModel,
        idMain: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.let { vehicleOwn ->
                    val list = vehicleOwn.equipSecList.toMutableList()
                    id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                    equipSharedPreferencesModel.id = id
                    list.add(equipSharedPreferencesModel)
                    vehicleOwn.equipSecList = list
                }
            }
            id
        }

    override suspend fun addPassengerColab(
        colabSharedPreferencesModel: ColabSharedPreferencesModel,
        idMain: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.let { vehicleOwn ->
                    val list = vehicleOwn.passengerColabList.toMutableList()
                    id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                    colabSharedPreferencesModel.id = id
                    list.add(colabSharedPreferencesModel)
                    vehicleOwn.passengerColabList = list
                }
            }
            id
        }

    override suspend fun addVehicleInvolved(entity: VehicleInvolvedSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = vehicleInvolvedList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.vehicleInvolvedList = list
            }
            Log.d("TestDebug", "Id Interno: $id")
            id
        }

    override suspend fun addInvolved(entity: InvolvedSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = involvedList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.involvedList = list
            }
            id
        }

    override suspend fun addWitness(entity: InvolvedSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = witnessList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.witnessList = list
            }
            id
        }

    override suspend fun addPassengerInvolved(
        entity: InvolvedSharedPreferencesModel,
        idMain: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                vehicleInvolvedList.find { it.id == idMain }?.let { vehicleInvolved ->
                    val list = vehicleInvolved.passengerInvolvedList.toMutableList()
                    id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                    entity.id = id
                    list.add(entity)
                    vehicleInvolved.passengerInvolvedList = list
                }
            }
            id
        }
}
