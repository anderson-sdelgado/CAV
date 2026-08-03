package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.repositories.variable.SendCardRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.CardRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.CardRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerColabRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.EquipSecRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedColabRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedExternalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerExternalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleExternalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleOwnRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessColabRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessExternalRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.roomModelToRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToInvolvedColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToInvolvedExternalRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToPassengerInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToWitnessColabRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToWitnessExternalRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import br.com.usinasantafe.cav.utils.tryCatch
import javax.inject.Inject

class ISendCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val vehicleOwnRoomDatasource: VehicleOwnRoomDatasource,
    private val vehicleExternalRoomDatasource: VehicleExternalRoomDatasource,
    private val passengerColabRoomDatasource: PassengerColabRoomDatasource,
    private val passengerExternalRoomDatasource: PassengerExternalRoomDatasource,
    private val equipSecRoomDatasource: EquipSecRoomDatasource,
    private val involvedExternalRoomDatasource: InvolvedExternalRoomDatasource,
    private val witnessExternalRoomDatasource: WitnessExternalRoomDatasource,
    private val cardRoomDatasource: CardRoomDatasource,
    private val cardRetrofitDatasource: CardRetrofitDatasource,
    private val involvedColabRoomDatasource: InvolvedColabRoomDatasource,
    private val witnessColabRoomDatasource: WitnessColabRoomDatasource
): SendCardRepository {

    override suspend fun save(): EmptyResult =
        call(getClassAndMethod()) {

            val cardSharedPreferencesModel = cardSharedPreferencesDatasource.get().getOrThrow()
            val model = cardSharedPreferencesModel.sharedPreferencesModelToRoomModel()
            val idCard = cardRoomDatasource.add(model).getOrThrow()

            cardSharedPreferencesModel.vehicleOwnList.forEach { vehicleOwn ->
                val model = vehicleOwn.sharedPreferencesModelToInvolvedExternalRoomModel(idCard)
                val idVehicle = vehicleOwnRoomDatasource.add(model).getOrThrow()
                vehicleOwn.passengerColabList.forEach {
                    val model = it.sharedPreferencesModelToInvolvedExternalRoomModel(idVehicle)
                    passengerColabRoomDatasource.add(model).getOrThrow()
                }
                vehicleOwn.equipSecList.forEach {
                    val model = it.sharedPreferencesModelToInvolvedExternalRoomModel(idVehicle)
                    equipSecRoomDatasource.add(model).getOrThrow()
                }
            }

            cardSharedPreferencesModel.vehicleExternalList.forEach { vehicleInvolved ->
                val model = vehicleInvolved.sharedPreferencesModelToInvolvedExternalRoomModel(idCard)
                val idVehicle = vehicleExternalRoomDatasource.add(model).getOrThrow()
                vehicleInvolved.passengerInvolvedList.forEach {
                    val model = it.sharedPreferencesModelToPassengerInvolvedRoomModel(idVehicle)
                    passengerExternalRoomDatasource.add(model).getOrThrow()
                }
            }

            cardSharedPreferencesModel.involvedExternalList.forEach { involved ->
                val model = involved.sharedPreferencesModelToInvolvedExternalRoomModel(idCard)
                involvedExternalRoomDatasource.add(model).getOrThrow()
            }

            cardSharedPreferencesModel.witnessExternalList.forEach { witness ->
                val model = witness.sharedPreferencesModelToWitnessExternalRoomModel(idCard)
                witnessExternalRoomDatasource.add(model).getOrThrow()
            }

            cardSharedPreferencesModel.involvedColabList.forEach { involved ->
                val model = involved.sharedPreferencesModelToInvolvedColabRoomModel(idCard)
                involvedColabRoomDatasource.add(model).getOrThrow()
            }

            cardSharedPreferencesModel.witnessColabList.forEach { witness ->
                val model = witness.sharedPreferencesModelToWitnessColabRoomModel(idCard)
                witnessColabRoomDatasource.add(model).getOrThrow()
            }

        }

    override suspend fun send(token: String): EmptyResult =
        call(getClassAndMethod()) {
            val cardRoomModel = cardRoomDatasource.getSend().getOrThrow()
            val idCard = cardRoomModel::id.required()
            val vehicleOwnRoomModelList = vehicleOwnRoomDatasource.listByIdCard(idCard).getOrThrow()
            val vehicleInvolvedRoomModelList = vehicleExternalRoomDatasource.listByIdCard(idCard).getOrThrow()

            val involvedExternalRoomModelList = involvedExternalRoomDatasource.listByIdCard(idCard).getOrThrow()
            val witnessExternalRoomModelList = witnessExternalRoomDatasource.listByIdCard(idCard).getOrThrow()

            val involvedColabRoomModelList = involvedColabRoomDatasource.listByIdCard(idCard).getOrThrow()
            val witnessColabRoomModelLis = witnessColabRoomDatasource.listByIdCard(idCard).getOrThrow()

            val idVehicleOwnList = vehicleOwnRoomModelList.map { it::id.required() }
            val idVehicleInvolvedList = vehicleInvolvedRoomModelList.map { it::id.required() }
            val passengerColabRoomModelList = passengerColabRoomDatasource.listByIdVehicleList(idVehicleOwnList).getOrThrow()
            val passengerExternalRoomModelList = passengerExternalRoomDatasource.listByIdVehicleList(idVehicleInvolvedList).getOrThrow()
            val equipSecRoomModelList = equipSecRoomDatasource.listByIdVehicleList(idVehicleOwnList).getOrThrow()

            val equipSecGrouped = equipSecRoomModelList.groupBy { it.idVehicle }
            val passengerColabGrouped = passengerColabRoomModelList.groupBy { it.idVehicle }
            val passengerInvolvedGrouped = passengerExternalRoomModelList.groupBy { it.idVehicle }

            val vehicleOwnRetrofitList = vehicleOwnRoomModelList.map { roomModel ->
                roomModel.roomModelToRetrofitModel().copy(
                    passengerColabList = passengerColabGrouped[roomModel.id]?.map { it.roomModelToRetrofitModel() } ?: emptyList(),
                    equipSecList = equipSecGrouped[roomModel.id]?.map { it.roomModelToRetrofitModel() } ?: emptyList()
                )
            }

            val vehicleInvolvedRetrofitList = vehicleInvolvedRoomModelList.map { roomModel ->
                roomModel.roomModelToRetrofitModel().copy(
                    passengerExternalList = passengerInvolvedGrouped[roomModel.id]?.map { it.roomModelToRetrofitModel() } ?: emptyList()
                )
            }
            val involvedExternalRetrofitList = involvedExternalRoomModelList.map { it.roomModelToRetrofitModel() }
            val witnessExternalRetrofitList = witnessExternalRoomModelList.map { it.roomModelToRetrofitModel() }

            val involvedColabRetrofitList = involvedColabRoomModelList.map { it.roomModelToRetrofitModel() }
            val witnessColabRetrofitList = witnessColabRoomModelLis.map { it.roomModelToRetrofitModel() }

            val modelRetrofit = cardRoomModel.roomModelToRetrofitModel(
                vehicleOwnList = vehicleOwnRetrofitList,
                vehicleInvolvedList = vehicleInvolvedRetrofitList,
                involvedExternalList = involvedExternalRetrofitList,
                witnessExternalList = witnessExternalRetrofitList,
                involvedColabList = involvedColabRetrofitList,
                witnessColabList = witnessColabRetrofitList
            )
            val model = cardRetrofitDatasource.send(token, modelRetrofit).getOrThrow()
            cardRoomDatasource.update(idCard, model.idServ).getOrThrow()

        }

    override suspend fun hasSend(): Result<Boolean> =
        call(getClassAndMethod()) {
            cardRoomDatasource.hasSend().getOrThrow()
        }

    override suspend fun delete(): EmptyResult =
        call(getClassAndMethod()) {
            val list = cardRoomDatasource.listDelete().getOrThrow()
            list.forEach { card ->
                val idCard = card::id.required()
                val vehicleOwnRoomModelList = vehicleOwnRoomDatasource.listByIdCard(idCard).getOrThrow()
                val vehicleInvolvedRoomModelList = vehicleExternalRoomDatasource.listByIdCard(idCard).getOrThrow()
                val idVehicleOwnList = vehicleOwnRoomModelList.map { it::id.required() }
                val idVehicleInvolvedList = vehicleInvolvedRoomModelList.map { it::id.required() }
                equipSecRoomDatasource.deleteByIdVehicleList(idVehicleOwnList).getOrThrow()
                passengerColabRoomDatasource.deleteByIdVehicleList(idVehicleOwnList).getOrThrow()
                passengerExternalRoomDatasource.deleteByIdVehicleList(idVehicleInvolvedList).getOrThrow()
                involvedExternalRoomDatasource.deleteByIdCard(idCard).getOrThrow()
                witnessExternalRoomDatasource.deleteByIdCard(idCard).getOrThrow()
                involvedColabRoomDatasource.deleteByIdCard(idCard).getOrThrow()
                witnessColabRoomDatasource.deleteByIdCard(idCard).getOrThrow()
                vehicleExternalRoomDatasource.deleteByIdCard(idCard).getOrThrow()
                vehicleOwnRoomDatasource.deleteByIdCard(idCard).getOrThrow()
                card.urlPhotoList.forEach { path ->
                    tryCatch("deletePhoto") {
                        val file = java.io.File(path)
                        if (file.exists()) {
                            file.delete()
                        }
                    }
                }
                cardRoomDatasource.deleteById(idCard).getOrThrow()
            }

        }

}