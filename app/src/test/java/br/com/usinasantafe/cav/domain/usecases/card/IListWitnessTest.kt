package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.Involved
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemListScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListWitnessTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IListWitness(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listWitness`() =
        runTest {
            whenever(
                cardRepository.listWitness()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listWitness",
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
                "IListWitness -> ICardRepository.listWitness"
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
                cardRepository.listWitness()
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
                cardRepository.listWitness()
            ).thenReturn(
                Result.success(
                    listOf(
                        Involved(
                            id = 1,
                            document = "12345678900",
                            name = "WITNESS 1"
                        ),
                        Involved(
                            id = 2,
                            document = "98765432100",
                            name = "WITNESS 2"
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
                        desc = "12345678900 - WITNESS 1"
                    ),
                    ItemListScreenModel(
                        id = 2,
                        desc = "98765432100 - WITNESS 2"
                    )
                )
            )
        }

    @Test
    fun `Check return correct if function execute successfully with null values`() =
        runTest {
            whenever(
                cardRepository.listWitness()
            ).thenReturn(
                Result.success(
                    listOf(
                        Involved(
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
