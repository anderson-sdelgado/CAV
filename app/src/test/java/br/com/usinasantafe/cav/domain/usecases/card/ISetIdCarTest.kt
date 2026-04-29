package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class ISetIdCarTest {

    private val equipRepository = mock<EquipRepository>()
    private val cardRepository = mock<CardRepository>()
    private val usecase = ISetIdCar(
        equipRepository = equipRepository,
        cardRepository = cardRepository
    )

    @Test
    fun `Check return failure if value of field is incorrect`() =
        runTest {
            val result = usecase("de25")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetIdCar -> toLong"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.NumberFormatException: For input string: \"de25\""
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getIdByNro`() =
        runTest {
            whenever(
                equipRepository.getIdByNro(200)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getIdByNro",
                    "-",
                    Exception()
                )
            )
            val result = usecase("200")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetIdCar -> IEquipRepository.getIdByNro"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository setIdCard`() =
        runTest {
            whenever(
                equipRepository.getIdByNro(200)
            ).thenReturn(
                Result.success(1)
            )
            whenever(
                cardRepository.setIdCar(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.setIdCar",
                    "-",
                    Exception()
                )
            )
            val result = usecase("200")
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "ISetIdCar -> ICardRepository.setIdCar"
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
                equipRepository.getIdByNro(200)
            ).thenReturn(
                Result.success(1)
            )
            val result = usecase("200")
            verify(cardRepository, atLeastOnce()).setIdCar(1)
            assertEquals(
                result.isSuccess,
                true
            )
        }


}