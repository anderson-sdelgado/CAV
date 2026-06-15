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

class IDeleteEquipSecTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IDeleteEquipSec(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository deleteEquipSecondary`() =
        runTest {
            whenever(
                cardRepository.deleteEquipSecondary(1, 2)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.deleteEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                idSelection = 2,
                idMain = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IDeleteEquipSec -> ICardRepository.deleteEquipSecondary"
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
                idSelection = 2,
                idMain = 1
            )
            verify(cardRepository, atLeastOnce()).deleteEquipSecondary(1, 2)
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
