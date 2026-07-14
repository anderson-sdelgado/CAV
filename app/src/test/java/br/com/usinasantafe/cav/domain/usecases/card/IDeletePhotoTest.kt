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

class IDeletePhotoTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IDeletePhoto(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository deletePhoto`() =
        runTest {
            whenever(
                cardRepository.deletePhoto("test")
            ).thenReturn(
                resultFailure(
                    "ICardRepository.deletePhoto",
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
                "IDeletePhoto -> ICardRepository.deletePhoto"
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
                cardRepository.deletePhoto("test")
            ).thenReturn(
                Result.success(Unit)
            )
            val result = usecase("test")
            verify(cardRepository, atLeastOnce()).deletePhoto("test")
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
