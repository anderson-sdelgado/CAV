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

class IGetDescEquipSecTest {

    private val cardRepository = mock<CardRepository>()
    private val equipRepository = mock<EquipRepository>()
    private val usecase = IGetDescEquipSec(
        cardRepository = cardRepository,
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository listIdEquipSecondary`() =
        runTest {
            whenever(
                cardRepository.listIdEquipSecondary(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.listIdEquipSecondary",
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
                "IGetDescEquipSec -> ICardRepository.listIdEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository listByIdList`() =
        runTest {
            whenever(
                cardRepository.listIdEquipSecondary(1)
            ).thenReturn(
                Result.success(listOf(10, 20))
            )
            whenever(
                equipRepository.listByIdList(listOf(10, 20))
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.listByIdList",
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
                "IGetDescEquipSec -> IEquipRepository.listByIdList"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return empty string if function execute successfully with empty list`() =
        runTest {
            whenever(
                cardRepository.listIdEquipSecondary(1)
            ).thenReturn(
                Result.success(emptyList())
            )
            whenever(
                equipRepository.listByIdList(emptyList())
            ).thenReturn(
                Result.success(emptyList())
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                ""
            )
        }

    @Test
    fun `Check return correct formatted string if function execute successfully`() =
        runTest {
            whenever(
                cardRepository.listIdEquipSecondary(1)
            ).thenReturn(
                Result.success(listOf(10, 20))
            )
            whenever(
                equipRepository.listByIdList(listOf(10, 20))
            ).thenReturn(
                Result.success(
                    listOf(
                        Equip(
                            id = 10,
                            nro = 100,
                            description = "EQUIP 1"
                        ),
                        Equip(
                            id = 20,
                            nro = 200,
                            description = "EQUIP 2"
                        )
                    )
                )
            )
            val result = usecase(1)
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "100 - EQUIP 1\n200 - EQUIP 2"
            )
        }

}
