package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.Option
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
    fun `Check return failure if have error in CardRepository getPlate - OPTION INSERT`() =
        runTest {
            whenever(
                cardRepository.getPlate()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getPlate",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                option = Option.INSERT,
                idMain = 0
            )
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
    fun `Check return empty if function execute successfully and return is null - OPTION INSERT`() =
        runTest {
            whenever(
                cardRepository.getPlate()
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.INSERT,
                idMain = 0
            )
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
    fun `Check return correct if function execute successfully - OPTION INSERT`() =
        runTest {
            whenever(
                cardRepository.getPlate()
            ).thenReturn(
                Result.success("ABC1234")
            )
            val result = usecase(
                option = Option.INSERT,
                idMain = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "ABC1234"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getPlate - OPTION EDIT`() =
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
            val result = usecase(
                option = Option.EDIT,
                idMain = 1
            )
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
    fun `Check return empty if function execute successfully and return is null - OPTION EDIT`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success(null)
            )
            val result = usecase(
                option = Option.EDIT,
                idMain = 1
            )
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
    fun `Check return correct if function execute successfully - OPTION EDIT`() =
        runTest {
            whenever(
                cardRepository.getPlate(1)
            ).thenReturn(
                Result.success("ABC1234")
            )
            val result = usecase(
                option = Option.EDIT,
                idMain = 1
            )
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
