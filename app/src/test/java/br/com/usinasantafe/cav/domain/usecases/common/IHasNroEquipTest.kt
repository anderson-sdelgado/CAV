package br.com.usinasantafe.cav.domain.usecases.common

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IHasNroEquipTest {

    private val equipRepository = mock<EquipRepository>()
    private val usecase = IHasNroEquip(
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if value of field is incorrect`() =
        runTest {
            val result = usecase("d526")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IHasNroEquip -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"d526\""
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository hasNro`() =
        runTest {
            whenever(
                equipRepository.hasNro(2200)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.hasNro",
                    "-",
                    Exception()
                )
            )
            val result = usecase("2200")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IHasNroEquip -> IEquipRepository.hasNro"
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
                equipRepository.hasNro(2200)
            ).thenReturn(
                Result.success(false)
            )
            val result = usecase("2200")
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