package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetPlateTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetPlate(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getPlate`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPlate",
                    "-",
                    Exception()
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetPlate -> ICardRepository.getPlate"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty if function execute successfully and return is null`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success("ABC1234")
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "ABC1234"
            )
        }

}
