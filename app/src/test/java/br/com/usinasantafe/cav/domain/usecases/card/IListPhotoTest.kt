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

class IListPhotoTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IListPhoto(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listPhoto`() =
        runTest {
            whenever(
                cardRepository.listPhoto()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listPhoto",
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
                "IListPhoto -> ICardRepository.listPhoto"
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
                cardRepository.listPhoto()
            ).thenReturn(
                Result.success(listOf("test1", "test2"))
            )
            val result = usecase()
            verify(cardRepository, atLeastOnce()).listPhoto()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull(),
                listOf("test1", "test2")
            )
        }

}
