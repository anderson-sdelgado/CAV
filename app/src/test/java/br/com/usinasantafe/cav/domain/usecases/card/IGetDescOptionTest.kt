package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.DataLocalRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescOptionTest {

    private val dataLocalRepository = mock<DataLocalRepository>()
    private val usecase = IGetDescOption(
        dataLocalRepository = dataLocalRepository
    )

    @Test
    fun `Check return failure if have error in DataLocalRepository getDescOptionById`() =
        runTest {
            whenever(
                dataLocalRepository.getDescOptionById(1)
            ).thenReturn(
                resultFailure(
                    "IDataLocalRepository.getDescOptionById",
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
                "IGetDescOption -> IDataLocalRepository.getDescOptionById"
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
                dataLocalRepository.getDescOptionById(1)
            ).thenReturn(
                Result.success("Test")
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "Test"
            )
        }

}