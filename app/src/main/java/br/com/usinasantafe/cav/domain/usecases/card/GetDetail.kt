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
                            FlowNote.COLAB,
                            FlowNote.PASSENGER_COLAB -> getDetailColab()
                            FlowNote.VEHICLE -> getDetailVehicle()
                            FlowNote.DRIVER,
                            FlowNote.PASSENGER_INVOLVED,
                            FlowNote.INVOLVED,
                            FlowNote.WITNESS -> getDetailInvolved()
                        }
                    }
                    Option.EDIT -> {
                        when (flowNote) {
                            FlowNote.EQUIP -> getDetailEquip(idMain)
                            FlowNote.EQUIP_SEC -> getDetailEquipSecondary(idMain, idSecondary)
                            FlowNote.COLAB -> getDetailColab(idMain)
                            FlowNote.PASSENGER_COLAB -> getDetailPassengerColab(idMain, idSecondary)
                            FlowNote.VEHICLE -> getDetailVehicle(idMain)
                            FlowNote.DRIVER -> getDetailDriver(idMain)
                            FlowNote.PASSENGER_INVOLVED -> getDetailPassengerInvolved(idMain, idSecondary)
                            FlowNote.INVOLVED -> getDetailInvolved(idMain)
                            FlowNote.WITNESS -> getDetailWitness(idMain)
                        }
                    }
                }.getOrThrow() ?: ""
            }
        }

}
