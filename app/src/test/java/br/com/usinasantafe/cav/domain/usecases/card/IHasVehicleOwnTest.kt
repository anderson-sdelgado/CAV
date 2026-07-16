package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.VehicleOwn
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IHasVehicleOwnTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IHasVehicleOwn(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listVehicleOwn`() =
        runTest {
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listVehicleOwn",
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
                "IHasVehicleOwn -> ICardRepository.listVehicleOwn"
            )
        }

    @Test
    fun `Check return true if CardRepository listVehicleOwn is not empty`() =
        runTest {
            whenever(
                cardRepository.listVehicleOwn()
            ).thenReturn(
                Result.success(listOf(VehicleOwn()))
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun `Check return false if CardRepository listVehicleOwn is empty`() =
        runTest {
            whenever(
                cardRepository.listVehicleOwn()
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
                false
            )
        }

}
