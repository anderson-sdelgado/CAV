package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.stable.ColabRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class IHasRegColabTest {

    private val colabRepository = mock<ColabRepository>()
    private val usecase = IHasRegColab(
        colabRepository = colabRepository
    )

    @Test
    fun `Check return failure if value of field is incorrect`() =
        runTest {
            val result = usecase("19759a")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IHasRegColab -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"19759a\""
            )
        }

}