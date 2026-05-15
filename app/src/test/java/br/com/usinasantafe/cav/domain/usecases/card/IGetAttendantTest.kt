package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetAttendantTest {

    private val cardRepository = mock<CardRepository>()
    private val colabRepository = mock<ColabRepository>()
    private val usecase = IGetAttendant(
        cardRepository = cardRepository,
        colabRepository = colabRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getRegAttendant`() =
        runTest {
            whenever(
                cardRepository.getRegAttendant()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getRegAttendant",
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
                "IGetAttendant -> ICardRepository.getRegAttendant"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in ColabRepository getNameByReg`() =
        runTest {
            whenever(
                cardRepository.getRegAttendant()
            ).thenReturn(
                Result.success(19759)
            )
            whenever(
                colabRepository.getNameByReg(19759)
            ).thenReturn(
                resultFailure(
                    "IColabRepository.getNameByReg",
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
                "IGetAttendant -> IColabRepository.getNameByReg"
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
                cardRepository.getRegAttendant()
            ).thenReturn(
                Result.success(19759)
            )
            whenever(
                colabRepository.getNameByReg(19759)
            ).thenReturn(
                Result.success("ANDERSON DA SILVA DELGADO")
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "19759 - ANDERSON DA SILVA DELGADO"
            )
        }

}