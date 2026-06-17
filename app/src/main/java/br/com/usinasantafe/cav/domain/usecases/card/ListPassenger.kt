package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.call
import br.com.usinasantafe.cav.utils.getClassAndMethod
import javax.inject.Inject

interface ListPassenger {
    suspend operator fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<List<ItemListScreenModel>>
}

class IListPassenger @Inject constructor(
    private val cardRepository: CardRepository,
    private val colabRepository: ColabRepository
): ListPassenger {

    override suspend fun invoke(
        flowNote: FlowNote,
        idMain: Int
    ): Result<List<ItemListScreenModel>> =
        call(getClassAndMethod()) {
            when(flowNote){
                FlowNote.PASSENGER_COLAB -> {
                    val colabList = cardRepository.listPassengerColab(idMain).getOrThrow()
                    val regList = colabList.map { it.reg!! }
                    val entityList = colabRepository.listColabByRegList(regList).getOrThrow()
                    colabList.map { colabCard ->
                        val fullData = entityList.find { it.reg == colabCard.reg }
                        ItemListScreenModel(
                            id = colabCard.id!!,
                            desc = "${colabCard.reg} - ${fullData?.name ?: "-"}"
                        )
                    }
                }
                else -> {
                    val entityList = cardRepository.listPassengerInvolved(idMain).getOrThrow()
                    entityList.map {
                        ItemListScreenModel(
                            id = it.id!!,
                            desc = "${it.document ?: "-"} - ${it.name ?: "-"}"
                        )
                    }
                }
            }
        }

}
