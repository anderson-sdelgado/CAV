package br.com.usinasantafe.cav.external.sharedpreferences.datasource.card

import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InsertCardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.ColabSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.EquipSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.InvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleInvolvedSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import javax.inject.Inject

class IInsertCardSharedPreferencesDatasource @Inject constructor(

): InsertCardSharedPreferencesDatasource {

    override suspend fun addVehicleOwn(entity: VehicleOwnSharedPreferencesModel): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun addEquipSec(
        equipSharedPreferencesModel: EquipSharedPreferencesModel,
        idMain: Int
    ): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun addPassengerColab(
        colabSharedPreferencesModel: ColabSharedPreferencesModel,
        idMain: Int
    ): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun addVehicleInvolved(entity: VehicleInvolvedSharedPreferencesModel): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun addInvolved(entity: InvolvedSharedPreferencesModel): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun addWitness(entity: InvolvedSharedPreferencesModel): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun addPassengerInvolved(
        entity: InvolvedSharedPreferencesModel,
        idMain: Int
    ): Result<Int> {
        TODO("Not yet implemented")
    }
}