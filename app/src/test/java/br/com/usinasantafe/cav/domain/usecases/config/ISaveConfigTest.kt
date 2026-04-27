package br.com.usinasantafe.cav.domain.usecases.config

import br.com.usinasantafe.cav.domain.entities.variable.Config
import br.com.usinasantafe.cav.domain.repositories.variable.ConfigRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISaveConfigTest {

    private val configRepository = mock<ConfigRepository>()
    private val usecase = ISaveConfig(
        configRepository = configRepository
    )

    @Test
    fun `Check return failure if number input is incorrect`() =
        runTest {
            val result = usecase(
                number = "dfas6fdsa7840",
                password = "12345",
                version = "1.00",
                idServ = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISaveConfig -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"dfas6fdsa7840\""
            )
        }

    @Test
    fun `Check return failure if have error in ConfigRepository save`() =
        runTest {
            whenever(
                configRepository.save(
                    Config(
                        number = 16997417840,
                        password = "12345",
                        version = "1.00",
                        idServ = 1
                    )
                )
            ).thenReturn(
                resultFailure(
                    "IConfigRepository.save",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                number = "16997417840",
                password = "12345",
                version = "1.00",
                idServ = 1
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISaveConfig -> IConfigRepository.save"
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
                number = "16997417840",
                password = "12345",
                version = "1.00",
                idServ = 1
            )
            verify(configRepository, atLeastOnce()).save(
                Config(
                    number = 16997417840,
                    password = "12345",
                    version = "1.00",
                    idServ = 1
                )
            )
            assertEquals(
                result.isSuccess,
                true
            )
        }

}