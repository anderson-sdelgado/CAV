package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetTypeAccidentListTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetTypeAccidentList(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setIdTypeAccidentList`() =
        runTest {
            whenever(
                cardRepository.setIdTypeAccidentList(listOf(1, 3))
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setIdTypeAccidentList",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                listOf(
                    ItemCheckBoxModel(
                        id = 1,
                        desc = "Test1",
                        flag = true
                    ),
                    ItemCheckBoxModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    ),
                    ItemCheckBoxModel(
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
                "ISetTypeAccidentList -> ICardRepository.setIdTypeAccidentList"
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
                    ItemCheckBoxModel(
                        id = 1,
                        desc = "Test1",
                        flag = true
                    ),
                    ItemCheckBoxModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    ),
                    ItemCheckBoxModel(
                        id = 3,
                        desc = "Test3",
                        flag = true
                    )
                )
            )
            verify(cardRepository, atLeastOnce()).setIdTypeAccidentList(listOf(1, 3))
            assertEquals(
                result.isSuccess,
                true
            )
        }

}