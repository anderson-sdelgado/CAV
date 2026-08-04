package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.repositories.variable.InsertCardRepository
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.ColabSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.EquipSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.InvolvedSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.VehicleSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleExternalSharedPreferencesModel
import br.com.usinasantafe.cav.infra.models.sharedpreferences.VehicleOwnSharedPreferencesModel
import br.com.usinasantafe.cav.lib.State
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

class IInsertCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val equipSharedPreferencesDatasource: EquipSharedPreferencesDatasource,
    private val colabSharedPreferencesDatasource: ColabSharedPreferencesDatasource,
    private val vehicleSharedPreferencesDatasource: VehicleSharedPreferencesDatasource,
    private val involvedSharedPreferencesDatasource: InvolvedSharedPreferencesDatasource
): InsertCardRepository {

    override suspend fun setIdEquip(idEquip: Int): EmptyResult =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.clean().getOrThrow()
            equipSharedPreferencesDatasource.setIdEquip(idEquip).getOrThrow()
        }

    override suspend fun setDetailEquip(text: String): EmptyResult =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.setDetail(text).getOrThrow()
        }

    override suspend fun setDetailEquipSec(text: String, idMain: Int): Result<Int> =
        call(getClassAndMethod()) {
            equipSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val equipCard = equipSharedPreferencesDatasource.get().getOrThrow()
            equipSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addEquipSec(equipCard, idMain).getOrThrow()
        }

    override suspend fun setDetailDriver(text: String): Result<Int> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val vehicle = vehicleSharedPreferencesDatasource.get().getOrThrow()
            val involved = involvedSharedPreferencesDatasource.get().getOrThrow()
            val model = VehicleExternalSharedPreferencesModel(vehicle = vehicle, driver = involved)
            vehicleSharedPreferencesDatasource.clean().getOrThrow()
            involvedSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addVehicleExternal(model).getOrThrow()
        }

    override suspend fun setDetailColab(text: String): Result<Int> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val equipCard = equipSharedPreferencesDatasource.get().getOrThrow()
            val colabCard = colabSharedPreferencesDatasource.get().getOrThrow()
            val model = VehicleOwnSharedPreferencesModel(equip = equipCard, colab = colabCard)
            equipSharedPreferencesDatasource.clean().getOrThrow()
            colabSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addVehicleOwn(model).getOrThrow()
        }

    override suspend fun setDetailPassengerColab(text: String, idMain: Int): Result<Int> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val model = colabSharedPreferencesDatasource.get().getOrThrow()
            colabSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addPassengerColab(model, idMain).getOrThrow()
        }

    override suspend fun setDetailVehicle(text: String): EmptyResult =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.setDetail(text).getOrThrow()
        }

    override suspend fun setDetailInvolvedExternal(text: String): Result<Int> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val model = involvedSharedPreferencesDatasource.get().getOrThrow()
            involvedSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addInvolvedExternal(model).getOrThrow()
        }

    override suspend fun setDetailWitnessExternal(text: String): Result<Int> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val model = involvedSharedPreferencesDatasource.get().getOrThrow()
            involvedSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addWitnessExternal(model).getOrThrow()
        }

    override suspend fun setDetailPassengerExternal(text: String, idMain: Int): Result<Int> =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val model = involvedSharedPreferencesDatasource.get().getOrThrow()
            involvedSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addPassengerExternal(model, idMain).getOrThrow()
        }

    override suspend fun setDetailInvolvedColab(text: String): Result<Int> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val model = colabSharedPreferencesDatasource.get().getOrThrow()
            colabSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addInvolvedColab(model).getOrThrow()
        }

    override suspend fun setDetailWitnessColab(text: String): Result<Int> =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setDetail(text).getOrThrow()
            val model = colabSharedPreferencesDatasource.get().getOrThrow()
            colabSharedPreferencesDatasource.clean().getOrThrow()
            cardSharedPreferencesDatasource.addWitnessColab(model).getOrThrow()
        }

    override suspend fun setRegColab(regColab: Long): EmptyResult =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.clean().getOrThrow()
            colabSharedPreferencesDatasource.setRegColab(regColab).getOrThrow()
        }

    override suspend fun setStateColab(state: State): EmptyResult =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setState(state).getOrThrow()
        }

    override suspend fun setBrand(text: String): EmptyResult =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.setBrand(text).getOrThrow()
        }

    override suspend fun setPlate(text: String): EmptyResult =
        call(getClassAndMethod()) {
            vehicleSharedPreferencesDatasource.clean().getOrThrow()
            vehicleSharedPreferencesDatasource.setPlate(text).getOrThrow()
        }

    override suspend fun setDocument(text: String): EmptyResult =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.clean().getOrThrow()
            involvedSharedPreferencesDatasource.setDocument(text).getOrThrow()
        }

    override suspend fun setStateExternal(state: State): EmptyResult =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setState(state).getOrThrow()
        }

    override suspend fun setName(text: String): EmptyResult =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setName(text).getOrThrow()
        }

    override suspend fun setPhone(text: String): EmptyResult =
        call(getClassAndMethod()) {
            involvedSharedPreferencesDatasource.setPhone(text).getOrThrow()
        }

    override suspend fun setDataInitialBreathalyzer(flagRealized: Boolean?, flagResult: Boolean?): EmptyResult =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setDataInitialBreathalyzer(flagRealized, flagResult).getOrThrow()
        }

    override suspend fun setCountBreathalyzer(count: Double?): EmptyResult =
        call(getClassAndMethod()) {
            colabSharedPreferencesDatasource.setCountBreathalyzer(count).getOrThrow()
        }

}
