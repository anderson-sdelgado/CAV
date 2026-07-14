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

class ISetPhotoTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetPhoto(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository setPhoto`() =
        runTest {
            whenever(
                cardRepository.setPhoto("test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setPhoto",
                    "-",
                    Exception()
                )
            )
            val result = usecase("test")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetPhoto -> ICardRepository.setPhoto"
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
                cardRepository.setPhoto("test")
            ).thenReturn(
                Result.success(Unit)
            )
            val result = usecase("test")
            verify(cardRepository, atLeastOnce()).setPhoto("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
