package br.com.usinasantafe.cav.infra.repositories.variable.card

import br.com.usinasantafe.cav.domain.repositories.variable.SendCardRepository
import br.com.usinasantafe.cav.infra.datasource.retrofit.variable.CardRetrofitDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.CardRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerColabRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.EquipSecRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.InvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.PassengerInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleInvolvedRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.VehicleOwnRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.room.variable.WitnessRoomDatasource
import br.com.usinasantafe.cav.infra.datasource.sharedpreferences.CardSharedPreferencesDatasource
import br.com.usinasantafe.cav.infra.models.retrofit.variable.roomModelToRetrofitModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToPassengerInvolvedRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToRoomModel
import br.com.usinasantafe.cav.infra.models.room.variable.sharedPreferencesModelToWitnessRoomModel
import br.com.usinasantafe.cav.utils.EmptyResult
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import br.com.usinasantafe.cav.utils.required
import javax.inject.Inject

class ISendCardRepository @Inject constructor(
    private val cardSharedPreferencesDatasource: CardSharedPreferencesDatasource,
    private val involvedRoomDatasource: InvolvedRoomDatasource,
    private val vehicleInvolvedRoomDatasource: VehicleInvolvedRoomDatasource,
    private val passengerColabRoomDatasource: PassengerColabRoomDatasource,
    private val passengerInvolvedRoomDatasource: PassengerInvolvedRoomDatasource,
    private val equipSecRoomDatasource: EquipSecRoomDatasource,
    private val vehicleOwnRoomDatasource: VehicleOwnRoomDatasource,
    private val witnessRoomDatasource: WitnessRoomDatasource,
    private val cardRoomDatasource: CardRoomDatasource,
    private val cardRetrofitDatasource: CardRetrofitDatasource
): SendCardRepository {

    override suspend fun save(): EmptyResult =
        call(getClassAndMethod()) {
            val cardSharedPreferencesModel = cardSharedPreferencesDatasource.get().getOrThrow()
            val model = cardSharedPreferencesModel.sharedPreferencesModelToRoomModel()
            val idCard = cardRoomDatasource.add(model).getOrThrow()

            cardSharedPreferencesModel.vehicleOwnList.forEach { vehicleOwn ->
                val model = vehicleOwn.sharedPreferencesModelToInvolvedRoomModel(idCard)
                val idVehicle = vehicleOwnRoomDatasource.add(model).getOrThrow()
                vehicleOwn.passengerColabList.forEach {
                    val model = it.sharedPreferencesModelToInvolvedRoomModel(idVehicle)
                    passengerColabRoomDatasource.add(model).getOrThrow()
                }
                vehicleOwn.equipSecList.forEach {
                    val model = it.sharedPreferencesModelToInvolvedRoomModel(idVehicle)
                    equipSecRoomDatasource.add(model).getOrThrow()
                }
            }

            cardSharedPreferencesModel.vehicleInvolvedList.forEach { vehicleInvolved ->
                val model = vehicleInvolved.sharedPreferencesModelToInvolvedRoomModel(idCard)
                val idVehicle = vehicleInvolvedRoomDatasource.add(model).getOrThrow()
                vehicleInvolved.passengerInvolvedList.forEach {
                    val model = it.sharedPreferencesModelToPassengerInvolvedRoomModel(idVehicle)
                    passengerInvolvedRoomDatasource.add(model).getOrThrow()
                }
            }

            cardSharedPreferencesModel.involvedList.forEach { involved ->
                val model = involved.sharedPreferencesModelToInvolvedRoomModel(idCard)
                involvedRoomDatasource.add(model).getOrThrow()
            }

            cardSharedPreferencesModel.witnessList.forEach { witness ->
                val model = witness.sharedPreferencesModelToWitnessRoomModel(idCard)
                witnessRoomDatasource.add(model).getOrThrow()
            }

        }

    override suspend fun send(token: String): EmptyResult =
        call(getClassAndMethod()) {
            val cardRoomModel = cardRoomDatasource.getSend().getOrThrow()
            val idCard = cardRoomModel::id.required()
            val vehicleOwnRoomModelList = vehicleOwnRoomDatasource.listByIdCard(idCard).getOrThrow()
            val vehicleInvolvedRoomModelList = vehicleInvolvedRoomDatasource.listByIdCard(idCard).getOrThrow()
            val involvedRoomModelList = involvedRoomDatasource.listByIdCard(idCard).getOrThrow()
            val witnessRoomModelList = witnessRoomDatasource.listByIdCard(idCard).getOrThrow()
            val idVehicleOwnList = vehicleOwnRoomModelList.map { it::id.required() }
            val idVehicleInvolvedList = vehicleInvolvedRoomModelList.map { it::id.required() }
            val passengerColabRoomModelList = passengerColabRoomDatasource.listByIdVehicleList(idVehicleOwnList).getOrThrow()
            val passengerInvolvedRoomModelList = passengerInvolvedRoomDatasource.listByIdVehicleList(idVehicleInvolvedList).getOrThrow()
            val equipSecRoomModelList = equipSecRoomDatasource.listByIdVehicleList(idVehicleOwnList).getOrThrow()

            val equipSecGrouped = equipSecRoomModelList.groupBy { it.idVehicle }
            val passengerColabGrouped = passengerColabRoomModelList.groupBy { it.idVehicle }
            val passengerInvolvedGrouped = passengerInvolvedRoomModelList.groupBy { it.idVehicle }

            val vehicleOwnRetrofitList = vehicleOwnRoomModelList.map { roomModel ->
                roomModel.roomModelToRetrofitModel().copy(
                    passengerColabList = passengerColabGrouped[roomModel.id]?.map { it.roomModelToRetrofitModel() } ?: emptyList(),
                    equipSecList = equipSecGrouped[roomModel.id]?.map { it.roomModelToRetrofitModel() } ?: emptyList()
                )
            }

            val vehicleInvolvedRetrofitList = vehicleInvolvedRoomModelList.map { roomModel ->
                roomModel.roomModelToRetrofitModel().copy(
                    passengerInvolvedList = passengerInvolvedGrouped[roomModel.id]?.map { it.roomModelToRetrofitModel() } ?: emptyList()
                )
            }
            val involvedRetrofitList = involvedRoomModelList.map { it.roomModelToRetrofitModel() }
            val witnessRetrofitList = witnessRoomModelList.map { it.roomModelToRetrofitModel() }

            val modelRetrofit = cardRoomModel.roomModelToRetrofitModel(
                vehicleOwnList = vehicleOwnRetrofitList,
                vehicleInvolvedList = vehicleInvolvedRetrofitList,
                involvedList = involvedRetrofitList,
                witnessList = witnessRetrofitList,
            )
            cardRetrofitDatasource.send(token,modelRetrofit).getOrThrow()
        }

    override suspend fun hasSend(): Result<Boolean> =
        call(getClassAndMethod()) {
            cardRoomDatasource.hasSend().getOrThrow()
        }

}