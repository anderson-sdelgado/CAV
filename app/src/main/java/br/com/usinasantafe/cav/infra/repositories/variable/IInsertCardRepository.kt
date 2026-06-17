package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.domain.repositories.variable.InsertCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.presenter.model.VehicleScreenModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IInsertCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource,
    private val colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource,
    private val vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource
): InsertCardRepository {

    override suspend fun setIdEquip(idEquip: Int): EmptyResult =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.setIdEquip(idEquip).getOrThrow()
        }

    override suspend fun setDetailEquip(text: String): EmptyResult =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.setDetail(text).getOrThrow()
        }

    override suspend fun setDetailEquipSec(text: String): EmptyResult =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.setDetail(text).getOrThrow()
        }

    override suspend fun setDetailDriver(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailColab(text: String): Result<Int> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val equipCard = equipSharedPreferencesDatasource.get().getOrThrow()
            val colabCard = colabSharedPreferencesDatasource.get().getOrThrow()
            val model = VehicleOwnSharedPreferencesModel(equip = equipCard, colab = colabCard)
            equipSharedPreferencesDatasource.clean().getOrThrow()
            colabSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.setVehicleOwn(model).getOrThrow()
        }

    override suspend fun setDetailPassengerColab(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setDetailVehicle(text: String): EmptyResult =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.setDetail(text).getOrThrow()
        }

    override suspend fun setDetailInvolved(text: String): Result<Int> =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setDetailWitness(text: String): Result<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun setRegColab(regColab: Long): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setStateColab(state: State): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setStateDriver(state: State): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setBrand(text: String): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setPlate(text: String): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setDocument(text: String): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setStateInvolved(state: State): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

    override suspend fun setName(text: String): EmptyResult =
        call(getClassAndMethod()) {
            TODO("Not yet implemented")
        }

}
