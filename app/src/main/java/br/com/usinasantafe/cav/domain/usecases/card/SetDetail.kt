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
                        FlowNote.EQUIP,
                        FlowNote.EQUIP_SEC -> setDetailEquip(text)
                        FlowNote.COLAB,
                        FlowNote.PASSENGER_COLAB -> setDetailColab(text)
                        FlowNote.VEHICLE -> setDetailVehicle(text)
                        FlowNote.DRIVER,
                        FlowNote.PASSENGER_INVOLVED,
                        FlowNote.INVOLVED,
                        FlowNote.WITNESS -> setDetailInvolved(text)
                    }
                    Option.EDIT -> when (flowNote) {
                        FlowNote.EQUIP -> updateDetailEquip(text, idMain)
                        FlowNote.EQUIP_SEC -> updateDetailEquipSecondary(text, idMain, idSecondary)
                        FlowNote.COLAB -> updateDetailColab(text, idMain)
                        FlowNote.PASSENGER_COLAB -> updateDetailPassengerColab(text, idMain, idSecondary)
                        FlowNote.VEHICLE -> updateDetailVehicle(text, idMain)
                        FlowNote.DRIVER -> updateDetailDriver(text, idMain)
                        FlowNote.PASSENGER_INVOLVED -> updateDetailPassengerInvolved(text, idMain, idSecondary)
                        FlowNote.INVOLVED -> updateDetailInvolved(text, idMain)
                        FlowNote.WITNESS -> updateDetailWitness(text, idMain)
                    }
                }
                result.getOrThrow().let { it as? Int }
            }
        }

}
