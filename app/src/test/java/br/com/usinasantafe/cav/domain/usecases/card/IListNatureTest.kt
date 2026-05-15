package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Nature
import br.com.usinasantafe.cav.domain.repositories.stable.NatureRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.ItemCheckBoxScreenModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IListNatureTest {

    private val natureRepository = mock<NatureRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = IListNature(
        natureRepository = natureRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in NatureRepository listAll`() =
        runTest {
            whenever(
                natureRepository.listAll()
            ).thenReturn(
                resultFailure(
                    "INatureRepository.listAll",
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
                "IListNature -> INatureRepository.listAll"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository listIdNature`() =
        runTest {
            whenever(
                natureRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        Nature(
                            id = 1,
                            description = "Test"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdNature",
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
                "IListNature -> ICardRepository.listIdNature"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                natureRepository.listAll()
            ).thenReturn(
                Result.success(
                    listOf(
                        Nature(
                            id = 1,
                            description = "Test"
                        ),
                        Nature(
                            id = 2,
                            description = "Test2"
                        )
                    )
                )
            )
            whenever(
                cardRepository.listIdNature()
            ).thenReturn(
                Result.success(
                    listOf(1)
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
                    ItemCheckBoxScreenModel(
                        id = 1,
                        desc = "Test",
                        flag = true
                    ),
                    ItemCheckBoxScreenModel(
                        id = 2,
                        desc = "Test2",
                        flag = false
                    )
                )
            )
        }

}