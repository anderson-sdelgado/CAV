package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.*
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.lib.Option
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface SetDetail {
    suspend operator fun invoke(
        text: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Int?>
}

class ISetDetail @Inject constructor(
    private val cardRepository: CardRepository
): SetDetail {

    override suspend fun invoke(
        text: String,
        option: Option,
        flowNote: FlowNote,
        idMain: Int,
        idSecondary: Int
    ): Result<Int?> =
        call(getClassAndMethod()) {
            with(cardRepository) {
                val result = when (option) {
                    Option.INSERT -> when (flowNote) {
                        FlowNote.EQUIP -> setDetailEquip(text)
                        FlowNote.EQUIP_SEC -> setDetailEquipSec(text, idMain)
                        FlowNote.COLAB -> setDetailColab(text)
                        FlowNote.PASSENGER_COLAB -> setDetailPassengerColab(text, idMain)
                        FlowNote.INVOLVED_COLAB -> setDetailInvolvedColab(text)
                        FlowNote.WITNESS_COLAB -> setDetailWitnessColab(text)
                        FlowNote.VEHICLE -> setDetailVehicle(text)
                        FlowNote.DRIVER -> setDetailDriver(text)
                        FlowNote.PASSENGER_EXTERNAL -> setDetailPassengerExternal(text, idMain)
                        FlowNote.INVOLVED_EXTERNAL -> setDetailInvolvedExternal(text)
                        FlowNote.WITNESS_EXTERNAL -> setDetailWitnessExternal(text)
                    }
                    Option.EDIT -> when (flowNote) {
                        FlowNote.EQUIP -> updateDetailEquip(text, idMain)
                        FlowNote.EQUIP_SEC -> updateDetailEquipSecondary(text, idMain, idSecondary)
                        FlowNote.COLAB -> updateDetailColab(text, idMain)
                        FlowNote.PASSENGER_COLAB -> updateDetailPassengerColab(text, idMain, idSecondary)
                        FlowNote.INVOLVED_COLAB -> updateDetailInvolvedColab(text, idMain)
                        FlowNote.WITNESS_COLAB -> updateDetailWitnessColab(text, idMain)
                        FlowNote.VEHICLE -> updateDetailVehicle(text, idMain)
                        FlowNote.DRIVER -> updateDetailDriver(text, idMain)
                        FlowNote.PASSENGER_EXTERNAL -> updateDetailPassengerExternal(text, idMain, idSecondary)
                        FlowNote.INVOLVED_EXTERNAL -> updateDetailInvolvedExternal(text, idMain)
                        FlowNote.WITNESS_EXTERNAL -> updateDetailWitnessExternal(text, idMain)
                    }
                }
                result.getOrThrow().let { it as? Int }
            }
        }

}
