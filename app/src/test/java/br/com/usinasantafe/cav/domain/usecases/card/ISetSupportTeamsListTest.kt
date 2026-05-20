package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetSupportTeamsListTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetSupportTeamsList(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setIdSupportTeamsList`() =
        runTest {
            whenever(
                cardRepository.setIdSupportTeamsList(listOf(1, 3))
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setIdSupportTeamsList",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "Test1",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 3,
                        desc = "Test3",
                        flag = true
                    )
                )
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetSupportTeamsList -> ICardRepository.setIdSupportTeamsList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            val result = usecase(
                listOf(
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "Test1",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    ),
                    ItemCheckBoxScreenModel(
                        id = 3,
                        desc = "Test3",
                        flag = true
                    )
                )
            )
            verify(cardRepository, atLeastOnce()).setIdSupportTeamsList(listOf(1, 3))
            assertEquals(
                result.isSuccess,
                true
            )
        }

}