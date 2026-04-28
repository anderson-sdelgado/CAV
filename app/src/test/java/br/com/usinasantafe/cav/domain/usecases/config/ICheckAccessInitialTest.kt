package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ICheckAccessInitialTest {

    private val configRepository = Mockito.mock<ConfigRepository>()
    private val usecase = ICheckAccessInitial(
        configRepository = configRepository
    )

    @Test
    fun `Check return failure if have error in ConfigRepository has`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                resultFailure(
                    "IConfigRepository.has",
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
                "ICheckAccessInitial -> IConfigRepository.has"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if config has return false`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                Result.success(false)
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

    @Test
    fun `Check return failure if have error in ConfigRepository getFlagUpdate`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                configRepository.getFlagUpdate()
            ).thenReturn(
                resultFailure(
                    "IConfigRepository.getFlagUpdate",
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
                "ICheckAccessInitial -> IConfigRepository.getFlagUpdate"
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
                configRepository.has()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                configRepository.getFlagUpdate()
            ).thenReturn(
                Result.success(false)
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