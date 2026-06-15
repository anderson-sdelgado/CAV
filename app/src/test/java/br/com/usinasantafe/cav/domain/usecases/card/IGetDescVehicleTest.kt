package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescVehicleTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetDescVehicle(
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
                "IGetDescVehicle -> ICardRepository.getPlate"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getBrand`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success("ABC-1234")
            )
            whenever(
                cardRepository.getBrand(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getBrand",
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
                "IGetDescVehicle -> ICardRepository.getBrand"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and plate is null`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success(null)
            )
            whenever(
                cardRepository.getBrand(1)
            ).thenReturn(
                Result.success("FIAT")
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "- - FIAT"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and brand is null`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success("ABC1234")
            )
            whenever(
                cardRepository.getBrand(1)
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
                "ABC1234 - -"
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
            whenever(
                cardRepository.getBrand(1)
            ).thenReturn(
                Result.success("FIAT")
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "ABC1234 - FIAT"
            )
        }

}
