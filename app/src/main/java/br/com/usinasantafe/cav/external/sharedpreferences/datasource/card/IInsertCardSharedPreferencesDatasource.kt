package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import android.util.Log
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InsertCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipCardSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.PeopleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
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
        equipCardSharedPreferencesModel: EquipCardSharedPreferencesModel,
        idMain: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.let { vehicleOwn ->
                    val list = vehicleOwn.equipSecList.toMutableList()
                    id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                    equipCardSharedPreferencesModel.id = id
                    list.add(equipCardSharedPreferencesModel)
                    vehicleOwn.equipSecList = list
                }
            }
            id
        }

    override suspend fun addPassengerColab(
        colabCardSharedPreferencesModel: ColabCardSharedPreferencesModel,
        idMain: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                vehicleOwnList.find { it.id == idMain }?.let { vehicleOwn ->
                    val list = vehicleOwn.passengerColabList.toMutableList()
                    id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                    colabCardSharedPreferencesModel.id = id
                    list.add(colabCardSharedPreferencesModel)
                    vehicleOwn.passengerColabList = list
                }
            }
            id
        }

    override suspend fun addVehicleExternal(entity: VehicleExternalSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = vehicleExternalList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.vehicleExternalList = list
            }
            id
        }

    override suspend fun addInvolvedExternal(entity: PeopleExternalSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = involvedExternalList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.involvedExternalList = list
            }
            id
        }

    override suspend fun addWitnessExternal(entity: PeopleExternalSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = witnessExternalList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.witnessExternalList = list
            }
            id
        }

    override suspend fun addPassengerExternal(
        entity: PeopleExternalSharedPreferencesModel,
        idMain: Int
    ): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                vehicleExternalList.find { it.id == idMain }?.let { vehicleInvolved ->
                    val list = vehicleInvolved.passengerInvolvedList.toMutableList()
                    id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                    entity.id = id
                    list.add(entity)
                    vehicleInvolved.passengerInvolvedList = list
                }
            }
            id
        }

    override suspend fun addInvolvedColab(entity: ColabCardSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = involvedColabList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.involvedColabList = list
            }
            id
        }

    override suspend fun addWitnessColab(entity: ColabCardSharedPreferencesModel): Result<Int> =
        result(getClassAndMethod()) {
            var id = 0
            datasource.get().updateModel {
                val list = witnessColabList.toMutableList()
                id = (list.mapNotNull { it.id }.maxOrNull() ?: 0) + 1
                entity.id = id
                list.add(entity)
                this.witnessColabList = list
            }
            id
        }

}
