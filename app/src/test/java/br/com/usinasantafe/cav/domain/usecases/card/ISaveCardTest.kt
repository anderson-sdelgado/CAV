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

class ISaveCardTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = ISaveCard(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository save`() =
        runTest {
            whenever(
                cardRepository.save()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.save",
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
                "ISaveCard -> ICardRepository.save"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository clean`() =
        runTest {
            whenever(
                cardRepository.save()
            ).thenReturn(
                Result.success(Unit)
            )
            whenever(
                cardRepository.clean()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.clean",
                    "-",
                    Exception()
                )
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).save()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISaveCard -> ICardRepository.clean"
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
                cardRepository.save()
            ).thenReturn(
                Result.success(Unit)
            )
            whenever(
                cardRepository.clean()
            ).thenReturn(
                Result.success(Unit)
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).save()
            verify(cardRepository, atLeastOnce()).clean()
            assertEquals(
                result.isSuccess,
                true
            )
        }

}
