package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.PeopleExternal
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListExternalPeopleExternalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IListInvolvedExternal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listInvolved`() =
        runTest {
            whenever(
                cardRepository.listInvolvedExternal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listInvolved",
                    "-",
                    Exception()
                )
            )
            val result = usecase()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IListInvolved -> ICardRepository.listInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return emptyList if function execute successfully with empty list`() =
        runTest {
            whenever(
                cardRepository.listInvolvedExternal()
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                emptyList()
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.listInvolvedExternal()
            ).thenReturn(
                Result.success(
                    listOf(
                        PeopleExternal(
                            id = 1,
                            document = "12345678900",
                            name = "PERSON 1"
                        ),
                        PeopleExternal(
                            id = 2,
                            document = "98765432100",
                            name = "PERSON 2"
                        )
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        desc = "12345678900 - PERSON 1"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        desc = "98765432100 - PERSON 2"
                    )
                )
            )
        }

    @Test
    fun `Check return correct if function execute successfully with null values`() =
        runTest {
            whenever(
                cardRepository.listInvolvedExternal()
            ).thenReturn(
                Result.success(
                    listOf(
                        PeopleExternal(
                            id = 1,
                            document = null,
                            name = null
                        )
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                listOf(
                    ItemListScreenModel(
                        id = 1,
                        desc = "- - -"
                    )
                )
            )
        }

}