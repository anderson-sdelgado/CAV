package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.variable.Local
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.presenter.model.LocalViewModel
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetLocalTest {

    private val cardRepository = mock<CardRepository>()
    private val usecase = IGetLocal(
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getLocal`() =
        runTest {
            whenever(
                cardRepository.getLocal()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getLocal",
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
                "IGetLocal -> ICardRepository.getLocal"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully and Local is empty`() =
        runTest {
            whenever(
                cardRepository.getLocal()
            ).thenReturn(
                Result.success(Local())
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                LocalViewModel(
                    address = "-",
                    latitude = "-",
                    longitude = "-"
                )
            )
        }

    @Test
    fun `Check return correct if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.getLocal()
            ).thenReturn(
                Result.success(
                    Local(
                        address = "RUA TEST",
                        latitude = -25.0563524,
                        longitude = -27.126546
                    )
                )
            )
            val result = usecase()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                LocalViewModel(
                    address = "RUA TEST",
                    latitude = "-25.0563524",
                    longitude = "-27.126546"
                )
            )
        }

}