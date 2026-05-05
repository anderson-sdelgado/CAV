package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetLocalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetLocal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setLocal`() =
        runTest {
            whenever(
                cardRepository.setLocal(
                    Local(
                        address = "Test",
                        latitude = 0.0,
                        longitude = 0.0
                    )
                )
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setLocal",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                address = "Test",
                latitude = 0.0,
                longitude = 0.0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetLocal -> ICardRepository.setLocal"
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
                address = "Test",
                latitude = 0.0,
                longitude = 0.0
            )
            verify(cardRepository, atLeastOnce()).setLocal(
                Local(
                    address = "Test",
                    latitude = 0.0,
                    longitude = 0.0
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
        }

}