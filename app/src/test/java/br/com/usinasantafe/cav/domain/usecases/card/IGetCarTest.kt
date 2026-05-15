package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetCarTest {

    private val cardRepository = mock<CardRepository>()
    private val equipRepository = mock<EquipRepository>()
    private val usecase = IGetCar(
        cardRepository = cardRepository,
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getIdCar`() =
        runTest {
            whenever(
                cardRepository.getIdCar()
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdCar",
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
                "IGetCar -> ICardRepository.getIdCar"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getById`() =
        runTest {
            whenever(
                cardRepository.getIdCar()
            ).thenReturn(
                Result.success(1)
            )
            whenever(
                equipRepository.getById(1)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getById",
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
                "IGetCar -> IEquipRepository.getById"
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
                cardRepository.getIdCar()
            ).thenReturn(
                Result.success(1)
            )
            whenever(
                equipRepository.getById(1)
            ).thenReturn(
                Result.success(
                    Equip(
                        id = 1,
                        nro = 2200,
                        description = "CAMINHÃO"
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
                "2200 - CAMINHÃO"
            )
        }

}