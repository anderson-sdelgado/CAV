package br.com.usinasantafe.cav.domain.usecases.card

import br.com.usinasantafe.cav.domain.entities.stable.Equip
import br.com.usinasantafe.cav.domain.repositories.stable.EquipRepository
import br.com.usinasantafe.cav.domain.repositories.variable.CardRepository
import br.com.usinasantafe.cav.lib.FlowNote
import br.com.usinasantafe.cav.utils.resultFailure
import kotlinx.coroutines.test.runTest
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class IGetDescEquipTest {

    private val cardRepository = mock<CardRepository>()
    private val equipRepository = mock<EquipRepository>()
    private val usecase = IGetDescEquip(
        cardRepository = cardRepository,
        equipRepository = equipRepository
    )

    @Test
    fun `Check return failure if have error in CardRepository getIdEquip - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getIdEquip(1)
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdEquip",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescEquip -> ICardRepository.getIdEquip"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getById - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getIdEquip(1)
            ).thenReturn(
                Result.success(10)
            )
            whenever(
                equipRepository.getById(10)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getById",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescEquip -> IEquipRepository.getById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote EQUIP`() =
        runTest {
            whenever(
                cardRepository.getIdEquip(1)
            ).thenReturn(
                Result.success(10)
            )
            whenever(
                equipRepository.getById(10)
            ).thenReturn(
                Result.success(
                    Equip(
                        id = 10,
                        nro = 2200,
                        description = "CAMINHÃO"
                    )
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP,
                idMain = 1,
                idSecondary = 0
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "2200 - CAMINHÃO"
            )
        }

    @Test
    fun `Check return failure if have error in CardRepository getIdEquipSecondary - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getIdEquipSecondary(
                    idMain = 1,
                    idSecondary = 2
                )
            ).thenReturn(
                resultFailure(
                    "ICardRepository.getIdEquipSecondary",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescEquip -> ICardRepository.getIdEquipSecondary"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return failure if have error in EquipRepository getById - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getIdEquipSecondary(
                    idMain = 1,
                    idSecondary = 2
                )
            ).thenReturn(
                Result.success(20)
            )
            whenever(
                equipRepository.getById(20)
            ).thenReturn(
                resultFailure(
                    "IEquipRepository.getById",
                    "-",
                    Exception()
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isFailure,
                true
            )
            assertEquals(
                result.exceptionOrNull()!!.message,
                "IGetDescEquip -> IEquipRepository.getById"
            )
            assertEquals(
                result.exceptionOrNull()!!.cause.toString(),
                "java.lang.Exception"
            )
        }

    @Test
    fun `Check return correct if function execute successfully - FlowNote EQUIP_SEC`() =
        runTest {
            whenever(
                cardRepository.getIdEquipSecondary(
                    idMain = 1,
                    idSecondary = 2
                )
            ).thenReturn(
                Result.success(20)
            )
            whenever(
                equipRepository.getById(20)
            ).thenReturn(
                Result.success(
                    Equip(
                        id = 20,
                        nro = 330,
                        description = "TRATOR"
                    )
                )
            )
            val result = usecase(
                flowNote = FlowNote.EQUIP_SEC,
                idMain = 1,
                idSecondary = 2
            )
            assertEquals(
                result.isSuccess,
                true
            )
            assertEquals(
                result.getOrNull()!!,
                "330 - TRATOR"
            )
        }

}
