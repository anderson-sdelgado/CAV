package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IDeleteVehiclePeopleExternalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IDeleteVehicleExternal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository deleteVehicleInvolved`() =
        runTest {
            whenever(
                cardRepository.deleteVehicleExternal(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.deleteVehicleInvolved",
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
                "IDeleteVehicleInvolved -> ICardRepository.deleteVehicleInvolved"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            val result = usecase(1)
            verify(cardRepository, atLeastOnce()).deleteVehicleExternal(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
