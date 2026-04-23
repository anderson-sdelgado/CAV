package br.com.usinasantafe.cav.infra.repositories.variable

import br.com.usinasantafe.cav.infra.datasource.ConfigSharedPreferencesDatasource
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IConfigRepositoryTest {

    private val configSharedPreferencesDatasource = mock<ConfigSharedPreferencesDatasource>()
    private val repository = IConfigRepository(
        configSharedPreferencesDatasource = configSharedPreferencesDatasource
    )

    @Test
    fun `has - Check return failure if have error in ConfigSharedPreferencesDatasource has`() =
        runTest {
            whenever(
                configSharedPreferencesDatasource.has()
            ).thenReturn(
                resultFailure(
                    "IConfigSharedPreferencesDatasource.has",
                    "-",
                    Exception()
                )
            )
            val result = repository.has()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IConfigRepository.has -> IConfigSharedPreferencesDatasource.has"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `has - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                configSharedPreferencesDatasource.has()
            ).thenReturn(
                Result.success(true)
            )
            val result = repository.has()
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
    fun `getPassword - Check return failure if have error in ConfigSharedPreferencesDatasource getPassword`() =
        runTest {
            whenever(
                configSharedPreferencesDatasource.getPassword()
            ).thenReturn(
                resultFailure(
                    "IConfigSharedPreferencesDatasource.getPassword",
                    "-",
                    Exception()
                )
            )
            val result = repository.getPassword()
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IConfigRepository.getPassword -> IConfigSharedPreferencesDatasource.getPassword"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `getPassword - Check return correct if function execute successfully`() =
        runTest {
            whenever(
                configSharedPreferencesDatasource.getPassword()
            ).thenReturn(
                Result.success("123456")
            )
            val result = repository.getPassword()
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "123456"
            )
        }

}