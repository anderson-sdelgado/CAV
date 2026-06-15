package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescDriverTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetDescDriver(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getDocumentDriver`() =
        runTest {
            whenever(
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getDocumentDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescDriver -> ICardRepository.getDocumentDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getNameDriver`() =
        runTest {
            whenever(
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                Result.success("12345678900")
            )
            whenever(
                cardRepository.getNameDriver(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getNameDriver",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescDriver -> ICardRepository.getNameDriver"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and document is null`() =
        runTest {
            whenever(
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                Result.success(null)
            )
            whenever(
                cardRepository.getNameDriver(1)
            ).thenReturn(
                Result.success("ANDERSON DA SILVA DELGADO")
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "- - ANDERSON DA SILVA DELGADO"
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.getDocumentDriver(1)
            ).thenReturn(
                Result.success("12345678900")
            )
            whenever(
                cardRepository.getNameDriver(1)
            ).thenReturn(
                Result.success("ANDERSON DA SILVA DELGADO")
            )
            val result = usecase(
                flowNote = FlowNote.DRIVER,
                idMain = 1
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "12345678900 - ANDERSON DA SILVA DELGADO"
            )
        }

}
