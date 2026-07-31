package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDetail {
    suspend operator fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String>
}

class IGetDetail @Inject constructor(
    private val cardRepository: CardRepository
): GetDetail {

    override suspend fun invoke(
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                when (option) {
                    Option.INSERT -> {
                        when (flowNote) {
                            FlowNote.EQUIP,
                            FlowNote.EQUIP_SEC -> getDetailEquip()
                            FlowNote.INVOLVED_COLAB,
                            FlowNote.WITNESS_COLAB,
                            FlowNote.COLAB,
                            FlowNote.PASSENGER_COLAB -> getDetailColab()
                            FlowNote.VEHICLE -> getDetailVehicle()
                            FlowNote.DRIVER,
                            FlowNote.PASSENGER_EXTERNAL,
                            FlowNote.INVOLVED_EXTERNAL,
                            FlowNote.WITNESS_EXTERNAL -> getDetailInvolvedExternal()
                        }
                    }
                    Option.EDIT -> {
                        when (flowNote) {
                            FlowNote.EQUIP -> getDetailEquip(idMain)
                            FlowNote.EQUIP_SEC -> getDetailEquipSecondary(idMain, idSecondary)
                            FlowNote.COLAB -> getDetailColab(idMain)
                            FlowNote.PASSENGER_COLAB -> getDetailPassengerColab(idMain, idSecondary)
                            FlowNote.INVOLVED_COLAB -> getDetailInvolvedColab(idMain)
                            FlowNote.WITNESS_COLAB -> getDetailWitnessColab(idMain)
                            FlowNote.VEHICLE -> getDetailVehicle(idMain)
                            FlowNote.DRIVER -> getDetailDriver(idMain)
                            FlowNote.PASSENGER_EXTERNAL -> getDetailPassengerExternal(idMain, idSecondary)
                            FlowNote.INVOLVED_EXTERNAL -> getDetailInvolvedExternal(idMain)
                            FlowNote.WITNESS_EXTERNAL -> getDetailWitnessExternal(idMain)
                        }
                    }
                }.getOrThrow() ?: ""
            }
        }

}
