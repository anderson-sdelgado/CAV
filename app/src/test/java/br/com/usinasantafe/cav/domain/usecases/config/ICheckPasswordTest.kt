package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ICheckPasswordTest {

    private val configRepository = mock<ConfigRepository>()
    private val usecase = ICheckPassword(
        configRepository = configRepository
    )

    @Test
    fun `Check return failure if have error in ConfigRepository hasConfig`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                resultFailure(
                    "IConfigRepository.hasConfig",
                    "-",
                    Exception()
                )
            )
            val result = usecase("123456")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICheckPassword -> IConfigRepository.hasConfig"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return true if function execute successfully and not have data in config shared preferences`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                Result.success(false)
            )
            val result = usecase("123456")
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                true
            )
        }

    @Test
    fun `Check return failure if have error in ConfigRepository getPassword`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                configRepository.getPassword()
            ).thenReturn(
                resultFailure(
                    "IConfigRepository.getPassword",
                    "-",
                    Exception()
                )
            )
            val result = usecase("123456")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ICheckPassword -> IConfigRepository.getPassword"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return false if function execute successfully and password input is different at password db`() =
        runTest {
            whenever(
                configRepository.has()
            ).thenReturn(
                Result.success(true)
            )
            whenever(
                configRepository.getPassword()
            ).thenReturn(
                Result.success("456789")
            )
            val result = usecase("123456")
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