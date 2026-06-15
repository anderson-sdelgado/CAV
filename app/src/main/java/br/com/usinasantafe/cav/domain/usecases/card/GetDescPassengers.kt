package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface GetDescPassengers {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<String>
}

class IGetDescPassengers @Inject constructor(
    private val cardRepository: CardRepository,
    private val colabRepository: ColabRepository
): GetDescPassengers {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<String> =
        call(getClassAndMethod()) {
            when(flowNote){
                FlowNote.PASSENGER_COLAB -> {
                    val regList = cardRepository.listRegPassengerColab(idMain).getOrThrow()
                    val entityList = colabRepository.listColabByRegList(regList).getOrThrow()
                    val descList = entityList.map { "${it.reg} - ${it.name}" }
                    descList.joinToString(separator = "\n")
                }
                else -> {
                    val entityList = cardRepository.listPassengerInvolved(idMain).getOrThrow()
                    val descList = entityList.map {  "${it.document ?: "-"} - ${it.name}" }
                    descList.joinToString(separator = "\n")
                }
            }
        }

}
